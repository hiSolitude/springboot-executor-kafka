package com.unicom.orderphotosyn.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaoyf on 2018/8/27
 *
 * 收到消息就存储数据库，不适用于数据量大的时候，有可能会把数据库压垮
 * 生产环境，多采用线程池的方式进行入库操作
 */
@Component
public class KafkaSenderTest {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送消息到kafka
     * topic ： springboot-kafka-test
     */
    public void sendTest() {
        kafkaTemplate.send("springboot-kafka-test", "hello springboot-kafka-test " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
