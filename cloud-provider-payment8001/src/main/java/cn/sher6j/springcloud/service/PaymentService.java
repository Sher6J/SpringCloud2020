package cn.sher6j.springcloud.service;

import cn.sher6j.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author sher6j
 * @create 2020-05-19-13:39
 */
public interface PaymentService {
    /**
     * 写操作
     * @param payment
     * @return
     */
    public int create(Payment payment);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Payment getPaymentById(@Param("id") Long id);
}
