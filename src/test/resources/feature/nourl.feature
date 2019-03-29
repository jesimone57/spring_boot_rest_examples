Feature:  No URLfound proper error response

  Background:
    * url 'http://localhost:8080'
    * configure lowerCaseResponseHeaders = true

  Scenario: Invalid URL response
    Given path '/hello'
    When method get
    Then status 404
    And match header content-type contains 'application/json'
    And match header content-type contains 'charset=utf-8'
    And match response.status_code == 404
    And match response.method == 'GET'
    And match response.error_message == 'The URL you have reached is not in service at this time'
    And match response.errors == '#[0]'
