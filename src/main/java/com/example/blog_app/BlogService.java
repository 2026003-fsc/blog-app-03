package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> findAll() {
        return blogRepository.findall();
    }

    // 検索のビジネスロジック
    public List<Blog> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return blogRepository.findall();
        }
        return blogRepository.searchByKeyword(keyword);
    }

    public void register(BlogForm blogForm) {
        blogRepository.register(blogForm);
    }

    // public List<Blog> findByTitle(BlogForm blogForm) {
    //     return blogRepository.findByTitle(blogForm);
    // }

    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    public void update(Long id, BlogForm blogForm){
        blogRepository.update(id, blogForm.getTitle(), blogForm.getContents());
    }
}
