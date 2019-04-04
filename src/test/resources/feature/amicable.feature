Feature:  Amicable numbers

  Background:
    * url baseUrl
    * configure lowerCaseResponseHeaders = true

  Scenario Outline: Find Amicable numbers in the range from <start> to <end> are <result>
    Given path '/amicables'
    And param start = <start>
    And param end = <end>
    When method get
    Then status 200
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And def result = <result>
    And match response == {numbers: <result>, start: <start>, end: <end>, count: '#(result.length)', type: 'Amicable'}
    Examples:
      | start  | end    | result
      |      1 |   1000 | [[ 220, 284]]
      |   1000 |   2000 | [[1184,1210]]
      |   2000 |   3000 | [[2620,2924]]
      |   5000 |   6000 | [[5020,5564]]
      |   6000 |   7000 | [[6232,6368]]
      |  12000 |  20000 | [[12285,14595], [17296,18416]]
#     | 185000 | 200000 | [[185368,203432],[196724,202444]]


  Scenario: amicable error response with no required parameters
    Given path '/amicables'
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

  Scenario: amicables error response with missing start parameter
    Given path '/amicables'
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

  Scenario: amicables error response with start parameter of wrong type
    Given path '/amicables'
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

  Scenario: amicables error response with missing end parameter
    Given path '/amicables'
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

  Scenario: amicables error response with end parameter of wrong type
    Given path '/amicables'
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

  Scenario: amicables error response with range reversed
    Given path '/amicables'
    And param start = 6
    And param end = 5
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.uri_path == '/amicables'
    And match response.error_message == 'Invalid range.  start value=6 must be before end value=5.'
    And match response.errors == '#[0]'

  Scenario: amicables error response when start is 0
    Given path '/amicables'
    And param start = 0
    And param end = 5
    When method get
    Then status 400
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.uri_path == '/amicables'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message == 'must be greater than 0'
    And match response.errors[0].error_field == 'start'
    And match response.errors[0].error_value == '0'
