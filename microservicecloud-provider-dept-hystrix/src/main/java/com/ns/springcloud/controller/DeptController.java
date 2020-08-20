package com.ns.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ns.springcloud.service.DeptService;
import com.ns.springcloud.entity.Dept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "部门模块", description = "部门", tags = "部门啊")
@RestController
public class DeptController {
    @Autowired
    private DeptService service;

     @ApiOperation(value = "添加用户", notes = "add")
     @RequestMapping(value = "/dept/add" ,method = RequestMethod.PUT)
     public Boolean add(Dept dept){
        return service.add(dept);
     }


    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @ApiOperation(value = "获取某个用户信息", notes = "get")
    @RequestMapping(value = "/dept/get/{id}" ,method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "defautCallMethod")
    public Dept get(@PathVariable("id")  long id){
        Dept dept = this.service.get(id);
        if (null == dept) {
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }
        return dept;
    }

    @ApiOperation(value = "获取所有用户信息", notes = "list")
    @RequestMapping(value = "/dept/list" ,method = RequestMethod.GET)
    public List<Dept> list(){
        return service.list();
    }


    public Dept defautCallMethod(@PathVariable("id") long id){
        return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

    public Dept processHystrix_Get(@PathVariable("id") long id)
    {
        return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

}
