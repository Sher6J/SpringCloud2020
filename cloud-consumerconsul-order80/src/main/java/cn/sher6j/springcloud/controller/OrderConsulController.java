package cn.sher6j.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author sher6j
 * @create 2020-05-20-17:52
 */
@RestController
@Slf4j
public class OrderConsulController {

    //要访问的服务提供方的微服务名称
    public static final String INVOKE_URL = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        String re = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return re;
    }
}
