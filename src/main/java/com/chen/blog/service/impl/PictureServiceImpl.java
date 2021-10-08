package com.chen.blog.service.impl;

import com.chen.blog.dao.PictureRepository;
import com.chen.blog.entity.Picture;
import com.chen.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;


/**
 * @ClassName PictureServiceImpl
 * @Author ChenYicheng
 * @Description 图片ServiceImpl
 * @Date 2021/9/14 19:44
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Transactional
    @Override
    public Picture savePicture(Picture picture) {
        picture.setCreateTime(new Date());
        return pictureRepository.save(picture);
    }
}
