package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.PrimeNumbers;
import com.jsimone.service.CommonNumberService;
import com.jsimone.service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = UrlPath.URL_IS_PRIME, method = RequestMethod.GET)
	public Boolean isPrimeNumber(@PathVariable Integer number) {
		PrimeNumbers primes = new PrimeNumbers();
		primes.setStart(number);
		primes.setEnd(number);
		primes.setPrimeNumbers(primeNumberService.computePrimes(number, number));
		return (!primes.getPrimeNumbers().isEmpty());
	}

	@RequestMapping(value = UrlPath.URL_PRIMES_IN_RANGE, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public PrimeNumbers getPrimesNumbersInRange(@PathVariable Integer start, @PathVariable Integer end) {
		PrimeNumbers primes = new PrimeNumbers();
		primes.setStart(start);
		primes.setEnd(end);
		primes.setPrimeNumbers(primeNumberService.computePrimes(start, end));
		return primes;
	}

	@RequestMapping(value = UrlPath.URL_PRIME_FACTORS_IN_RANGE, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Map<Integer, List<Integer>> getFactorsInRange(@PathVariable int start, @PathVariable int end) {
		Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
		for (int i = start; i <= end; i++) {
			List<Integer> factors = primeNumberService.computePrimeFactorization(i);
			map.put(i, factors);
		}
		return map;
	}

	@RequestMapping(value = UrlPath.URL_PRIME_FACTORIZATION, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Integer> getPrimeFactorization(@PathVariable int number) {
		return primeNumberService.computePrimeFactorization(number);
	}
}