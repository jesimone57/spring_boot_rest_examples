Feature:  palindrome numbers

  Background:
    * url baseUrl
    * configure lowerCaseResponseHeaders = true

  Scenario: palindromes success response
    Given path '/palindromes'
    And param start = 1
    And param end = 10
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response == {numbers:[1,2,3,4,5,6,7,8,9], start:1, end:10, count:9, type:Palindrome}

  Scenario: palindromes error response with no required parameters
    Given path '/palindromes'
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

  Scenario: palindromes error response with missing start parameter
    Given path '/palindromes'
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

  Scenario: palindromes error response with start parameter of wrong type
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
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'Failed to convert property value of type'
    And match response.errors[0].error_field == 'start'
    And match response.errors[0].error_value == 'a'

  Scenario: palindromes error response with missing end parameter
    Given path '/palindromes'
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

  Scenario: palindromes error response with end parameter of wrong type
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
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'Failed to convert property value of type'
    And match response.errors[0].error_field == 'end'
    And match response.errors[0].error_value == 'z'

  Scenario: palindromes error response with range reversed
    Given path '/palindromes'
    And param start = 6
    And param end = 5
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.uri_path == '/palindromes'
    And match response.error_message == 'Invalid range.  start value=6 must be before end value=5.'
    And match response.errors == '#[0]'

  Scenario: palidromes error response when start is 0
    Given path '/palindromes'
    And param start = 0
    And param end = 5
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.uri_path == '/palindromes'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message == 'must be greater than 0'
    And match response.errors[0].error_field == 'start'
    And match response.errors[0].error_value == '0'

