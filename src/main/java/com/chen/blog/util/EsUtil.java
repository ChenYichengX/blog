package com.chen.blog.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName EsUtil
 * @Author ChenYicheng
 * @Description elasticsearch工具类：@Component默认是单例
 * @Date 2021/10/29 11:37
 */
//@Component
public class EsUtil {

    @Value("elasticsearch.ipAddr")
    private String ipAddr;

    @Autowired
    private RestHighLevelClient RestHighLevelClient;


    public RestHighLevelClient getClient(){

        // 创建 HttpHost 对象
        HttpHost host = new HttpHost(ipAddr,9200);

        // 创建 RestClientBuilder
        RestClientBuilder builder = RestClient.builder(host);

        // 创建 RestHighLevelClient
        RestHighLevelClient client = new RestHighLevelClient(builder);

        // 返回
        return client;
    }

}
