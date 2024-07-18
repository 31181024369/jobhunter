package vn.hoidanit.jobhunter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.util.error.IdInvalidException;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // @GetMapping("/users/create")
    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User postManUser) {
        // User user = new User();
        // user.setEmail("long542.nt@gmail.com");
        // user.setName("longhoang");
        // user.setPassword("123456");
        String hashPassword = this.passwordEncoder.encode(postManUser.getPassword());
        postManUser.setPassword(hashPassword);
        User ericUser = this.userService.handCreateUser(postManUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(ericUser);
    }

    // @ExceptionHandler(value = IdInvalidException.class)
    // public ResponseEntity<String> handleIdException(IdInvalidException
    // idException) {
    // return ResponseEntity.badRequest().body(idException.getMessage());
    // }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) throws IdInvalidException {
        if (id >= 1500) {
            throw new IdInvalidException("Id khong lon hown 1501");
        }

        this.userService.handleDeleteUser(id);
        // return ResponseEntity.status(HttpStatus.OK).body("delete user success");
        return ResponseEntity.ok("ericUser");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User userCurrent = this.userService.fetchUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userCurrent);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> listUser = this.userService.fetchAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(listUser);

    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User userUpdate = this.userService.handleUploadUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }

}
