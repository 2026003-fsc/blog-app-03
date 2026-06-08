package com.example.blog_app;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
    private final JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    // ブログの全件表示（一覧）
    public List<Post> findall(){
        return jdbcClient.sql("SELECT title, content FROM blogs")
                .query(Post.class)
                .list();
    }


    // キーワード検索
    public List<Post> searchByKeyword(String keyword){
        return jdbcClient.sql("SELECT title, content FROM blogs WHERE title LIKE :keyword")
                .param("keyword", "%" + keyword + "%")
                .query(Post.class)
                .list();
    }
}
