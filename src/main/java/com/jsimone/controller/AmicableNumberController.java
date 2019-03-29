package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.NumbersSetResponse;
import com.jsimone.entity.NumbersType;
import com.jsimone.entity.Range;
import com.jsimone.service.FactorNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "Amicable Numbers API",
        description = "An amicable number is one whose factors sum to second number, for which that second number's factor sum to the first number.",
        tags = "Amicable Numbers API")
@RequestMapping("/")
public class AmicableNumberController extends RestControllerBase {

    @Autowired
    private FactorNumberService factorNumberService;

    @ApiOperation(value = "Find amicable number pairs in the given range")
    @GetMapping(value = UrlPath.URL_AMICABLE_NUMBERS, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersSetResponse getAmicableNumbersInRange(@Valid Range range) {
        NumbersSetResponse response = new NumbersSetResponse(range);
        response.setNumbers(factorNumberService.computeAmicableNumberPairs(range.getStart(), range.getEnd()), NumbersType.Amicable);
        return response;
    }

}