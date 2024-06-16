package org.example.tpo_12.auth;

import org.example.tpo_12.auth.UserRegisterDTO;
import org.example.tpo_12.auth.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reader")
    public String registerReaderForm(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "reader-registration-form";
    }

    @GetMapping("/publisher")
    public String registerPublisherForm(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "publisher-registration-form";
    }

    @PostMapping("/reader")
    public String registerReader(UserRegisterDTO user) {
        userService.registerReader(user);
        return "redirect:/register/confirm";
    }

    @PostMapping("/publisher")
    public String registerPublisher(UserRegisterDTO user) {
        userService.registerPublisher(user);
        return "redirect:/register/confirm";
    }

    @GetMapping("/confirm")
    public String confirm() {
        return "registration-confirm";
    }
}
