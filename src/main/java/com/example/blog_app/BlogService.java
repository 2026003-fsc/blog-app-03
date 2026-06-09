package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public List<Blog> findAll(){
        return blogRepository.findall();
    }

    // 検索のビジネスロジック
    public List<Blog> search(String keyword){
        if(keyword == null || keyword.isBlank()){
            return blogRepository.findall();
        }
        return blogRepository.searchByKeyword(keyword);
    }

    public void register(Blog post){
        blogRepository.register(new Blog(post.getId(), post.getTitle(), post.getContent()));
    }

    public Optional<Blog> findById(Long id){
        return blogRepository.findById(id);
    }
}
