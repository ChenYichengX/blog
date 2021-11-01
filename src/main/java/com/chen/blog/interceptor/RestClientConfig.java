package com.chen.blog.interceptor;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.http.HttpHeaders;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName ElasticsearchConfig
 * @Author ChenYicheng
 * @Description es 配置
 * @Date 2021/11/1 15:11
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Value("elasticsearch.ipAddr")
    private String ipAddr;

    @Value("elasticsearch.port")
    private String port;

    @Value("elasticsearch.username")
    private String username;

    @Value("elasticsearch.password")
    private String password;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(ipAddr + ":" + port)
                .withBasicAuth(username,password)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

//    @Bean
//    public ElasticsearchConverter elasticsearchConverter() {
//        return new MappingElasticsearchConverter(elasticsearchMappingContext());
//    }
//
//
//    @Bean
//    public ReactiveElasticsearchOperations reactiveElasticsearchOperations() {
//        return new ReactiveElasticsearchTemplate(reactiveElasticsearchClient(), elasticsearchConverter());
//    }

//    @Bean
//    @Override
//    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.add("some-header", "on every request");
//
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo(ipAddr + port)
//                //.useSsl()
//                //.withProxy("localhost:8888")
//                //.withPathPrefix("ela")
//                .withConnectTimeout(Duration.ofSeconds(5))
//                .withSocketTimeout(Duration.ofSeconds(3))
//                //.withDefaultHeaders(defaultHeaders)
//                .withBasicAuth(username, password)
//                .withHeaders(() -> {
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.add("currentTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//                    return headers;
//                })
//                .build();
//        return ReactiveRestClients.create(clientConfiguration);
//    }
}
