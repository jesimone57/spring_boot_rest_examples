package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.NumbersResponse;
import com.jsimone.entity.NumbersType;
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

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@Api(value = "Prime Numbers API",
        description = "A prime number is a number that has no other divisors besides only 1 and itself",
        tags = "Prime Numbers API")
@RequestMapping("/")
public class PrimeNumberController {

    @Autowired
    private PrimeNumberService primeNumberService;

    @Autowired
    private CommonNumberService commonNumberService;

    @ApiOperation(value = "Is the given number a prime number?")
    @GetMapping(value = UrlPath.URL_IS_PRIME)
    public Boolean isPrimeNumber(@PathVariable Integer number) {
        NumbersResponse response = new NumbersResponse();
        response.setStart(number);
        response.setEnd(number);
        response.setNumbers(primeNumberService.computePrimesInRange(number, number), NumbersType.Prime);
        return (!response.getNumbers().isEmpty());
    }

    @ApiOperation(value = "Find all prime numbers in the given range")
    @GetMapping(value = UrlPath.URL_PRIMES_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public NumbersResponse getPrimesNumbersInRange(@PathVariable Integer start, @PathVariable Integer end) {
        NumbersResponse primes = new NumbersResponse();
        primes.setStart(start);
        primes.setEnd(end);
        primes.setNumbers(primeNumberService.computePrimesInRange(start, end), NumbersType.Prime);
        return primes;
    }

    @ApiOperation(value = "Find all prime factors for each number in the given range")
    @GetMapping(value = UrlPath.URL_PRIME_FACTORS_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<Integer, List<Integer>> getPrimeFactorsInRange(@PathVariable int start, @PathVariable int end) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = start; i <= end; i++) {
            List<Integer> factors = primeNumberService.computePrimeFactorization(i);
            map.put(i, factors);
        }
        return map;
    }

    @ApiOperation(value = "Find all prime factors of the given number")
    @GetMapping(value = UrlPath.URL_PRIME_FACTORIZATION, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Integer> getPrimeFactorization(@PathVariable int number) {
        return primeNumberService.computePrimeFactorization(number);
    }
}