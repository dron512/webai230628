package com.example.mh230703.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
//interface InterA{
//    int a = 10;
//}
//class A implements InterA{}
@Configuration
public class MyConf {
//    public void doA(){
//        InterA aa = new A();
//    }
//    public void doB(){
//        Object a = new String();
//        Object b = new Integer(10);
//    }

    @Bean
    public DataSource dataSource(){
        BasicDataSource dbs = new BasicDataSource();
        dbs.setUrl("jdbc:mysql://localhost:3306/mhpark");
        dbs.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbs.setUsername("root");
        dbs.setPassword("1234");
        dbs.setInitialSize(10);
        return dbs;
    }
}
