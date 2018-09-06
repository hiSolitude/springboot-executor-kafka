package com.unicom.orderphotosyn;

import com.unicom.orderphotosyn.producer.KafkaSender;
import com.unicom.orderphotosyn.producer.KafkaSenderTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
public class OrderPhotoSynApplication {

    @Autowired
    private KafkaSender kafkaSender;


    public static void main(String[] args) {
        SpringApplication.run(OrderPhotoSynApplication.class, args);
    }

    @Autowired
    private KafkaSenderTest kafkaSenderTest;

   /* //然后每隔1分钟执行一次
    //不使用线程池消费模式
    @Scheduled(fixedRate = 1000 * 60)
    public void testKafka1() {
        try {
            kafkaSenderTest.sendTest();
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    //然后每隔1分钟执行一次
    //线程池消费模式
    @Scheduled(fixedRate = 1000 * 60)
    public void testKafka2() throws Exception {
        kafkaSender.sendTest();
    }
}
