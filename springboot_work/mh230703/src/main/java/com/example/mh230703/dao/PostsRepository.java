package com.example.mh230703.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class PostsRepository {

    @Autowired
    DataSource dataSource;

    public void doInsert(String content){
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "insert into posts (content) values (?)"
                    );
            pstmt.setString(1, content);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(conn != null) try{ conn.close(); }catch (Exception e){}
        }
    }
}
