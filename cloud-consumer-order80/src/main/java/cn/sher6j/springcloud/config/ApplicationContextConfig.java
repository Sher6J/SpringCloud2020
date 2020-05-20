package cn.sher6j.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author sher6j
 * @create 2020-05-19-18:54
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
//    @LoadBalanced//使用该注解赋予RestTemplate负载均衡的能力
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
//applicationContext.xml <bean id="" class="">
