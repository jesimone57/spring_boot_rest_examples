Feature:  palindrome numbers

  Background:
    * url 'http://localhost:8080'
    * configure lowerCaseResponseHeaders = true

  Scenario: palidromes sucess response
    Given path '/palindromes'
    And param start = 1
    And param end = 10
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == {numbers:[1,2,3,4,5,6,7,8,9], start:1, end:10, count:9, type:Palindrome}

  Scenario: palidromes error response with no rewquired parameters
    Given path '/palindromes'
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 2 errors'
    And match response.errors[*].error_message contains 'end must be a positive number or 0'
    And match response.errors[*].error_message contains 'start must be a positive number or 0'

  Scenario: palidromes error response with missing start parameter
    Given path '/palindromes'
    And param end = 10
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors[0].error_message contains 'start must be a positive number or 0'

  Scenario: palidromes error response with start parameter of wrong type
    Given path '/palindromes'
    And param start = 'a'
    And param end = 10
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors[0].error_message contains 'Failed to convert property value of type'
    And match response.errors[0].error_field == 'start'

  Scenario: palidromes error response with missing end parameter
    Given path '/palindromes'
    And param start = 1
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors[0].error_message contains 'end must be a positive number or 0'

  Scenario: palidromes error response with end parameter of wrong type
    Given path '/palindromes'
    And param start = 1
    And param end = 'z'
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors[0].error_message contains 'Failed to convert property value of type'
    And match response.errors[0].error_field == 'end'

  Scenario: palidromes error response with range reversed
    Given path '/palindromes'
    And param start = 6
    And param end = 5
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message == 'Invalid range.  start value=6 must be before end value=5.'
    And match response.errors == '#[]'
