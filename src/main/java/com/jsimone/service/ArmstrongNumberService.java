package com.jsimone.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsimone on 6/1/17.
 */
@Service
public class ArmstrongNumberService {

	/**
	 * An Armstrong number of 3 digit is a number for which sum of cube of its digits are equal to number
	 * e.g. 371 is an Armstrong number because 3*3*3 + 7*7*7 + 1*1*1 = 371).
	 * (input 153 output true,  123 output false)
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Integer> computeArmstrongNumbersInRange(int start, int end) {
		List<Integer> results = new ArrayList<>();

		// swap the range if reversed
		if (end < start) {
			int temp = start;
			start = end;
			end = temp;
		}

		for (int i = start; i <= end; i++) {
			int[] digits = getDigits(i);
			long sumOfDigitsToNthPower = 0;
			for (Integer j : digits) {
				sumOfDigitsToNthPower += Math.pow(j, digits.length);
			}
			if (sumOfDigitsToNthPower == i) {
				results.add(i);
			}
		}

		return results;
	}

	public int[] getDigits(int number) {
		String s = String.valueOf(number);
		int[] digits = new int[s.length()];
		int i = 0;
		for (Character c : s.toCharArray()) {
			int digit = Integer.valueOf(c.toString());
			digits[i] = digit;
			i++;
		}
		return digits;
	}

}
