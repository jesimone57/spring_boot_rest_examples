Feature:  say hello

  Background:
    * url 'http://localhost:8080'
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

  Scenario: Hello not found exception
    Given path '/hello'
    When method get
    Then status 404
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 404
    And match response.method == 'GET'
    And match response.error_message == 'The URL you have reached is not in service at this time'
    And match response.errors == '#[]'