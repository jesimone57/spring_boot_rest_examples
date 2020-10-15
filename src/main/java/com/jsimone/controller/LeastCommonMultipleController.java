package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.service.CommonNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Least Common Multiple (LCM) API",
        description = "The LCM is the product of all the common prime factors pairs and all the unique factors from the two given numbers.",
        tags = "Least Common Multiple (LCM) API")
@RequestMapping("/")
public class LeastCommonMultipleController {

    @Autowired
    private CommonNumberService commonNumberService;

    /**
     * The LCM is the product of all the common prime factors pairs and all the unique factors from the two given numbers.
     * http://stackoverflow.com/questions/4201860/how-to-find-gcf-lcm-on-a-set-of-numbers
     */
    @ApiOperation("Find the Least Common Multiple")
    @GetMapping(value = UrlPath.URL_LCM, produces = {MediaType.APPLICATION_JSON_VALUE})
    public int getLeastCommonMultiple(@PathVariable int number1, @PathVariable int number2) {
        return commonNumberService.computeLeastCommonMultiple(number1, number2);
    }


}