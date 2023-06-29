package com.example.demo.conf;

import com.example.demo.obj.AA;
import com.example.demo.obj.BB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
개발환경 설정
 */
@Configuration
public class MyConfiguraion {

    @Bean
    public AA aa(){
        return new AA("존");
    }

    @Bean
    public BB bb(){
        return new BB();
    }
}