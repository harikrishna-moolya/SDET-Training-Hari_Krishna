@smoke @regression
Feature: Login functionality

  Scenario: Login with valid credentials
    Given the user is on the login page
    When the user enters login details
      | username       | password |
      | test@a.com     | 123456   |
      | hk01@gmail.com | 1432     |
    Then the user should be redirected to the dashboard
