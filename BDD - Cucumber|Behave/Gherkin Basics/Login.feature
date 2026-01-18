Feature: Login Functionality
  To ensure users can access their account
  As a registered user
  I want to login using my credentials

  Background:
    Given the user is on the login page

  Scenario: Login with invalid password
    When the user enters username "user@example.com" and password "wrongpass"
    Then an error message should be displayed

  Scenario: Login with invalid username
    When the user enters username "wronguser@example.com" and password "correctpass"
    Then an error message should be displayed

  Scenario: Login with invalid username and invalid password
    When the user enters username "wronguser@example.com" and password "wrongpass"
    Then an error message should be displayed

  Scenario: Login with valid username and password
    When the user enters username "user@example.com" and password "correctpass"
    Then the user should be logged in successfully
    And the user should be redirected to the home page
