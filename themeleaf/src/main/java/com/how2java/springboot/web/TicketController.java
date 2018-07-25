package com.how2java.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.TicketMapper;
import com.how2java.springboot.mapper.TrainMapper;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.Ticket;
import com.how2java.springboot.pojo.Train;
import com.how2java.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TicketController {
    @Autowired
    TicketMapper ticketMapper;

    @RequestMapping("/listTicket")
    public String listTicket(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start,size,"tiid desc");
        List<Ticket> tickets=ticketMapper.findAll();
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        PageInfo<Ticket> page = new PageInfo<>(tickets);
        m.addAttribute("page", page);
        return "ticketMana";
    }

    @RequestMapping("/deleteTicket")
    public String deleteTicket(Ticket t){
        ticketMapper.delete(t.getTiid());
        System.out.println(t.getTiid());
        System.out.println(t.getTiid());
        System.out.println(t.getTiid());
        System.out.println(t.getTiid());
        System.out.println(t.getTiid());
        System.out.println(t.getTiid());
        System.out.println(t.getTiid());
        return "redirect:listTicket";
    }

    @RequestMapping("/addTicket")
    public String addTicket(Ticket t){
        ticketMapper.save(t);
        return "redirect:listTicket";
    }
}
