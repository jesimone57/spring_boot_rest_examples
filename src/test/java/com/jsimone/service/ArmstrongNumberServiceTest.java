package com.jsimone.service;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jsimone on 6/2/17.
 */
public class ArmstrongNumberServiceTest {

    @Test
    public void armstrongNumber() throws Exception {
        ArmstrongNumberService armstrongNumberService = new ArmstrongNumberService();
        List<Integer> numbers = armstrongNumberService.computeArmstrongNumbersInRange(370, 375);
        assertEquals(2, numbers.size());
        assertEquals(Integer.valueOf(370), numbers.get(0));
        assertEquals(Integer.valueOf(371), numbers.get(1));
    }

    @Test
    public void getDigits1() throws Exception {
        ArmstrongNumberService armstrongNumberService = new ArmstrongNumberService();
        int[] digits = armstrongNumberService.getDigits(1);
        assertEquals(1, digits.length);
        assertEquals(1, digits[0]);
    }

    @Test
    public void getDigits2() throws Exception {
        ArmstrongNumberService armstrongNumberService = new ArmstrongNumberService();
        int[] digits = armstrongNumberService.getDigits(53);
        assertEquals(2, digits.length);
        assertEquals(5, digits[0]);
        assertEquals(3, digits[1]);
    }

    @Test
    public void getDigits3() throws Exception {
        ArmstrongNumberService armstrongNumberService = new ArmstrongNumberService();
        int[] digits = armstrongNumberService.getDigits(749);
        assertEquals(3, digits.length);
        assertEquals(7, digits[0]);
        assertEquals(4, digits[1]);
        assertEquals(9, digits[2]);
    }

    @Test
    public void getDigits4() throws Exception {
        ArmstrongNumberService armstrongNumberService = new ArmstrongNumberService();
        int[] digits = armstrongNumberService.getDigits(1234);
        assertEquals(4, digits.length);
        assertEquals(1, digits[0]);
        assertEquals(2, digits[1]);
        assertEquals(3, digits[2]);
        assertEquals(4, digits[3]);
    }
}
