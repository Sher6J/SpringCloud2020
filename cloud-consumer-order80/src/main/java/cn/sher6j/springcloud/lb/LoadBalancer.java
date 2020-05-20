package cn.sher6j.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 负载均衡算法发接口
 * @author sher6j
 * @create 2020-05-20-22:23
 */
public interface LoadBalancer {
    /**
     * 从服务列表中用负载均衡算法选择出具体的实例
     * @param serviceInstances 服务列表
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
