package com.jsimone.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsimone on 6/1/17.
 */
@Service
public class PalindromicNumberService {

    /**
     * A palindromic number is one which when reversed equals itself.  for Example 626, 737 etc.
     *
     * @param start
     * @param end
     * @return
     */
    public List<Integer> computePalindromicNumbersInRange(int start, int end) {
        List<Integer> results = new ArrayList<>();

        // swap the range if reversed
        if (end < start) {
            int temp = start;
            start = end;
            end = temp;
        }

        for (int i = start; i <= end; i++) {
            String reverse = new StringBuilder(String.valueOf(i)).reverse().toString();
            if (String.valueOf(i).equals(reverse)) {
                results.add(i);
            }
        }

        return results;
    }

}
