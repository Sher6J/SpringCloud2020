package cn.sher6j.springcloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sher6j
 * @create 2020-05-26-9:59
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacor/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "Nacos服务注册，端口：" + serverPort + "；id：" + id;
    }
}
