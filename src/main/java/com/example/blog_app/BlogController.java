package com.example.blog_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false) String keyword, Model model){
        model.addAttribute("blogs", blogService.search(keyword));
        return "blogs";
    }

}
