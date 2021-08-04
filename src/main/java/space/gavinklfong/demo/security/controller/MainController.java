package space.gavinklfong.demo.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value={"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String adminIndex() {
        return "admin/index";
    }

    @GetMapping("/shared")
    public String sharedIndex() {
        return "shared/index";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/403")
    public String notFound() {
        return "403";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/login-error")
    public String loginError() {
        return "login-error";
    }
}
