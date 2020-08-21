package com.ns.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@EnableHystrixDashboard
@SpringBootApplication
public class Consumer_Hystrix_DashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(Consumer_Hystrix_DashboardApplication.class,args);
    }
}
