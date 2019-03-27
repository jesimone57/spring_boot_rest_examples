Feature:  prime numbers

  Background:
    * url 'http://localhost:8080'
    * configure lowerCaseResponseHeaders = true

  Scenario: is 11 a prime number?
    Given path '/isprime/11'
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == 'true'

  Scenario: is 12 a prime number?
    Given path '/isprime/12'
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == 'false'

  Scenario Outline: validate <number> for being prime=<result>
    Given path '/isprime/<number>'
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == <result>
    Examples:
      | number | result |
      | 7      | true   |
      | 8      | false  |
      | 10     | false  |
      | 11     | true   |
      | 12     | false  |
      | 13     | true   |

  Scenario Outline: Find all the the prime factors of <number> are <result>
    Given path '/primefactors/<number>'
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == <result>
    Examples:
      | number | result     |
      | 2      | [2]        |
      | 3      | [3]        |
      | 6      | [2,3]      |
      | 10     | [2,5]      |
      | 100    | [2,2,5,5]  |
      | 10001  | [73,137]   |
      | 100001 | [11,9091]  |

  Scenario Outline: Find all the the prime factors in the range from <start> to <end> are <result>
    Given path '/primefactorsinrange/<start>/<end>'
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == {numbers:<result>, start:<start>, end:<end>, count:<count>, type:PrimeFactors}
    Examples:
      | start | end     | result                                        | count
      | 8     | 10      | {8: [2,2,2], 9:[3,3], 10:[2,5]}               | 3
      | 13    | 16      | {13: [13], 14:[2,7], 15:[3,5], 16:[2,2,2,2]}  | 4


  Scenario Outline: Find all primes in the range from <start> to <end> are <result>
    Given path '/primesinrange/<start>/<end>'
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == {numbers:<result>, start:<start>, end:<end>, count:4, type:Prime}
    Examples:
      | start | end | result
      | 1     | 10  | [2,3,5,7]
      | 10    | 20  | [11,13,17,19]
      | 20    | 40  | [23,29,31,37]
