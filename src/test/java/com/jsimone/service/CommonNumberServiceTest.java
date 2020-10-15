package com.jsimone.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CommonNumberServiceTest {

    private CommonNumberService commonNumberService = new CommonNumberService();
    private PrimeNumberService primeNumberService = new PrimeNumberService();

    @Before
    public void setUp() {
        commonNumberService.setPrimeNumberService(primeNumberService);
    }

    @Test
    public void test_leastCommonMultiple() throws Exception {
        assertEquals("lcm is incorrect", 336, commonNumberService.computeLeastCommonMultiple(28, 48));
        assertEquals("lcm is incorrect", 750, commonNumberService.computeLeastCommonMultiple(250, 75));
        assertEquals("lcm is incorrect", 440, commonNumberService.computeLeastCommonMultiple(88, 40));
    }

    @Test
    public void test_greatestCommonDivisor() throws Exception {
        assertEquals("GCD is incorrect", 15, commonNumberService.computeGreatestCommonDivisor(30, 45));
        assertEquals("GCD is incorrect", 20, commonNumberService.computeGreatestCommonDivisor(100, 80));
        assertEquals("GCD is incorrect", 1001, commonNumberService.computeGreatestCommonDivisor(30030, 1001));
    }
}
