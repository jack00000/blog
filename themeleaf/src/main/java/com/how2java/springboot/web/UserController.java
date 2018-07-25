package com.how2java.springboot.web;

import com.how2java.springboot.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;

@Controller
public class UserController extends HttpServlet {



    @GetMapping("/user")
    public String greetingForm(Model model) {

        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping("/user")
    public String greetingSubmit(@ModelAttribute User user) {
        return "result";
    }
}
