Feature:  say hello

  Background:
    * url baseUrl
    * configure lowerCaseResponseHeaders = true

  Scenario: Hello using a path variable
    Given path '/hello/tom'
    When method get
    Then status 200
    And match header content-type contains 'text/plain'
    And match header content-type contains 'charset=utf-8'
    And match response == 'hello tom'

  Scenario: Hello using a url named parameter
    Given path '/hello2'
    And param name = 'fred'
    When method get
    Then status 200
    And match header content-type contains 'text/plain'
    And match header content-type contains 'charset=utf-8'
    And match response == 'hello fred'

