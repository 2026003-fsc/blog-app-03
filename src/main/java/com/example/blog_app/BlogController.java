package com.example.blog_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class BlogController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/post")
    public String post(){
        return "post";
    }

    

}
