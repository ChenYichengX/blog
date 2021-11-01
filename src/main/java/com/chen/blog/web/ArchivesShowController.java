package com.chen.blog.web;

import com.chen.blog.aspect.SystemLog;
import com.chen.blog.dao.BlogRepository;
import com.chen.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 陈奕成
 * @create 2020 05 14 12:25
 */
@Controller
public class ArchivesShowController {

    @Autowired
    private BlogService blogService;

    @SystemLog(serviceName = "blog服务", module = "首页模块", action = "访问'归档'页")
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archivesMap", blogService.archivesBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }
}
