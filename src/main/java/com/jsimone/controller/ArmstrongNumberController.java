package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.NumbersResponse;
import com.jsimone.entity.NumbersType;
import com.jsimone.entity.Range;
import com.jsimone.service.ArmstrongNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "Armstrong Numbers API",
        description = "Is a number that is the sum of its own digits each raised to the power of the number of digits. " +
                "An Armstrong number of 3 digits is a number for which sum of cube of its digits are equal to the number." +
                "For example 371 is an armstrong number because  3^3=27 + 7^3=343 + 1^3=1 = 371" +
                "An armstrong number of 4 digits is a number for which sum of 4th power of its digits are equal to the number." +
                "For example 1634 is an armstrong number because  1^4=1 + 6^4=1296 + 3^4=81 + 4^4=256 = 1634",
        tags = "Armstrong Numbers API")
@RequestMapping("/")
public class ArmstrongNumberController extends RestControllerBase {

    @Autowired
    private ArmstrongNumberService armstrongNumberService;

    @ApiOperation(value = "Find amstrong numbers in the given range")
    @GetMapping(value = UrlPath.URL_ARMSTRONG_NUMBERS, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersResponse getArmstrongNumbersInRange(@Valid Range range) {
        NumbersResponse response = new NumbersResponse(range);
        response.setNumbers(armstrongNumberService.computeArmstrongNumbersInRange(range.getStart(), range.getEnd()), NumbersType.Armstrong);
        return response;
    }

}