package com.how2java.springboot.web;

import com.how2java.springboot.mapper.*;
import com.how2java.springboot.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SerachController {
    @Autowired
    TrainMapper trainMapper;

    @Autowired
    StationMapper stationMapper;

    @Autowired
    SellerMapper sellerMapper;

    @Autowired
    SaleMapper  saleMapper;

    @RequestMapping("/serach")
    public String serach(Sale s, Model m){
        User u1=new User("zhangsan","88888888");
        m.addAttribute("u1",u1);
        Sale sale=saleMapper.get(s.getTiid());
        Seller seller=sellerMapper.get(sale.getSlid());

        m.addAttribute("sale",sale);
        m.addAttribute("seller",seller);

        return "result";
    }


}
