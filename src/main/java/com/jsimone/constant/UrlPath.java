package com.jsimone.constant;

/**
 * Created by jsimone on 6/2/17.
 */
public class UrlPath {

    private UrlPath() {
    }

    public static final String URL_HELLO2 = "/hello2";
    public static final String URL_HELLO = "/hello/{name}";

    public static final String URL_TIME_OF_THE_DAY = "/timeoftheday";
    public static final String URL_TIME_OF_THE_DAY_XML = "/timeofthedayxml";
    public static final String URL_TIME_OF_THE_DAY_JSON = "/timeofthedayjson";

    public static final String URL_GCD = "/gcd/{number1}/{number2}";
    public static final String URL_LCM = "/lcm/{number1}/{number2}";

    public static final String URL_IS_PRIME = "/isprime/{number}";
    public static final String URL_PRIMES_IN_RANGE = "/primesinrange/{start}/{end}";
    public static final String URL_PRIME_FACTORS_IN_RANGE = "/primefactorsinrange/{start}/{end}";
    public static final String URL_PRIME_FACTORIZATION = "/primefactors/{number}";

    public static final String URL_ARMSTRONG_NUMBERS_IN_RANGE = "/armstrong/{start}/{end}";
    public static final String URL_AMICABLE_NUMBERS_IN_RANGE = "/amicable/{start}/{end}";
    public static final String URL_PERFECT_NUMBERS_IN_RANGE = "/perfect/{start}/{end}";

    public static final String URL_PALINDROMIC_NUMBERS_IN_RANGE = "/palindromes/{start}/{end}";
}
