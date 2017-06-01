package com.jsimone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jsimone on 6/1/17.
 */
@Service
public class CommonNumberService {

	@Autowired
	private PrimeNumberService primeNumberService;

	public int computeLeastCommonMultiple(int number1, int number2) {
		List<Integer> factors1 = primeNumberService.computePrimeFactorization(number1);
		List<Integer> factors2 = primeNumberService.computePrimeFactorization(number2);
		int product = 1;
		//List<Integer> common = new ArrayList<Integer>();
		//List<Integer> unique = new ArrayList<Integer>();
		for (Integer factor : factors1) {
			if (factors2.contains(factor)) {
				factors2.remove(factor); // this common factor already accounted for.  Do not use again.
				//common.add(factor);
				product *= factor;
			} else {
				//unique.add(factor);
				product *= factor;
			}
		}
		for (Integer factor : factors2) {
			product *= factor;
		}
		return product;
	}


	public int computeGreatestCommonDivisor(int number1, int number2) {
		List<Integer> factors1 = primeNumberService.computePrimeFactorization(number1);
		List<Integer> factors2 = primeNumberService.computePrimeFactorization(number2);
		int product = 1;
		for (Integer factor : factors1) {
			if (factors2.contains(factor)) {
				product *= factor;
				factors2.remove(factor); // this common factor already accounted for.  Do not use again.
			}
		}
		return product;
	}
}
