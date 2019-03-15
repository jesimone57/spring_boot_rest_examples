package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.ArmstrongNumbers;
import com.jsimone.service.ArmstrongNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ArmstrongNumberController {

    @Autowired
    private ArmstrongNumberService armstrongNumberService;

    /**
     * An Armstrong number of 3 digit is a number for which sum of cube of its digits are equal to number
     * e.g. 371 is an Armstrong number because 3*3*3 + 7*7*7 + 1*1*1 = 371).
     */
    @GetMapping(value = UrlPath.URL_ARMSTRONG_NUMBERS_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArmstrongNumbers getArmstrongNumbersInRange(@PathVariable int start, @PathVariable int end) {
        ArmstrongNumbers numbers = new ArmstrongNumbers();
        numbers.setStart(start);
        numbers.setEnd(end);
        numbers.setArmstrongNumbers(armstrongNumberService.computeArmstrongNumbersInRange(start, end));
        return numbers;
    }

}