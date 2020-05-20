package cn.sher6j.springcloud;

        import cn.sher6j.myrule.MySelfRule;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
        import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author sher6j
 * @create 2020-05-19-18:49
 */
@SpringBootApplication
@EnableEurekaClient
//访问的微服务为CLOUD-PAYMENT-SERVICE，采用配置文件中的轮询算法
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class);
    }
}
