package cn.dec30.dubbo.conusmer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 22:35
 * @description 消费者
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.dec30")
public class DubboConsumerBootMain {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerBootMain.class, args);
    }

}