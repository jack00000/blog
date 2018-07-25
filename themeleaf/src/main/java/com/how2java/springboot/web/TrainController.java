package com.how2java.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.TicketMapper;
import com.how2java.springboot.mapper.TrainMapper;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.Train;
import com.how2java.springboot.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TrainController {
    @Autowired
    TrainMapper trainMapper;

    @RequestMapping("/updateTrain")
    public String updataTrain(Train t){
        trainMapper.update(t);
        return "redirect:listTrain";
    }

    @RequestMapping("/addTrain")
    public String addTrain(Train t){
        trainMapper.save(t);
        return "redirect:listTrain";
    }
    @RequestMapping("/editTrain")
    public String editTrain(int trid,Model m){
        Train t=trainMapper.get(trid);
        m.addAttribute("t",t);
        return "editTrain";
    }
    @RequestMapping("/deleteTrain")
    public String deleteTrain(Train t){
        trainMapper.delete(t.getTrid());
        return "redirect:listTrain";
    }

    @RequestMapping("/listTrain")
    public String listTrain(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start,size,"trid desc");
        List<Train> trains=trainMapper.findAll();
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        PageInfo<Train> page = new PageInfo<>(trains);
        m.addAttribute("page", page);
        return "trainMana";
    }
}
