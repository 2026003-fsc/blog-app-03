package com.example.blog_app;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    // 検索のビジネスロジック
    public List<Post> search(String keyword){
        if(keyword == null || keyword.isBlank()){
            return blogRepository.findall();
        }
        return blogRepository.searchByKeyword(keyword);
    }
}
