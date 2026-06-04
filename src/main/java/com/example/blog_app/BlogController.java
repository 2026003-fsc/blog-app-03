package com.example.blog_app;

import org.springframework.jdbc.core.simple.JdbcClient;

public class BlogController {
    private final JdbcClient jdbcClient;

    public BlogController(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    
}
