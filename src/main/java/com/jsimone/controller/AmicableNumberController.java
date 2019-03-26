package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.NumbersSetResponse;
import com.jsimone.entity.NumbersType;
import com.jsimone.service.FactorNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RestController
@Api(value = "Amicable Numbers API",
        description = "An amicable number is one whose factors sum to second number, for which that second number's factor sum to the first number.",
        tags = "Amicable Numbers API")
@RequestMapping("/")
public class AmicableNumberController {

    @Autowired
    private FactorNumberService factorNumberService;

    /**
     * An amicable number is one whose factors sum to second number, for which that second number's factor sum to the first number.
     * Hence the numbers are related in this way.
     */
    @ApiOperation(value = "Find amicable number pairs in the given range")
    @GetMapping(value = UrlPath.URL_AMICABLE_NUMBERS_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersSetResponse getAmicableNumbersInRange(@PathVariable int start, @PathVariable int end) {
        List<Set<Integer>> listofSets = new ArrayList<>();

        NumbersSetResponse response = new NumbersSetResponse();
        response.setStart(start);
        response.setEnd(end);
        for (int i = start; i < end; i++) {
            Integer result = factorNumberService.computeAmicableNumber(i);
            if (result != null) {
                Set<Integer> pair = new TreeSet<>();
                pair.add(i);
                pair.add(result);
                if (!listofSets.contains(pair)) {
                    listofSets.add(pair);
                }
            }
        }
        response.setNumbers(listofSets, NumbersType.Amicable);
        return response;
    }

}