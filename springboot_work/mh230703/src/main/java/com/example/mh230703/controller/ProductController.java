package com.example.mh230703.controller;

import com.example.mh230703.dao.ProductsRepository;
import com.example.mh230703.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductsRepository productsRepository;

    @PostMapping("insert")
    public String insert(Product product){
        System.out.println(product);
        productsRepository.doInsert(product);
        return "redirect:/";
    }

    @GetMapping("view")
    public String view(Model model,Product product){
        Product dbProduct = productsRepository.doSelectRow(product);
        model.addAttribute("product",dbProduct);
        return "product/view";
    }

}
