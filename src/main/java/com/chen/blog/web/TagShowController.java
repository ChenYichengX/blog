package com.chen.blog.web;

import com.chen.blog.aspect.SystemLog;
import com.chen.blog.entity.Tag;
import com.chen.blog.service.BlogService;
import com.chen.blog.service.TagService;
import com.chen.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 05 14 10:36
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @SystemLog(serviceName = "blog服务", module = "标签模块", action = "查看标签页、或查看某个标签的相关博客")
    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 4,sort = {"updateTime"},direction = Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){
        List<Tag> tags = tagService.listTagTop(100);
        if(id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
