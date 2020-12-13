package com.rufeng.system;

import com.rufeng.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


@EnableRyFeignClients
@SpringCloudApplication
public class RufengSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RufengSystemApplication.class, args);
    }

}
