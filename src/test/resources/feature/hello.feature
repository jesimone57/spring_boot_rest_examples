Feature:  say hello

  Background:
    * url baseUrl
    * configure lowerCaseResponseHeaders = true

  Scenario: Hello using a path variable
    Given path 'hello/tom'
    When method get
    Then status 200
    And match header content-type contains 'text/plain'
    And match header content-type contains 'charset=utf-8'
    And match response == 'hello tom'

  Scenario: Hello using a url named parameter
    Given path 'hello2'
    And param name = 'fred'
    When method get
    Then status 200
    And match header content-type contains 'text/plain'
    And match header content-type contains 'charset=utf-8'
    And match response == 'hello fred'

  Scenario Outline: Hello to <name> should be hello <name>
    Given path 'hello2'
    And param name = <name>
    When method get
    Then status 200
    And match header content-type contains 'text/plain'
    And match header content-type contains 'charset=utf-8'
    And match response == <response>
    Examples:
      | name      | response
      | 'tom'     | 'hello tom'
      | 'fred'    | 'hello fred'
      | 'marcus'  | 'hello marcus'

