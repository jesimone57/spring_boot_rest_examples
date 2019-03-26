package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.NumbersResponse;
import com.jsimone.entity.NumbersType;
import com.jsimone.service.ArmstrongNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Armstrong Numbers API",
        description = "Is a number that is the sum of its own digits each raised to the power of the number of digits. " +
                "An Armstrong number of 3 digits is a number for which sum of cube of its digits are equal to the number." +
                "An armstrong number of 4 digits is a number for which sum of 4th power of its digits are equal to the number." +
                "For example 1634 is an armstrong number because  1^4=1 + 6^4=1296 + 3^4=81 + 4^4=256 = 1634",
        tags = "Armstrong Numbers API")
@RequestMapping("/")
public class ArmstrongNumberController {

    @Autowired
    private ArmstrongNumberService armstrongNumberService;

    /**
     * An Armstrong number of 3 digit is a number for which sum of cube of its digits are equal to number
     * e.g. 371 is an Armstrong number because 3*3*3 + 7*7*7 + 1*1*1 = 371).
     */
    @ApiOperation(value = "Find amstrong numbers in the given range")
    @GetMapping(value = UrlPath.URL_ARMSTRONG_NUMBERS_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersResponse getArmstrongNumbersInRange(@PathVariable int start, @PathVariable int end) {
        NumbersResponse response = new NumbersResponse();
        response.setStart(start);
        response.setEnd(end);
        response.setNumbers(armstrongNumberService.computeArmstrongNumbersInRange(start, end), NumbersType.Armstrong);
        return response;
    }

}