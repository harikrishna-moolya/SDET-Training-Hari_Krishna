Feature: User CRUD Operations

  Scenario: Perform complete CRUD flow
    Given API is configured
    When user creates a new user
    Then response status code should be 200
    When user retrieves the user
    Then response status code should be 200
    When user updates the user
    Then response status code should be 200
    When user deletes the user
    Then response status code should be 200
    When user fetches invalid user
    Then response status code should be 404
