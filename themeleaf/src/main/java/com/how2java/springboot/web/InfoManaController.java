package com.how2java.springboot.web;

import com.how2java.springboot.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfoManaController {

    @RequestMapping("/trainMana")
    public String trainMana(Model m){
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        return "trainMana";
    }
    @RequestMapping("/userMana")
    public String userMana(Model m){
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        return "userMana";
    }
    @RequestMapping("/ticketMana")
    public String ticketMana(Model m){
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        return "ticketMana";
    }
    @RequestMapping("/saleMana")
    public String saleMana(Model m){
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        return "saleMana";
    }
    @RequestMapping("/backMana")
    public String backMana(Model m){
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        return "backMana";
    }
}
