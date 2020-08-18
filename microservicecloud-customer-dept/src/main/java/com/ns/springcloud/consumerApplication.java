package com.ns.springcloud;

import com.netflix.loadbalancer.IRule;
import com.ns.myrule.RandomRule_My;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@Configuration
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
public class consumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(consumerApplication.class,args);

    }

    @Bean
    @LoadBalanced//SpringCloud ribbon是基于Netfix Ribbon实现的一套客户端 负载均衡工具
    public RestTemplate restTemplate (){

        return  new RestTemplate();
    }

    @Bean
    public IRule MyRule(){
        return new RandomRule_My();
    }
}
