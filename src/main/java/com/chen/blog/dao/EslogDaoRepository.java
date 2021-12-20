package com.chen.blog.dao;

import com.chen.blog.entity.EsLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName EslogDaoRepository
 * @Author ChenYicheng
 * @Description es_log_Dao
 * @Date 2021/12/20 11:23
 */
public interface EslogDaoRepository extends JpaRepository<EsLog,String> {
}
