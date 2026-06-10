package com.example.blog_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BlogController {

    private final BlogService blogService;

    BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // 一覧とキーワード検索
    @GetMapping("/blogs")
    public String allBlogs(@RequestParam(required = false, defaultValue = "") String keyword, Model model) {
        List<Blog> blogs = blogService.search(keyword);
        model.addAttribute("blogs", blogs);
        model.addAttribute("keyword", keyword);
        return "blogs";
    }

    @PostMapping("/blogs")
    public String blog(@ModelAttribute BlogForm blogForm, Model model){
        blogService.register(blogForm);
        model.addAttribute("blogs", blogService.findByTitle(blogForm));
        return "blogs";
    }

    // 各ブログの詳細表示
    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blogOpt.get());
        return "detail";
    }


    // 投稿フォーム
    @GetMapping("/post")
    public String newBlog(){
        return "/post";
    }

    // // 投稿処理
    // @PostMapping("/post")
    // public String post(@ModelAttribute BlogForm blogForm, RedirectAttributes redirectAttributes){
    //     blogService.register(blogForm);
    //     redirectAttributes.addFlashAttribute("message", "「" + blogForm.getTitle() + "」を投稿しました");
    //     return "redirect:/blogs";
    // }

    // 削除処理
    

}
