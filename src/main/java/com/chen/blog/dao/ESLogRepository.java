package com.chen.blog.dao;

import com.chen.blog.aspect.entity.ESLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName ESLogRepository
 * @Author ChenYicheng
 * @Description es repository
 * @Date 2021/11/1 16:00
 */
@Repository
public interface ESLogRepository extends ElasticsearchRepository<ESLog,String> {

}
