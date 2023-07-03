package com.example.mh230703.controller;

import com.example.mh230703.dto.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Controller
public class AController {

    @Autowired
    DataSource ds;

    @GetMapping("/")
    public String index(Model model){
        ArrayList<People> al = new ArrayList<>();
        try{
            Connection conn = ds.getConnection();
            PreparedStatement pstmt
                    = conn.prepareStatement("select * from people");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String age = rs.getString("age");
                People temp = new People(name,age);
//                temp.setAge(age);
//                temp.setName(name);
                al.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("al",al);
        return "index";
    }
}
