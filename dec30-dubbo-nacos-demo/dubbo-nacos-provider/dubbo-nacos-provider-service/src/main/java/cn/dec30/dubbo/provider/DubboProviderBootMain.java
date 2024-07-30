package cn.dec30.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author medusakiller
 * @version 1.0
 * @date 2024/7/24 23:12
 * @description todo
 */
@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.dec30")
public class DubboProviderBootMain {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderBootMain.class, args);
    }

}
