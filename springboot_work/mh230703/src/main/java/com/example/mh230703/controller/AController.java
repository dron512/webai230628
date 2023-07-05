package com.example.mh230703.controller;

import com.example.mh230703.dao.PeopleRepository;
import com.example.mh230703.dao.PostsRepository;
import com.example.mh230703.dao.ProductsRepository;
import com.example.mh230703.dto.People;
import com.example.mh230703.dto.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Controller
public class AController {

    @Autowired
    DataSource ds;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    SqlSession sqlSession;

    @GetMapping("/")
    public String index(Model model) throws IOException {
/*
        ArrayList<People> al = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = null;
        try{
            conn = ds.getConnection();
            PreparedStatement pstmt
                    = conn.prepareStatement("select * from people");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String age = rs.getString("age");
                People temp = new People(name,age);
                al.add(temp);
            }
            pstmt = conn.prepareStatement("select * from products");
            rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                int idx = rs.getInt("idx");
                Product p = new Product(idx,name,price,quantity);
                products.add(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn != null ) try{ conn.close(); }catch (Exception e){}
        }
 */
        model.addAttribute("al",peopleRepository.doSelect());
        model.addAttribute("products",productsRepository.doSelect());
        model.addAttribute("posts",postsRepository.doSelect());
        return "index";
    }

    @PostMapping("/post")
    public String post(String content, HttpServletRequest request){
//        String request.getParameter("content")
        System.out.println("일로오나");
        System.out.println(content);
        postsRepository.doInsert(content);
        return "redirect:/";
    }
}
