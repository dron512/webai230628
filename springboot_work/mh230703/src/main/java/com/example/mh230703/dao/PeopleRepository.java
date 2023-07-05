package com.example.mh230703.dao;

import com.example.mh230703.dto.People;
import com.example.mh230703.dto.Product;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeopleRepository {

    @Autowired
    SqlSession sqlSession;

    public List<People> doSelect(){
        return sqlSession.selectList("people.select");
    }

}
