package com.jsimone.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * Created by jsimone on 6/1/17.
 */
@Service
public class PrimeNumberService {

	public List<Integer> computePrimesInRange(int start, int end) {
		List<Integer> primes = new ArrayList<>();

		// swap the range if reversed
		if (end < start) {
			int temp = start;
			start = end;
			end = temp;
		}

		if (start <= 1) {
			start = 2;
		}

		// if start is even, then make it odd
		if (start % 2 == 0 && start != 2) {
			start++;
		}

		if (end < start) {
			return primes;
		}

		if (start == 2) {
			primes.add(2);
			start++;
		}

		for (int i = start; i <= end; i = i + 2) {
			if (isPrimeNumber(i)) {
				primes.add(i);
			}
		}

		return primes;
	}

	public boolean isPrimeNumber(int number) {
		if (number == 2) {
			return true;
		}

		boolean isPrime = true;
		if (number % 2 == 0) {
			isPrime = false;
		} else {
			for (int j = 3; j <= sqrt(number); j = j + 2) {
				if (number % j == 0) {
					isPrime = false;
					break;
				}
			}
		}
		return isPrime;
	}

	public List<Integer> computePrimeFactorization(int number) {
		long maxPossibleFactor = Math.round(sqrt(number));
		//System.out.println("max possible factor="+maxPossibleFactor);
		List<Integer> primes = computePrimesInRange(2, (int) maxPossibleFactor);
		//System.out.println(primes);

		List<Integer> factors = new ArrayList<>();
		for (Integer divisor : primes) {
			//System.out.println("\tdivisor="+divisor+"   number%divisor="+number%divisor);
			while (number % divisor == 0) {
				factors.add(divisor);
				number /= divisor;
				//System.out.println("number is now "+number);
			}
			if (number == 1) {
				break;
			}
		}
		if (factors.isEmpty() || number > 1) {
			factors.add(number);
		}
		return factors;
	}

}
