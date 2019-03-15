package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping(value = UrlPath.URL_HELLO2)
    public String sayHelloByParameter(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping(value = UrlPath.URL_HELLO)
    public String sayHelloByVariable(@PathVariable String name) {
        return "hello " + name;
    }

}
