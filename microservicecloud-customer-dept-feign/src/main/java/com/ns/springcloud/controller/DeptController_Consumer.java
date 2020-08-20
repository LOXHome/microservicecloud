package com.ns.springcloud.controller;

import java.util.List;

import com.ns.springcloud.entity.Dept;
import com.ns.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeptController_Consumer {
	@Autowired
	private DeptClientService service;

	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id)
	{
		return service.get(id);
	}

	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list()
	{
		return  service.list();
	}

	@RequestMapping(value = "/consumer/dept/add")
	public Object add(Dept dept)
	{
		return service.add(dept);
	}
}
