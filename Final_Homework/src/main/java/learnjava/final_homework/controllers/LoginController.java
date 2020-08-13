package learnjava.final_homework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    
    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }
    
    @GetMapping("/teacher")
    public String teacher() {
        return "/teacher";
    }

    
    @GetMapping("/student")
    public String student() {
        return "/student";
    }


    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
    
    @GetMapping("/layout")
    public String layout() {
        return "/layout_sample";
    }

}
