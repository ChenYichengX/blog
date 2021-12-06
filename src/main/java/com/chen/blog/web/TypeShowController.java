package com.chen.blog.web;

import com.chen.blog.aspect.SystemLog;
import com.chen.blog.entity.Blog;
import com.chen.blog.entity.Type;
import com.chen.blog.service.BlogService;
import com.chen.blog.service.TypeService;
import com.chen.blog.vo.BlogQuery;
import com.chen.blog.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈奕成
 *  !@create 2020 05 14 10:36
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @SystemLog(serviceName = "blog服务", module = "分类模块", action = "查看分类页、或查看某个分类的相关博客")
    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 4,sort = {"updateTime"},direction = Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){
        List<Type> types = typeService.listTypeTop(100);
        if(id == -1){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",types);
        Page<Blog> blogs = blogService.listBlog(pageable, blogQuery, null);
        model.addAttribute("page",blogs);
        List<PageResult> page = new ArrayList<>();
        if (blogs.getTotalPages() <= 4) {
            for (int i = 0; i < blogs.getTotalPages(); i++) {
                if (blogs.getNumber() == i) {
                    page.add(new PageResult(i + 1, true));
                } else {
                    page.add(new PageResult(i + 1, false));
                }
            }
        } else {
            int number = blogs.getNumber();
            if (number >= 2) {
                page.add(new PageResult(number - 1, false));
                page.add(new PageResult(number, false));
                page.add(new PageResult(number + 1, true));
                page.add(new PageResult(number + 2, false));
                page.add(new PageResult(number + 3, false));
            } else {
                for (int i = 0; i <= 4; i++) {
                    if (number == i) {
                        page.add(new PageResult(i + 1, true));
                    } else {
                        page.add(new PageResult(i + 1, false));
                    }
                }
            }
        }
        model.addAttribute("pageSize", page);
        model.addAttribute("activeTypeId",id);
        return "type";
    }
}
