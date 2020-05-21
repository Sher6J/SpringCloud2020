package cn.sher6j.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
