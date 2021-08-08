package space.gavinklfong.demo.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.gavinklfong.demo.security.model.LoginAttempt;
import space.gavinklfong.demo.security.repository.LoginAttemptRepository;

import java.util.List;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private LoginAttemptRepository loginAttemptRepo;

    @GetMapping("/test")
    public String getEntries() {
        List<LoginAttempt> loginAttempts = loginAttemptRepo.findByUsername("user1");

        loginAttempts.forEach(item -> {
            log.info("login attempts = {}", item.toString());
        });

        return "OK";
    }
}
