package com.chen.blog.service.impl;

import com.chen.blog.dao.CommentRepository;
import com.chen.blog.entity.Comment;
import com.chen.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.transform.Templates;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 05 13 21:59
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Direction.ASC,"createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if(parentCommentId != -1){
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        } else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    /**
     * 循环每个顶级的评论
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentView = new ArrayList<>();
        for(Comment comment : comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentView.add(c);
        }
        //合并评论的各层 子代 到第一级 子代集合中
        combineChildren(commentView);
        return commentView;
    }
    //存放迭代找出所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * root 根节点，blog不为空的对像集合
     * @param comments
     */
    private void combineChildren(List<Comment> comments){
        for(Comment comment : comments){
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1){
                //循环迭代，找出子代，存放在 tempReplys 中
                recursively(reply1);
            }
            //修改顶级节点的 reply 集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    /**
     * 递归迭代，剥洋葱
     * @param comment
     */
    private void recursively(Comment comment){
        tempReplys.add(comment);//顶级节点存放到临时存放集合
        if(comment.getReplyComments().size()>0){
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply : replys){
                tempReplys.add(reply);
                if(reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }
}
