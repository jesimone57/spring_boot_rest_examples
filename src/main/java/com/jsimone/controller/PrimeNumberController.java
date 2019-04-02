package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.NumbersMapResponse;
import com.jsimone.entity.NumbersResponse;
import com.jsimone.entity.NumbersType;
import com.jsimone.entity.Range;
import com.jsimone.service.CommonNumberService;
import com.jsimone.service.PrimeNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@Api(value = "Prime Numbers API",
        description = "A prime number is a whole number greater than 1 whose only factors are 1 and itself. A factor is a whole numbers that can be divided evenly into another number. ",
        tags = "Prime Numbers API")
@RequestMapping("/")
public class PrimeNumberController extends RestControllerBase {

    @Autowired
    private PrimeNumberService primeNumberService;

    @Autowired
    private CommonNumberService commonNumberService;

    @ApiOperation(value = "Is the given number a prime number?")
    @GetMapping(value = UrlPath.URL_IS_PRIME)
    public Boolean isPrimeNumber(@PathVariable Integer number) {
        List<Integer> result = primeNumberService.computePrimesInRange(number, number);
        return (!result.isEmpty());
    }

    @ApiOperation(value = "Find all prime numbers in the given range")
    @GetMapping(value = UrlPath.URL_PRIMES_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersResponse getPrimesNumbersInRange(@PathVariable Integer start, @PathVariable Integer end) {
        NumbersResponse primes = new NumbersResponse(start, end);
        primes.setNumbers(primeNumberService.computePrimesInRange(start, end), NumbersType.Prime);
        return primes;
    }

    @ApiOperation(value = "Find all prime factors for each number in the given range")
    @GetMapping(value = UrlPath.URL_PRIME_FACTORS, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersMapResponse getPrimeFactorsInRange(@Valid Range range) {
        NumbersMapResponse response = new NumbersMapResponse(range);
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = range.getStart(); i <= range.getEnd(); i++) {
            List<Integer> factors = primeNumberService.computePrimeFactorization(i);
            map.put(i, factors);
        }
        response.setNumbers(map, NumbersType.PrimeFactors);
        return response;
    }

    @ApiOperation(value = "Find all prime factors of the given number")
    @GetMapping(value = UrlPath.URL_PRIME_FACTORIZATION, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Integer> getPrimeFactorization(@PathVariable int number) {
        return primeNumberService.computePrimeFactorization(number);
    }
}