package cn.sher6j.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author sher6j
 * @create 2020-05-21-21:27
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Long id) {
        return "paymentInfo_OK出现异常";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        return "paymentInfo_TimeOut出现异常";
    }
}
