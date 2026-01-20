@login
Feature: Login to the application
  As a registered user
  I want to log in to the application
  So that I can access my dashboard

  Background:
    Given the user is on the login page

  @smoke
  Scenario: Login with valid credentials
    When the user enters valid username and password
    And clicks on the login button
    Then the user should be redirected to the dashboard

  @regression
  Scenario Outline: Login with multiple credentials
    When the user enters username "<username>" and password "<password>"
    And clicks on the login button
    Then "<loginResult>" should be displayed

    Examples:
      | username         | password | loginResult |
      | test@a.com       | 123456   | error       |
      | hk01@gmail.com   | 1432     | success     |
