package cn.sher6j.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sher6j
 * @create 2020-05-20-17:49
 */
@SpringBootApplication
@EnableDiscoveryClient //用于向使用Consul或Zookeeper作为注册中心时注册服务
public class OrderConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain80.class);
    }
}
