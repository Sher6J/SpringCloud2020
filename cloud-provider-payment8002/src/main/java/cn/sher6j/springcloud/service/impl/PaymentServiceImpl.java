package cn.sher6j.springcloud.service.impl;

import cn.sher6j.springcloud.dao.PaymentDao;
import cn.sher6j.springcloud.entities.Payment;
import cn.sher6j.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sher6j
 * @create 2020-05-19-13:40
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired //也可以用java自带的@Resource替代
    private PaymentDao paymentDao;

    /**
     * 写操作
     * @param payment
     * @return
     */
    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
