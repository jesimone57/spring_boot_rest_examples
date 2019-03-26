package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "Hello by name", tags="Hello API")
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping(value = UrlPath.URL_HELLO2, produces = {"text/plain;charset=UTF-8"})
    public String sayHelloByParameter(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping(value = UrlPath.URL_HELLO, produces = {"text/plain;charset=UTF-8"})
    public String sayHelloByVariable(@PathVariable String name) {
        return "hello " + name;
    }

}
