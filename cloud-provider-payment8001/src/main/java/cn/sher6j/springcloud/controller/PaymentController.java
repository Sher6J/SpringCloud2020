package cn.sher6j.springcloud.controller;

import cn.sher6j.springcloud.entities.CommonResult;
import cn.sher6j.springcloud.entities.Payment;
import cn.sher6j.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sher6j
 * @create 2020-05-19-13:43
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 插入payment
     * @param payment
     * @return
     */
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("=======插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功, 端口号：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("=======查询结果：" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询数据库成功, 端口号：" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询ID：" + id, null);
        }
    }

    /**
     * 服务发现
     * @return
     */
    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices(); //获取服务列表的信息
        for (String service : services) {
            log.info("=======service:" + service + "=======");
        }

        //根据微服务名称获取具体服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("=======" + instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri() + "=======");
        }

        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
