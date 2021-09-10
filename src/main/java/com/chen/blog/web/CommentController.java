package com.chen.blog.web;

import com.chen.blog.entity.Comment;
import com.chen.blog.entity.User;
import com.chen.blog.service.BlogService;
import com.chen.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.servlet.http.HttpSession;

/**
 * @author 陈奕成
 * @create 2020 05 13 21:50
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String submitPost(Comment comment, HttpSession session){
        comment.setBlog(blogService.getBlog(comment.getBlog().getId()));
        User user = (User) session.getAttribute("user");
        if(user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
        }

        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlog().getId();
    }
}