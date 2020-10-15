Feature:  No URLfound proper error response

  Background:
    * url baseUrl
    * configure lowerCaseResponseHeaders = true

  Scenario: Invalid URL response
    Given path '/hello'
    When method get
    Then status 404
    And match header content-type == 'application/json'
    And match response.status_code == 404
    And match response.method == 'GET'
    And match response.error_message == 'The URL you have reached is not in service at this time'
    And match response.errors == '#[0]'
