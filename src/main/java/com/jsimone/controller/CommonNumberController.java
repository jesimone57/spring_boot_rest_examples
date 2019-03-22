package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.service.CommonNumberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CommonNumberController {

    @Autowired
    private CommonNumberService commonNumberService;

    /**
     * The GCD is the product of all the common prime factors between the two given numbers.
     * Also known as the Greatest Common Factor (GCF).
     * http://stackoverflow.com/questions/4201860/how-to-find-gcf-lcm-on-a-set-of-numbers
     */
    @ApiOperation(value = "Find the Greatest Common Divisor. The GCD is the product of all the common prime factors between the two given numbers.")
    @GetMapping(value = UrlPath.URL_GCD, produces = {MediaType.APPLICATION_JSON_VALUE})
    public int getGreatestCommonDivisor(@PathVariable int number1, @PathVariable int number2) {
        return commonNumberService.computeGreatestCommonDivisor(number1, number2);
    }

    /**
     * The LCM is the product of all the common prime factors pairs and all the unique factors from the two given numbers.
     * http://stackoverflow.com/questions/4201860/how-to-find-gcf-lcm-on-a-set-of-numbers
     */
    @ApiOperation("Find the Leat Common Multiple. The LCM is the product of all the common prime factors pairs and all the unique factors from the two given numbers.")
    @GetMapping(value = UrlPath.URL_LCM, produces = {MediaType.APPLICATION_JSON_VALUE})
    public int getLeastCommonMultiple(@PathVariable int number1, @PathVariable int number2) {
        return commonNumberService.computeLeastCommonMultiple(number1, number2);
    }


}