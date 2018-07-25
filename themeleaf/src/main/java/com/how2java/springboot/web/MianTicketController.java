package com.how2java.springboot.web;

import com.how2java.springboot.mapper.TicketMapper;
import com.how2java.springboot.mapper.TrainMapper;
import com.how2java.springboot.pojo.Ticket;
import com.how2java.springboot.pojo.Train;
import com.how2java.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.SplittableRandom;

@Controller
public class MianTicketController {
    @Autowired
    TrainMapper trainMapper;

    @Autowired
    TicketMapper ticketMapper;

    @RequestMapping("/ticketInfo")
    public String listTicketInfo(Model m){
        List<Ticket> tickets=ticketMapper.findAll();
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        m.addAttribute("tickets",tickets);
        return "ticketInfo";
    }
    @RequestMapping("/trainInfo")
    public String listTrainInfo(Model m){
        List<Train> trains=  trainMapper.findAll();
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        m.addAttribute("trains",trains);
        return "trainInfo";
    }
    @RequestMapping("/fristPage")
    public String showFristPage(){
        return "fristPage";
    }
}
