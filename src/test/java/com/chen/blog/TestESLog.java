package com.chen.blog;

import com.chen.blog.util.EsUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.jupiter.api.Test;

/**
 * @ClassName TestESLog
 * @Author ChenYicheng
 * @Description 创建Eslog索引结构
 * @Date 2021/10/29 16:19
 */
public class TestESLog {

    String index = "person";

    String type = "man";

    RestHighLevelClient client = getClient();

    public static RestHighLevelClient getClient(){

        // 创建 HttpHost 对象
        HttpHost host = new HttpHost("121.36.1.142",9200);

        // 创建 RestClientBuilder
        RestClientBuilder builder = RestClient.builder(host);

        // 创建 RestHighLevelClient
        RestHighLevelClient client = new RestHighLevelClient(builder);

        // 返回
        return client;
    }

    /**
     * @Author ChenYicheng
     * @Description 创建索引，类型
     * @Date 2021/10/29 16:21
     */
    @Test
    public void prepare() throws Exception{

        // 1、Settings
        Settings.Builder put = Settings.builder()
                .put("number_of_shards", 3) // 分片数
                .put("number_of_replicas", 1);// 备份数

        // 2、mappings
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                .startObject("properties")
                .startObject("createDate").field("type", "date").endObject()
                .startObject("sendDate").field("type", "date").endObject()
                .startObject("longCode").field("type", "keyword").endObject()
                .startObject("mobile").field("type", "keyword").endObject()
                .startObject("corpName").field("type", "keyword").endObject()
                .startObject("smsContent").field("type", "text").field("analyzer", "ik_max_word").endObject()
                .startObject("state").field("type", "integer").endObject()
                .startObject("operatorId").field("type", "integer").endObject()
                .startObject("province").field("type", "keyword").endObject()
                .startObject("ipAddr").field("type", "ip").endObject()
                .startObject("replyTotal").field("type", "integer").endObject()
                .startObject("fee").field("type", "long").endObject()
                .endObject()
                .endObject();

        // 3、添加索引
        CreateIndexRequest request = new CreateIndexRequest(index)
                .settings(put)
                .mapping(type, mappings);

        client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("ok!");
    }
}
