package com.unicom.orderphotosyn.producer;

import com.unicom.orderphotosyn.config.KafkaConsumerConfig;
import com.unicom.orderphotosyn.consumer.ConsumerGroup;
import com.unicom.orderphotosyn.consumer.KafkaConsumerThread;
import com.unicom.orderphotosyn.threadPool.KafkaConsumerPool;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zhaoyf on 2018/9/5
 */
@Component
public class KafkaSender {

    @Resource
    KafkaConsumerPool consumerPool;

    @Resource
    KafkaConsumerConfig consumerConfig;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 这里需要放到程序启动完成之后执行
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行。
     */
    @PostConstruct
    void d() {

        ConsumerGroup consumerThread = new ConsumerGroup("group-1", "access_data", consumerConfig);
        ConsumerGroup consumerThread2 = new ConsumerGroup("group-2", "access_data", consumerConfig);

        /**
         * 各起两个消费者 ,Kafka consumer是非线程安全的 Consumer 需要一个new 的
         */
        consumerPool.SubmitConsumerPool(new KafkaConsumerThread(consumerThread));
        consumerPool.SubmitConsumerPool(new KafkaConsumerThread(consumerThread));

        consumerPool.SubmitConsumerPool(new KafkaConsumerThread(consumerThread2));
        consumerPool.SubmitConsumerPool(new KafkaConsumerThread(consumerThread2));
    }

    /**
     * 发送消息到kafka
     */
    public void sendTest() throws InterruptedException, IOException, KeeperException {

        /**
         * topic='access_data'
         */
        kafkaTemplate.send("access_data", "" + System.currentTimeMillis());
        kafkaTemplate.send("access_data", "" + System.currentTimeMillis());
        kafkaTemplate.send("access_data", "" + System.currentTimeMillis());
        kafkaTemplate.send("access_data", "" + System.currentTimeMillis());
        kafkaTemplate.send("access_data", "" + System.currentTimeMillis());
        kafkaTemplate.send("access_data", "" + System.currentTimeMillis());
    }


}