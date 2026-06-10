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
        return jdbcClient.sql("SELECT id, title, contents FROM blogs")
                .query(Blog.class)
                .list();
    }

    // キーワード検索
    public List<Blog> searchByKeyword(String keyword) {
        return jdbcClient.sql("SELECT id, title, contents FROM blogs WHERE title LIKE :keyword")
                .param("keyword", "%" + keyword + "%")
                .query(Blog.class)
                .list();
    }

    // 投稿
    public void register(BlogForm blogForm) {
        jdbcClient.sql("INSERT INTO blogs (title, contents) VALUES (:title, :contents)")
                .param("title", blogForm.getTitle())
                .param("contents", blogForm.getContents())
                .update();
    }

    // // 各ブログへのアクセス
    // public List<Blog> findByTitle(BlogForm blogForm){
    //     return jdbcClient.sql("SELECT id, title, contents FROM blogs WHERE title = :title")
    //             .param("title", blogForm.getTitle())
    //             .query(Blog.class)
    //             .list();
    // }

    //各ブログへのアクセス
    public Optional<Blog> findById(Long id) {
        return jdbcClient.sql("SELECT id, title, contents FROM blogs WHERE id = :id")
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

    // ブログの更新
    public void update(Long id, String title, String contents){
        jdbcClient.sql("UPDATE blogs SET title = :title, contents = :contents WHERE id = :id")
                .param("title", title)
                .param("contents", contents)
                .param("id", id)
                .update();
    }
}
