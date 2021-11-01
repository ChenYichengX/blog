package com.chen.blog;

import com.chen.blog.util.EsUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
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

    String index = "blog_logs";

    String type = "man";

    RestHighLevelClient client = getClient();

    public static RestHighLevelClient getClient(){

        // 创建 HttpHost 对象
        HttpHost host = new HttpHost("121.36.1.142",9200);


        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "Chen516127.0"));

        // 创建 RestClientBuilder
        RestClientBuilder builder = RestClient.builder(host).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(-1);
                requestConfigBuilder.setSocketTimeout(-1);
                requestConfigBuilder.setConnectionRequestTimeout(-1);
                return requestConfigBuilder;
            }
        }).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.disableAuthCaching();
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        });

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
                .startObject("serverName").field("type", "keyword").endObject()
                .startObject("module").field("type", "keyword").endObject()
                .startObject("action").field("type", "keyword").endObject()
                .startObject("userName").field("type", "text").endObject()
                .startObject("createTime").field("type", "date").endObject()
                .startObject("data").field("type", "text").field("analyzer", "ik_max_word").endObject()
                .startObject("resultCode").field("type", "text").endObject()
                .startObject("ip").field("type", "ip").endObject()
                .startObject("browswer").field("type", "keyword").endObject()
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
