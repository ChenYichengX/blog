package com.chen.blog.util;


/**
 * @ClassName EsUtil
 * @Author ChenYicheng
 * @Description elasticsearch工具类：@Component默认是单例
 * @Date 2021/10/29 11:37
 */
//@Component
public class EsUtil {

//    @Value("elasticsearch.ipAddr")
//    private String ipAddr;
//
//    @Value("elasticsearch.username")
//    private String username;
//
//    @Value("elasticsearch.password")
//    private String password;

//    @Autowired
//    private RestHighLevelClient RestHighLevelClient;


    /*public RestHighLevelClient getClient(){

        // 创建 HttpHost 对象
        HttpHost host = new HttpHost(ipAddr,9200);


        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

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
    }*/

}
