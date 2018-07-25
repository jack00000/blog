package com.how2java.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.BackMapper;
import com.how2java.springboot.pojo.Back;
import com.how2java.springboot.pojo.Train;
import com.how2java.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BackController {
    @Autowired
    BackMapper backMapper;

    @RequestMapping("/listBack")
    public String listTrain(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start,size,"tiid desc");
        List<Back> backs=backMapper.findAll();
        m.addAttribute("backs",backs);
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        PageInfo<Back> page=new PageInfo<>(backs);
        m.addAttribute("page",page);
        return "backMana";
    }

    @RequestMapping("/deleteBack")
    public String deleteBack(Back b){
        backMapper.delete(b.getTiid());
        System.out.println(b.getTiid());
        System.out.println(b.getTiid());
        System.out.println(b.getTiid());
        System.out.println(b.getTiid());
        System.out.println(b.getTiid());
        return "redirect:listBack";
    }


    @RequestMapping("/addBack")
    public String addBack(Back b){
        backMapper.save(b);

        return "redirect:listBack";
    }


}
