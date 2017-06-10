package com.jsimone.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsimone on 6/1/17.
 */
@Service
public class FactorNumberService {

	public List<Integer> computeFactors(int number) {
		List<Integer> factors = new ArrayList<>();
		for (int divisor=1; divisor<number; divisor++) {
			//System.out.println("\tdivisor="+divisor+"   number%divisor="+number%divisor);
			if (number % divisor == 0) {
				factors.add(divisor);
			}
		}
		return factors;
	}

	public Integer computeAmicableNumber(int number) {
		List<Integer> factors1 = computeFactors(number);
		int sumOfFactors1 = factors1.stream().mapToInt(e -> e).sum();
		List<Integer> factors2 = computeFactors(sumOfFactors1);
		int sumOfFactors2 = factors2.stream().mapToInt(e -> e).sum();
		if (sumOfFactors1 != number && sumOfFactors2 == number) {
			return sumOfFactors1;
		} else {
			return null;
		}
	}

	public boolean isPerfectNumbe(int number) {
		List<Integer> factors = computeFactors(number);
		int sumOfFactors = factors.stream().mapToInt(e -> e).sum();
		if (sumOfFactors == number) {
			return true;
		} else {
			return false;
		}
	}

}
