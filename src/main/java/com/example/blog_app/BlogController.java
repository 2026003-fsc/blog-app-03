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

    // @PostMapping("/blogs")
    // public String blog(@ModelAttribute BlogForm blogForm, Model model){
    //     blogService.register(blogForm);
    //     model.addAttribute("blogs", blogService.findByTitle(blogForm));
    //     return "blogs";
    // }

    // 各ブログの詳細表示
    @GetMapping("/blogs/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blogOpt.get());
        return "blogs/detail";
    }

    
    // 投稿フォーム
    @GetMapping("/blogs/post")
    public String newBlog(){
        return "blogs/post";
    }

    // 投稿処理
    @PostMapping("/blogs")
    public String post(@ModelAttribute BlogForm blogForm, RedirectAttributes redirectAttributes){
        blogService.register(blogForm);
        redirectAttributes.addFlashAttribute("message", "「" + blogForm.getTitle() + "」を投稿しました");
        return "redirect:/blogs";
    }


    // 編集フォームの表示
    @GetMapping("/blogs/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        Optional<Blog> blogOpt = blogService.findById(id);
        if(blogOpt.isEmpty()){
            return "redirect:/blogs";
        }
        Blog blog = blogOpt.get();
        
        model.addAttribute("blogForm", new BlogForm(blog.getTitle(), blog.getContents()));
        model.addAttribute("blogId", id);
        return "blogs/edit";
    }

    // ブログの削除
    @PostMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        blogService.delete(id);
        redirectAttributes.addFlashAttribute("message", "ブログを削除しました");
        return "redirect:/blogs";
    }

    // ブログの編集
    @PostMapping("/blogs/{id}")
    public String update(@PathVariable Long id, @ModelAttribute BlogForm form){
        blogService.update(id, form);
        return "redirect:/blogs";
    }


    // サイト紹介
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    // プロフィール
    @GetMapping("/profile")
    public String introduce(){
        return "profile";
    }

}
