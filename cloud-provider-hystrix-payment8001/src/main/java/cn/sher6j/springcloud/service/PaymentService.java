package cn.sher6j.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * 应该要先写接口，再写实现类，这里为了节约时间，直接用实现类了
 * @author sher6j
 * @create 2020-05-21-17:29
 */
@Service
public class PaymentService {
    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Long id) {
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id;
    }

    /**
     * 模拟复杂业务需要3秒钟
     * HystrixCommand配置2秒以内走正常的逻辑，超过2秒走服务降级逻辑
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String paymentInfo_TimeOut(Long id) {
        int time = 3;
        //暂停几秒钟线程，程序本身没有错误，就是模拟超时
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_TimeOut, id: " + id;
    }

    /**
     * 兜底方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Long id) {
        return "系统忙，请稍后再试";
    }

    //======服务熔断

    /**
     * fallbackMethod                               服务降级方法
     * circuitBreaker.enabled                       是否开启断路器   默认为true
     * circuitBreaker.requestVolumeThreshold        请求次数    默认20
     * circuitBreaker.sleepWindowInMilliseconds     时间窗口期  默认5000
     * circuitBreaker.errorThresholdPercentage      失败率达到多少后跳闸 默认50
     * 以下配置意思是在10秒时间内请求10次，如果有6此是失败的，就触发熔断器
     * 注解@HystrixProperty中的属性在com.netflix.hystrix.HystrixCommandProperties类中查看
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Long id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + " 调用成功，流水号： " + serialNumber;
    }

    /**
     * 服务熔断触发的服务降级方法
     * @param id
     * @return
     */
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Long id) {
        return "id 不能为负数，请稍后再试。id:" + id;
    }
}
