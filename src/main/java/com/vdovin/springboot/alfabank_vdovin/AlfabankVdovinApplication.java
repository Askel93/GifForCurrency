package com.vdovin.springboot.alfabank_vdovin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlfabankVdovinApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlfabankVdovinApplication.class, args);
    }

}
