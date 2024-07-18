package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);

    }

    public User fetchUserById(long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public List<User> fetchAllUser() {
        return this.userRepository.findAll();

    }

    public User handleUploadUser(User user) {
        User currentUser = this.fetchUserById(user.getId());
        if (currentUser != null) {
            currentUser.setName(user.getEmail());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());
            currentUser = this.userRepository.save(currentUser);
        }
        return currentUser;

    }

    public User handleGetUserByUsername(String username) {
        return this.userRepository.findByEmail(username);
    }
}
