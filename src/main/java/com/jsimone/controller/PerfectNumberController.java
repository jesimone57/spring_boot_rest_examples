package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.NumbersResponse;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Perfect Numbers API",
        description = "A perfect number is one whose factors sum to the number itself. For example 6 is a perfect number because its factors 1,2 and 3 = 1+2+3 = 6, which is the number itself.",
        tags = "Perfect Numbers API")
@RequestMapping("/")
public class PerfectNumberController {

    @Autowired
    private FactorNumberService factorNumberService;

    @ApiOperation(value = "Find all perfect numbers in the given range")
    @GetMapping(value = UrlPath.URL_PERFECT_NUMBERS, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersResponse getPerfectNumbersInRange(@Valid Range range) {
        List<Integer> list = new ArrayList<>();

        NumbersResponse response = new NumbersResponse(range);
        for (int i = range.getStart(); i <= range.getEnd(); i++) {
            if (factorNumberService.isPerfectNumber(i)) {
                list.add(i);
            }
        }
        response.setNumbers(list, NumbersType.Perfect);
        return response;
    }

}