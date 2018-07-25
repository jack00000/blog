package com.how2java.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.springboot.mapper.SellerMapper;
import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.Seller;
import com.how2java.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SellerController {
    @Autowired
    SellerMapper sellerMapper;

   @RequestMapping("/addSeller")
    public String addSeller(Seller s)  {

        sellerMapper.save(s);
        return "redirect:listSeller";
    }
    @RequestMapping("/deleteSeller")
    public String deleteSeller(Seller s) throws Exception {
        sellerMapper.delete(s.getSlid());
        return "redirect:listSeller";
    }
   /* @RequestMapping("/updateSeller")
    public String updateCategory(Seller s) throws Exception {
        sellerMapper.update(s);
        return "redirect:listSeller";
    }*/
    @RequestMapping("/editSeller")
    public String listSeller(int slid,Model m) throws Exception {
        Seller s= sellerMapper.get(slid);
        m.addAttribute("s", s);
        return "editSeller";
    }

    @RequestMapping("/listSeller")
    public String listCategory(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        PageHelper.startPage(start,size,"slid desc");
        List<Seller> sellers=sellerMapper.findAll();
        PageInfo<Seller> page = new PageInfo<>(sellers);
        m.addAttribute("page", page);
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        return "sellerMana";
    }
}
