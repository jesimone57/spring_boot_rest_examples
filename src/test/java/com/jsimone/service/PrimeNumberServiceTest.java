package com.jsimone.service;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrimeNumberServiceTest {

    private PrimeNumberService primeNumberService = new PrimeNumberService();

    @Test
    public void test_generatePrimesTo100() throws Exception {
        Integer[] expected = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        List<Integer> list = primeNumberService.computePrimesInRange(3, 100);
        assertEquals("prime number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_generatePrimes2to10() throws Exception {
        Integer[] expected = {2, 3, 5, 7};
        List<Integer> list = primeNumberService.computePrimesInRange(2, 10);
        assertEquals("prime number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_generatePrimes2() throws Exception {
        Integer[] expected = {2};
        List<Integer> list = primeNumberService.computePrimesInRange(2, 2);
        assertEquals("prime number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_generatePrimes1() throws Exception {
        Integer[] expected = {2};
        List<Integer> list = primeNumberService.computePrimesInRange(1, 2);
        assertEquals("prime number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_generatePrimesBackwardsRange() throws Exception {
        Integer[] expected = {3, 5};
        List<Integer> list = primeNumberService.computePrimesInRange(5, 3);
        assertEquals("prime number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_generatePrimesLessThan2() throws Exception {
        Integer[] expected = {2, 3};
        List<Integer> list = primeNumberService.computePrimesInRange(-1, 3);
        assertEquals("prime number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_generatePrimesLessThan1() throws Exception {
        Integer[] expected = {};
        List<Integer> list = primeNumberService.computePrimesInRange(-1, 1);
        assertEquals("prime number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_isPrime101() throws Exception {
        assertTrue("prime number incorrect", primeNumberService.isPrimeNumber(101));
    }

    @Test
    public void test_isNotPrime4() throws Exception {
        assertFalse("prime number incorrect", primeNumberService.isPrimeNumber(4));
    }

    @Test
    public void test_generatePrimes4_4() throws Exception {
        Integer[] expected = {};
        List<Integer> list = primeNumberService.computePrimesInRange(4, 4);
        assertEquals("prime number list is incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_primeFactorization2() throws Exception {
        Integer[] expected = {2};
        List<Integer> list = primeNumberService.computePrimeFactorization(2);
        assertEquals("prime factors are incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_primeFactorization6() throws Exception {
        Integer[] expected = {2, 3};
        List<Integer> list = primeNumberService.computePrimeFactorization(6);
        assertEquals("prime factors are incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_primeFactorization96() throws Exception {
        Integer[] expected = {2, 2, 2, 2, 2, 3};
        List<Integer> list = primeNumberService.computePrimeFactorization(96);
        assertEquals("prime factors are incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_primeFactorization101() throws Exception {
        Integer[] expected = {101};
        List<Integer> list = primeNumberService.computePrimeFactorization(101);
        assertEquals("prime factors are incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_primeFactorization5735() throws Exception {
        Integer[] expected = {5, 31, 37};
        List<Integer> list = primeNumberService.computePrimeFactorization(5735);
        assertEquals("prime factors are incorrect", Arrays.asList(expected), list);
    }

    @Test
    public void test_primeFactorization() throws Exception {
        for (int i = 2; i <= 10001; i++) {
            List<Integer> factors = primeNumberService.computePrimeFactorization(i);
            //System.out.println(i+"   "+factors);
            int product = 1;
            for (Integer factor : factors) {
                product *= factor;
            }
            assertEquals("prime factors are incorrect", i, product);
        }
    }

    @Test
    public void test_isPrime() throws Exception {
        for (int i = 2; i <= 10001; i++) {
            List<Integer> factors = primeNumberService.computePrimeFactorization(i);
            //System.out.println(i+"   "+factors);
            if (factors.size() == 1) {
                assertTrue(i + " should be prime and is not", primeNumberService.isPrimeNumber(i));
            } else {
                assertFalse(i + " should not be prime and is", primeNumberService.isPrimeNumber(i));
            }
        }
    }


}
