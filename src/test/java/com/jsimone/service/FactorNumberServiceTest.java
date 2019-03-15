package com.jsimone.service;

import com.jsimone.app.Application;
import com.jsimone.service.FactorNumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

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
	public void testPerfectNumber6() throws Exception {
		assertTrue(factorNumberService.isPerfectNumbe(6));
		assertFalse(factorNumberService.isPerfectNumbe(10));
		assertFalse(factorNumberService.isPerfectNumbe(12));
	}

	@Test
	public void testPerfectNumber28() throws Exception {
		assertTrue(factorNumberService.isPerfectNumbe(28));
		assertFalse(factorNumberService.isPerfectNumbe(30));
		assertFalse(factorNumberService.isPerfectNumbe(48));
	}
}
