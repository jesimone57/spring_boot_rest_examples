package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.PalindromicNumbers;
import com.jsimone.service.PalindromicNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PalindromicNumberController {

	@Autowired
	private PalindromicNumberService palindromicNumberService;

	/**
	 * A palindromic number is one which equals itself when reversed such as 12321 or 626 or 11.
	 */
	@RequestMapping(value = UrlPath.URL_PALINDROMIC_NUMBERS_IN_RANGE, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public PalindromicNumbers getPalindromicNumbersInRange(@PathVariable int start, @PathVariable int end) {
		PalindromicNumbers numbers = new PalindromicNumbers();
		numbers.setStart(start);
		numbers.setEnd(end);
		numbers.setPalindromicNumbers(palindromicNumberService.computePalindromicNumbersInRange(start, end));
		return numbers;
	}

}