# Examples Showing How to Implement REST API using Spring Boot

This repository contains examples of how to use the Spring REST framework to implement 
an API which does some simple things:
1. Say hello
2. Tell the time of day
3. Compute prime numbers and prime factors of a number
4. Compute the Greatest Common Divisor (GCD) of two numbers
5. Compute the Least Commonn Multiple (LCM) of two numbers

NOTE:
If you try to hit an invalid endpoint, the Spring controller advice will produce a
nice error message indicating the endpoint is not in service.

## To execute the code
1. git clone the repo to your local machine
2. mvn clean compile test
3. mvn spring-boot:run
4. Then try any of the following URLS:

## Hello Examples

### Hello using a name as a path variable
[http://localhost:8080/hello/tom](http://localhost:8080/hello/tom)

### Hello using a name as a request paramter
[http://localhost:8080/hello2?name=fred](http://localhost:8080/hello2?name=fred)

## Time of the Day Examples

### Time of the day - plain text
[http://localhost:8080/timeoftheday](http://localhost:8080/timeoftheday)

### Time of the day - in XML
[http://localhost:8080/timeofthedayxml](http://localhost:8080/timeofthedayxml)

### Time of the day - in JSON
[http://localhost:8080/timeofthedayjson](http://localhost:8080/timeofthedayjson)

## Primes, Factors, Greatest Command Divisor (GCD) and Least Common Multiple (LCM) Examples

### Is the given number prime?
[http://localhost:8080/isprime/101](http://localhost:8080/isprime/101)

[http://localhost:8080/isprime/100](http://localhost:8080/isprime/100)

### Find prime numbers in the given range
[http://localhost:8080/primesinrange/3/101](http://localhost:8080/primesinrange/3/101)

[http://localhost:8080/primesinrange/10001/10099](http://localhost:8080/primesinrange/10001/10099)

### Find the prime factorization of the numbers in the given range
[http://localhost:8080/factorsinrange/3/101](http://localhost:8080/factorsinrange/3/101)

[http://localhost:8080/factorsinrange/10001/10099](http://localhost:8080/factorsinrange/10001/10099)

### Find the prime factors of a given number
[http://localhost:8080/factors/100](http://localhost:8080/factors/100)

[http://localhost:8080/factors/1024](http://localhost:8080/factors/1024)

### Find the greatest common divisor (GCD) - also known as Greatest Common Factor (GCF)
[http://localhost:8080/gcd/30/45](http://localhost:8080/gcd/30/45)

### Find the least common multiple (LCM)
[hhttp://localhost:8080/lcm/10/15](http://localhost:8080/lcm/10/15)

