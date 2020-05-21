package cn.sher6j.springcloud.controller;

import cn.sher6j.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sher6j
 * @create 2020-05-21-17:34
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("========result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("========result:" + result);
        return result;
    }


    //=====服务熔断
    @GetMapping("payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("=========result:" + result);
        return result;
    }
}
