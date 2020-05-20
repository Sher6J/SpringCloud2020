package cn.sher6j.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author sher6j
 * @create 2020-05-19-21:04
 */
@SpringBootApplication
@EnableEurekaServer//声明我是服务注册中心
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class);
    }
}
