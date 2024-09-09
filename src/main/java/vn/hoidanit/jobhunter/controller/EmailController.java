package vn.hoidanit.jobhunter.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import vn.hoidanit.jobhunter.service.EmailService;
import vn.hoidanit.jobhunter.service.SubscriberService;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class EmailController {
    private final EmailService emailService;
    private final SubscriberService subscriberService;

    public EmailController(EmailService emailService, SubscriberService subscriberService) {
        this.emailService = emailService;
        this.subscriberService = subscriberService;
    }

    // @Scheduled(cron = "*/30 * * * * *")
    @GetMapping("/email")
    @ApiMessage("Send simple email")
    @Transactional
    public String sendSimpleEmail() {
        // this.emailService.sendSimpleEmail();
        // this.emailService.sendEmailSync("long542.nt@gmail.com", "test send email",
        // "<h1> <b> hello </b> </h1>", false,
        // true);
        // this.emailService.sendEmailFromTemplateSync("long542.nt@gmail.com", "test
        // send email", "job");
        this.subscriberService.sendSubscribersEmailJobs();
        return "ok";
    }
}
