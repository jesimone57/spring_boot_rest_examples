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
    Given path '/primes'
    And param start = <start>
    And param end = <end>
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

  Scenario: primes error response with no required parameters
    Given path '/primes'
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 2 errors'
    And match response.errors == '#[2]'
    And match response.errors[*].error_message contains 'end must be a positive number or 0'
    And match response.errors[*].error_message contains 'start must be a positive number or 0'

  Scenario: primes error response with missing start parameter
    Given path '/primes'
    And param end = 10
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'start must be a positive number or 0'
    And match response.errors[0].error_field == 'start'
    And match response.errors[0].error_value == null

  Scenario: primes error response with start parameter of wrong type
    Given path '/primes'
    And param start = 'a'
    And param end = 10
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'Failed to convert property value of type'
    And match response.errors[0].error_field == 'start'
    And match response.errors[0].error_value == 'a'

  Scenario: primes error response with missing end parameter
    Given path '/primes'
    And param start = 1
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'end must be a positive number or 0'
    And match response.errors[0].error_field == 'end'
    And match response.errors[0].error_value == null

  Scenario: primes error response with end parameter of wrong type
    Given path '/primes'
    And param start = 1
    And param end = 'z'
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'Failed to convert property value of type'
    And match response.errors[0].error_field == 'end'
    And match response.errors[0].error_value == 'z'

  Scenario: primes error response with range reversed
    Given path '/primes'
    And param start = 6
    And param end = 5
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.uri_path == '/primes'
    And match response.error_message == 'Invalid range.  start value=6 must be before end value=5.'
    And match response.errors == '#[0]'

  Scenario: Primes error response when start is 0
    Given path '/primes'
    And param start = 0
    And param end = 5
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.uri_path == '/primes'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message == 'must be greater than 0'
    And match response.errors[0].error_field == 'start'
    And match response.errors[0].error_value == '0'

