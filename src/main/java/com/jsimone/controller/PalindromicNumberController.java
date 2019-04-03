package com.jsimone.controller;

import com.jsimone.entity.NumbersResponse;
import com.jsimone.entity.NumbersType;
import com.jsimone.entity.Range;
import com.jsimone.service.PalindromicNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.jsimone.constant.UrlPath.URL_PALINDROMIC_NUMBERS;
import static com.jsimone.constant.UrlPath.URL_PALINDROMIC_NUMBERS_IN_RANGE;

@RestController
@Api(value = "Palindromic Numbers API",
        description = "A palindromic number is one which equals itself when reversed such as 12321 or 626 or 11.",
        tags = "Palindromic Numbers API")
@RequestMapping("/")
public class PalindromicNumberController {

    @Autowired
    private PalindromicNumberService palindromicNumberService;

    @ApiOperation(value = "Find all palindromic numbers in the given range")
    @GetMapping(value = URL_PALINDROMIC_NUMBERS_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersResponse getPalindromicNumbersInRange(@PathVariable int start, @PathVariable int end) {
        NumbersResponse response = new NumbersResponse(start, end);
        response.setNumbers(palindromicNumberService.computePalindromicNumbersInRange(start, end), NumbersType.Palindrome);
        return response;
    }

    @ApiOperation(value = "Find all palindromic numbers in the given range")
    @GetMapping(value = URL_PALINDROMIC_NUMBERS, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersResponse getPalindromicNumbersInRange2(@Valid Range range) {
        NumbersResponse response = new NumbersResponse(range);
        response.setNumbers(palindromicNumberService.computePalindromicNumbersInRange(range.getStart(), range.getEnd()), NumbersType.Palindrome);
        return response;
    }

}