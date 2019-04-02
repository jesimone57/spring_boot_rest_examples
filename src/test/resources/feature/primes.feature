Feature:  prime numbers

  Background:
    * url baseUrl
    * configure lowerCaseResponseHeaders = true

  Scenario: Is 11 a prime number?
    Given path '/isprime/11'
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == 'true'

  Scenario: Is 12 a prime number?
    Given path '/isprime/12'
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == 'false'

  Scenario Outline: Validate that <number> is prime=<result>
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
