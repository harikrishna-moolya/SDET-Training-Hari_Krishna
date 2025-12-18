Feature: Login Functionality
  To ensure users can access their account
  As a registered user
  I want to login using my credentials
  Scenario: Login with invalid password
    Given the user is on the login page
    When the user enters username "user@example.com" and password "wrongpass"
    Then an error message should be displayed
  Scenario: Login with invalid password
    Given the user is on the login page
    When the user enters correct username
    And Wrong password
    Then an error message should be displayed
  Scenario: Login with invalid username
    Given the user is on the login page
    When the user enters wrong username
    And correct password
    Then an error message should be displayed
  Scenario: Login with invalid username and passwpord
    Given the user is on the login page
    When the user enters wrong username
    And correct password
    Then an error message should be displayed
