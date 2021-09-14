package com.chen.blog.dao;

import com.chen.blog.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName PictureRepository
 * @Author ChenYicheng
 * @Description 图片的repository
 * @Date 2021/9/14 19:41
 */
public interface PictureRepository extends JpaRepository<Picture,Long> {
}
