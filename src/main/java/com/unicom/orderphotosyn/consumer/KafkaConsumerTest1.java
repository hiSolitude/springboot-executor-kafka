package com.unicom.orderphotosyn.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyf on 2018/8/27
 */
@Component
public class KafkaConsumerTest1 {

    private static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    private static final String TOPIC = "springboot-kafka-test";

    /**
     * 监听 kafka-springboot-test
     *
     * @param message
     */
    @KafkaListener(topics = {TOPIC})
    public void consumer(String message) {
        log.info("test topic message : {}", message);
    }


}
