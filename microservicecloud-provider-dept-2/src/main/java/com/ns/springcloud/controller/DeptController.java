package com.ns.springcloud.controller;

import com.ns.springcloud.entity.Dept;
import com.ns.springcloud.service.DeptService;
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
     @RequestMapping(value = "/dept/add" ,method = RequestMethod.POST)
     public Boolean add(@RequestBody Dept dept){

        return service.add(dept);
     }

    @ApiOperation(value = "获取某个用户信息", notes = "get")
    @RequestMapping(value = "/dept/get/{id}" ,method = RequestMethod.GET)
    public Dept get(@PathVariable("id")  Long id){
        return service.get(id);
    }

    @ApiOperation(value = "获取所有用户信息", notes = "list")
    @RequestMapping(value = "/dept/list" ,method = RequestMethod.GET)
    public List<Dept> list(){
        return service.list();
    }


}
