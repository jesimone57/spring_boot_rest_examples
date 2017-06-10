package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.AmicableNumbers;
import com.jsimone.service.FactorNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/")
public class AmicableNumberController {

	@Autowired
	private FactorNumberService factorNumberService;

	/**
	 * An amicable number is one whose factors sum to second number, for which that second number's factor sum to the first number.
	 * Hence the numbers are related in this way.
	 */
	@RequestMapping(value = UrlPath.URL_AMICABLE_NUMBERS_IN_RANGE, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public AmicableNumbers getPalindromicNumbersInRange(@PathVariable int start, @PathVariable int end) {
		List<Set<Integer>> listofSets = new ArrayList<>();

		AmicableNumbers amicableNumbers = new AmicableNumbers();
		amicableNumbers.setStart(start);
		amicableNumbers.setEnd(end);
		for (int i = start; i < end; i++) {
			Integer result = factorNumberService.computeAmicableNumber(i);
			if (result != null) {
				Set<Integer> pair = new TreeSet<>();
				pair.add(i);
				pair.add(result);
				if (! listofSets.contains(pair)) {
					listofSets.add(pair);
				}
			}
		}
		amicableNumbers.setAmicableNumbers(listofSets);
		return amicableNumbers;
	}

}