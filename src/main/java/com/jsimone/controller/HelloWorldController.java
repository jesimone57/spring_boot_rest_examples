package com.jsimone.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public String sayHelloByParameter(@RequestParam("name") String name) {
		return "hello " + name;
	}

	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	public String sayHelloByVariable(@PathVariable String name) {
		return "hello " + name;
	}

}
