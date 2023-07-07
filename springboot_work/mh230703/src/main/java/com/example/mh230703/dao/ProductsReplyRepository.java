package com.example.mh230703.dao;

import com.example.mh230703.dto.Product;
import com.example.mh230703.dto.Product_reply;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsReplyRepository {

    @Autowired
    SqlSession sqlSession;

    public List<Product_reply> doSelect(Product_reply product_reply){
        return sqlSession.selectList("product_reply.select",product_reply);
    }

    public void doInsert(Product_reply product_reply) {
        sqlSession.insert("product_reply.insert", product_reply);
    }

    public Product_reply doSelectRow(Product_reply product_reply){
        return sqlSession.selectOne("product_reply.selectrow", product_reply);
    }

}
