package com.chen.blog.web;

import com.chen.blog.service.BlogService;
import com.chen.blog.service.TagService;
import com.chen.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 陈奕成
 * @create 2020 05 09 13:25
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(@PageableDefault(size = 4,sort = {"updateTime"},direction = Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(4));
        model.addAttribute("tags",tagService.listTagTop(7));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(4));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){

        model.addAttribute("blog",blogService.getAndConvert(id));
        System.out.println("----------");
        return "blog";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 4,sort = {"updateTime"},direction = Direction.DESC)Pageable pageable,
                         @RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/footer/newblog")
    public String newBlogList(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));
        System.out.println("1");
        return "_fragments :: newbloglist";
    }
}
