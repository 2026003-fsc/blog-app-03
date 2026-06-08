package com.example.blog_app;

public class Post {
    private final int id;
    private final String title;
    private final String content;

    public Post(int id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
