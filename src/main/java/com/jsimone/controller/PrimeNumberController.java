package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.PrimeNumbers;
import com.jsimone.service.CommonNumberService;
import com.jsimone.service.PrimeNumberService;
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
@RequestMapping("/")
public class PrimeNumberController {

    @Autowired
    private PrimeNumberService primeNumberService;

    @Autowired
    private CommonNumberService commonNumberService;

    @ApiOperation(value = "Is the given number a prime number?")
    @GetMapping(value = UrlPath.URL_IS_PRIME)
    public Boolean isPrimeNumber(@PathVariable Integer number) {
        PrimeNumbers primes = new PrimeNumbers();
        primes.setStart(number);
        primes.setEnd(number);
        primes.setPrimeNumbers(primeNumberService.computePrimesInRange(number, number));
        return (!primes.getPrimeNumbers().isEmpty());
    }

    @ApiOperation(value = "Find all prime numbers in the given range")
    @GetMapping(value = UrlPath.URL_PRIMES_IN_RANGE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public PrimeNumbers getPrimesNumbersInRange(@PathVariable Integer start, @PathVariable Integer end) {
        PrimeNumbers primes = new PrimeNumbers();
        primes.setStart(start);
        primes.setEnd(end);
        primes.setPrimeNumbers(primeNumberService.computePrimesInRange(start, end));
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