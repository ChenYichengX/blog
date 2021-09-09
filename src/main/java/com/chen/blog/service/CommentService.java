package com.chen.blog.service;

import com.chen.blog.entity.Comment;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 05 13 21:57
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
