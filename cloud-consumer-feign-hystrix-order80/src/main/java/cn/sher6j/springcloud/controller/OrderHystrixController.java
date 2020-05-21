package cn.sher6j.springcloud.controller;

import cn.sher6j.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sher6j
 * @create 2020-05-21-20:02
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

//    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id) {
//        int i = 1/0; //手动模拟错误
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    public String paymentInfo_TimeOut(@PathVariable("id") Long id) {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    /**
     * 定制服务降级方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Long id) {
        return "定制异常处理信息";
    }

    /**
     * 全局服务降级方法
     * @return
     */
    public String payment_Global_FallbackMethod() {
        return "全局异常处理信息";
    }
}
