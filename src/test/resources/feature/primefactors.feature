Feature:  Prime Factors

  Background:
    * url baseUrl
    * configure lowerCaseResponseHeaders = true
    * def keys = function(o){ return o.keySet() }
    * def values = function(o){ return o.values() }
    * def size = function(o){ return o.size() }

  Scenario Outline: Find all the the prime factors of <number> are <result>
    Given path '/primefactors/<number>'
    When method get
    Then status 200
    And match header content-type == 'application/json'
    And match response == <result>
    Examples:
      | number | result    |
      | 2      | [2]       |
      | 3      | [3]       |
      | 6      | [2,3]     |
      | 10     | [2,5]     |
      | 100    | [2,2,5,5] |
      | 10001  | [73,137]  |
      | 100001 | [11,9091] |

  Scenario Outline: Find all the the prime factors in the range from <start> to <end> are <result>
    Given path '/primefactors'
    And param start = <start>
    And param end = <end>
    When method get
    Then status 200
    And match header content-type == 'application/json'
    And def result = <result>
    And match response == {numbers:<result>, start:<start>, end:<end>, count: '#(size(result))', type:PrimeFactors}
    Examples:
      | start | end | result
      | 8     | 10  | {8: [2,2,2], 9:[3,3], 10:[2,5]}
      | 13    | 16  | {13: [13], 14:[2,7], 15:[3,5], 16:[2,2,2,2]}


  Scenario: Prime factors error response with no required parameters
    Given path '/primefactors'
    When method get
    Then status 400
    And match header content-type == 'application/json'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 2 errors'
    And match response.errors == '#[2]'
    And match response.errors[*].error_message contains 'end must be a positive number or 0'
    And match response.errors[*].error_message contains 'start must be a positive number or 0'

  Scenario: Prime factors error response with missing start parameter
    Given path '/primefactors'
    And param end = 10
    When method get
    Then status 400
    And match header content-type == 'application/json'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'start must be a positive number or 0'
    And match response.errors[0].error_field == 'start'
    And match response.errors[0].error_value == null

  Scenario: Prime factors error response with start parameter of wrong type
    Given path '/primefactors'
    And param start = 'a'
    And param end = 10
    When method get
    Then status 400
    And match header content-type == 'application/json'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'Failed to convert property value of type'
    And match response.errors[0].error_field == 'start'
    And match response.errors[0].error_value == 'a'

  Scenario: Prime factors error response with missing end parameter
    Given path '/primefactors'
    And param start = 1
    When method get
    Then status 400
    And match header content-type == 'application/json'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'end must be a positive number or 0'
    And match response.errors[0].error_field == 'end'
    And match response.errors[0].error_value == null

  Scenario: Prime factors error response with end parameter of wrong type
    Given path '/primefactors'
    And param start = 1
    And param end = 'z'
    When method get
    Then status 400
    And match header content-type == 'application/json'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message contains 'Failed to convert property value of type'
    And match response.errors[0].error_field == 'end'
    And match response.errors[0].error_value == 'z'

  Scenario: Prime factors error response with range reversed
    Given path '/primefactors'
    And param start = 6
    And param end = 5
    When method get
    Then status 400
    And match header content-type == 'application/json'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.uri_path == '/primefactors'
    And match response.error_message == 'Invalid range.  start value=6 must be before end value=5.'
    And match response.errors == '#[0]'

  Scenario: Prime factors error response when start is 0
    Given path '/primefactors'
    And param start = 0
    And param end = 5
    When method get
    Then status 400
    And match header content-type == 'application/json'
    And match response.status_code == 400
    And match response.method == 'GET'
    And match response.uri_path == '/primefactors'
    And match response.error_message contains 'BeanPropertyBindingResult: 1 errors'
    And match response.errors == '#[1]'
    And match response.errors[0].error_message == 'must be greater than 0'
    And match response.errors[0].error_field == 'start'
    And match response.errors[0].error_value == '0'

