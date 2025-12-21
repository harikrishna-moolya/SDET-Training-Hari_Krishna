Feature: PetStore User CRUD API

  Background:
    Given API is configured

  # ---------- POSITIVE TEST CASES ----------

  Scenario: Create a user successfully
    When user creates a new user
    Then response status code should be 200

  Scenario: Retrieve existing user
    When user retrieves the user
    Then response status code should be 200

  Scenario: Update existing user
    When user updates the user
    Then response status code should be 200

  Scenario: Delete existing user
    When user deletes the user
    Then response status code should be 200

  # ---------- NEGATIVE TEST CASES ----------

  Scenario: Retrieve non existing user
    When user retrieves non existing user
    Then response status code should be 404

  Scenario: Delete non existing user
    When user deletes non existing user
    Then response status code should be 404

  Scenario: Create user with empty payload
    When user creates user with empty payload
    Then response status code should be 400
