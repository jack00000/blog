package com.how2java.springboot.web;

import com.how2java.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login2main")
    public String login2main(User user, Model m){
        System.out.println(user.getPassword());
        User u1=new User("zhangsan","88888888");


        if(user.getName().equalsIgnoreCase(u1.getName())&&user.getPassword().equalsIgnoreCase(user.getPassword())){
            m.addAttribute("u1",u1);
            return "fristPage";
        }else {
            return "login";
        }




    }
}
