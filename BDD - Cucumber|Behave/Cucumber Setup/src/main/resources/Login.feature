Feature: Login Functionality
  To ensure users can access their account
  As a registered user
  I want to login using my credentials

  Scenario: Login with invalid password
    Given the user is on the login page
    When the user enters incorrect username and password
    Then an error message should be displayed
