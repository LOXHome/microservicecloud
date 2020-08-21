package com.ns.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaServer//eureka服务启动类，接收其他服务注册
public class Eureka7002Application {

    public static void main(String[] args) {
        Map map=new HashMap();
        SpringApplication.run(Eureka7002Application.class,args);
    }
}
