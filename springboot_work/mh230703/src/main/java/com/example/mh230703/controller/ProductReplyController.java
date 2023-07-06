package com.example.mh230703.controller;

import com.example.mh230703.dao.ProductsReplyRepository;
import com.example.mh230703.dao.ProductsRepository;
import com.example.mh230703.dto.Product;
import com.example.mh230703.dto.Product_reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/products_reply")
public class ProductReplyController {

    @Autowired
    ProductsReplyRepository productsReplyRepository;

    @GetMapping("insert")
    @ResponseBody
    public String insert(Product_reply product_reply){
        System.out.println(product_reply);
        productsReplyRepository.doInsert(product_reply);
        return "test";
    }

    @GetMapping("view")
    public String view(Model model,Product product){
//        Product dbProduct = productsRepository.doSelectRow(product);
//        model.addAttribute("product",dbProduct);
        return "product/view";
    }

}
