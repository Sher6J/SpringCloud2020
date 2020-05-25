package cn.sher6j.sprincloud.service.impl;

import cn.sher6j.sprincloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 发送消息接口实现类
 * @author sher6j
 * @create 2020-05-25-12:21
 */
//@Service//此注解不再需要了，因为这个service不再是传统意义上的和Controller、DAO数据等进行交互的service，而是要绑定绑定器打交道的service
@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("========serial:" + serial);
        return null;
    }
}
