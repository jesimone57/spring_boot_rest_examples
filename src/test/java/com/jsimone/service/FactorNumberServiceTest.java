package com.jsimone.service;

import com.jsimone.entity.Range;
import com.jsimone.exception.ErrorResponseException;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class FactorNumberServiceTest {

    private FactorNumberService factorNumberService = new FactorNumberService();

    @Test
    public void computeFactorsOf100() {
        Integer[] expected = {1, 2, 4, 5, 10, 20, 25, 50};
        List<Integer> list = factorNumberService.computeFactors(100);
        assertEquals("factor number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void computeFactorsOf220() {
        Integer[] expected = {1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110};
        List<Integer> list = factorNumberService.computeFactors(220);
        assertEquals("factor number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void findAmicableNumberOf220() {
        Integer amicableNumber = factorNumberService.computeAmicableNumber(220);
        assertEquals(Integer.valueOf(284), amicableNumber);
    }

    @Test
    public void findAmicableNumberOf284() {
        Integer amicableNumber = factorNumberService.computeAmicableNumber(284);
        assertEquals(Integer.valueOf(220), amicableNumber);
    }

    @Test
    public void findAmicableNumberPairs() {
        List<Set<Integer>> pairs = factorNumberService.computeAmicableNumberPairs(1, 1000);
        Set<Integer> set = new TreeSet<>();
        set.add(220);
        set.add(284);
        List<Set<Integer>> expected = new ArrayList<>();
        expected.add(set);
        assertEquals(expected, pairs);
    }

    @Test
    public void testPerfectNumber6() {
        assertTrue(factorNumberService.isPerfectNumber(6));
        assertFalse(factorNumberService.isPerfectNumber(10));
        assertFalse(factorNumberService.isPerfectNumber(12));
    }

    @Test
    public void testPerfectNumber28() {
        assertTrue(factorNumberService.isPerfectNumber(28));
        assertFalse(factorNumberService.isPerfectNumber(30));
        assertFalse(factorNumberService.isPerfectNumber(48));
    }

    @Test
    public void testPerfectNumberinRange() {
        List<Integer> expected = new ArrayList<>();
        expected.add(6);
        expected.add(28);
        assertEquals(expected, factorNumberService.perfectNumbersInRange(new Range(1, 30)).getNumbers());
    }

    @Test(expected = ErrorResponseException.class)
    public void testPerfectNumberInRangeException() {
        factorNumberService.perfectNumbersInRange(new Range(6, 5));
    }
}
