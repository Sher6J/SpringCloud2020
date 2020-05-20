package cn.sher6j.springcloud.lb.impl;

import cn.sher6j.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sher6j
 * @create 2020-05-20-22:26
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        //自旋锁
        do {
            current = this.atomicInteger.get(); //初始值为0
            next = current >= 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("========访问次数next:" + next);
        return next;
    }

    /**
     * 从服务列表中用轮询负载均衡算法选择出具体的实例
     * Rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标
     * @param serviceInstances 服务列表
     * @return
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
