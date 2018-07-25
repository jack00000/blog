package com.how2java.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.SaleMapper;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.Sale;
import com.how2java.springboot.pojo.Train;
import com.how2java.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SaleController {
     @Autowired
    SaleMapper saleMapper;




    @RequestMapping("/listSale")
    public String listTrain(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start,size,"tiid desc");
        List<Sale> sales=saleMapper.findAll();
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        PageInfo<Sale> page = new PageInfo<>(sales);
        m.addAttribute("page", page);
        return "saleMana";
    }
    @RequestMapping("/addSale")
    public String addSale(Sale s){
        saleMapper.save(s);
        return "redirect:listSale";
    }

    @RequestMapping("/deleteSale")
    public String deleteSale(Sale s){
        saleMapper.delete(s.getTiid());
        return "redirect:listSale";
    }
}
