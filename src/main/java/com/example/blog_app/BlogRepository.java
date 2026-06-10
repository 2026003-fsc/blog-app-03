package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
    private final JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    // ブログの全件表示（一覧）
    public List<Blog> findall() {
        return jdbcClient.sql("SELECT id, title, content FROM blogs")
                .query(Blog.class)
                .list();
    }

    // キーワード検索
    public List<Blog> searchByKeyword(String keyword) {
        return jdbcClient.sql("SELECT id, title, content FROM blogs WHERE content LIKE :keyword")
                .param("keyword", "%" + keyword + "%")
                .query(Blog.class)
                .list();
    }

    // 投稿
    public void register(BlogForm blogForm) {
        jdbcClient.sql("INSERT INTO blogs (title, content) VALUES (:title, :content)")
                .param("title", blogForm.getTitle())
                .param("content", blogForm.getContent())
                .update();
    }

    // 各ブログへのアクセス
    public List<Blog> findByTitle(BlogForm blogForm){
        return jdbcClient.sql("SELECT id, title, content FROM blogs WHERE title = :title")
                .param("title", blogForm.getTitle())
                .query(Blog.class)
                .list();
    }

    //各ブログへのアクセス
    public Optional<Blog> findById(Long id) {
        return jdbcClient.sql("SELECT id, title, content FROM blogs WHERE id = :id")
                .param("id", id)
                .query(Blog.class)
                .optional();
    }

    // ブログの削除
    public void deleteById(Long id) {
        jdbcClient.sql("DELETE FROM blogs WHERE id = :id")
                .param("id", id)
                .update();
    }
}
