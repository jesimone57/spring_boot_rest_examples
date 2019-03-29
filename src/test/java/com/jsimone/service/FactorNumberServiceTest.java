package com.jsimone.service;

import com.jsimone.app.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by jsimone on 6/9/17.
 */
@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class FactorNumberServiceTest {

	@Autowired
	FactorNumberService factorNumberService;

	@Test
	public void computeFactorsOf100() throws Exception {
		Integer[] expected = {1, 2, 4, 5, 10, 20, 25, 50};
		List<Integer> list = factorNumberService.computeFactors(100);
		assertEquals("factor number list is incorrect", Arrays.asList(expected), list);
	}

	@Test
	public void computeFactorsOf220() throws Exception {
		Integer[] expected = {1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110};
		List<Integer> list = factorNumberService.computeFactors(220);
		assertEquals("factor number list is incorrect", Arrays.asList(expected), list);
	}

	@Test
	public void findAmicableNumberOf220() throws Exception {
		Integer amicableNumber = factorNumberService.computeAmicableNumber(220);
		assertEquals(Integer.valueOf(284), amicableNumber);
	}

	@Test
	public void findAmicableNumberOf284() throws Exception {
		Integer amicableNumber = factorNumberService.computeAmicableNumber(284);
		assertEquals(Integer.valueOf(220), amicableNumber);
	}

	@Test
	public void findAmicableNumberPairs() throws Exception {
		List<Set<Integer>> pairs = factorNumberService.computeAmicableNumberPairs(1,1000);
		Set<Integer> set = new TreeSet<>();
		set.add(220);
		set.add(284);
		List<Set<Integer>> expected = new ArrayList<>();
		expected.add(set);
		assertEquals(expected, pairs);
	}

	@Test
	public void testPerfectNumber6() throws Exception {
		assertTrue(factorNumberService.isPerfectNumber(6));
		assertFalse(factorNumberService.isPerfectNumber(10));
		assertFalse(factorNumberService.isPerfectNumber(12));
	}

	@Test
	public void testPerfectNumber28() throws Exception {
		assertTrue(factorNumberService.isPerfectNumber(28));
		assertFalse(factorNumberService.isPerfectNumber(30));
		assertFalse(factorNumberService.isPerfectNumber(48));
	}
}
