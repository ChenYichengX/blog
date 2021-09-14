package com.chen.blog.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Picture
 * @Author ChenYicheng
 * @Description 图片信息info
 * @Date 2021/9/14 19:34
 */
@Entity
@Table(name = "t_picture")
public class Picture {
    @Id
    @GeneratedValue
    private Long id;
    
    /** 图片原名称-ChenYicheng-2021/9/14 19:36 */
    private String pictureName; 
    
    /** 图片真实路径-ChenYicheng-2021/9/14 19:37 */
    private String realPath;
    
    /** 创建时间-ChenYicheng-2021/9/14 19:39 */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
