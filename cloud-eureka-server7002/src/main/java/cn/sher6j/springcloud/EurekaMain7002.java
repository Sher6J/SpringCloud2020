package cn.sher6j.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author sher6j
 * @create 2020-05-19-22:47
 */
@SpringBootApplication
@EnableEurekaServer//声明我是服务注册中心
public class EurekaMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class);
    }
}
