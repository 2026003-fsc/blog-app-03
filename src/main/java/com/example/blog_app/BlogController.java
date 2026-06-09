package com.example.blog_app;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BlogController {

    private final BlogService blogService;

    BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/post")
    public String post(){
        return "post";
    }

    // 一覧とキーワード検索
    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false,defaultValue = "") String keyword, Model model){
        List<Blog> blog = blogService.search(keyword);
        model.addAttribute("blogs", blogService.findAll());
        model.addAttribute("blogs", blogService.search(keyword));
        return "blogs";
    }

    // 各ブログの詳細表示
    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable Long id, Model model){
        return blogService.findById()
    }

}
