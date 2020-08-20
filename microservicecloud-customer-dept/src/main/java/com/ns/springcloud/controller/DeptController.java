package com.ns.springcloud.controller;

import com.ns.springcloud.entity.Dept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Api(value = "消费者" ,description = "消费者服务",tags ="consumer....")
@RestController
public class DeptController {

    @Autowired
    private DiscoveryClient client;
    
//    private static final  String REST_URL_PREFIX="http://localhost:8001";
    private static final  String REST_URL_PREFIX="http://MICROSERVICECLOUD-DEPT";

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "添加用户", notes = "add")
    @PostMapping("/consumer/dept/add")
    public  Boolean add(@RequestBody Dept dept){

        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add/", dept, Boolean.class);//url,request,type
    }

    @ApiOperation(value = "获取某个用户", notes = "get")
    @GetMapping("/consumer/dept/get/{id}")
    public  Dept get(@PathVariable("id") String id){

        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/"+id, Dept.class);//url,request,type
    }

    @ApiOperation(value = "获取全部用户", notes = "list")
    @GetMapping("/consumer/dept/list")
    public List<Dept> add(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);//url,request,type
    }
    
    
    @GetMapping("/consumer/dept/descory/{id}")
    public Dept  getById(@PathVariable("id") String id){
        List<String> services = client.getServices();
        System.out.println("servers:"+services);
        Dept dept=null;
        List<ServiceInstance> instances = client.getInstances("microservicecloud-dept");
        for (ServiceInstance instance : instances) {
            URI uri = instance.getUri();
            String serviceId = instance.getServiceId();
            System.out.println(uri+"/"+serviceId);
            dept = restTemplate.getForObject(uri + "/dept/get/" + id, Dept.class);
        }
        return dept;
    }

}
