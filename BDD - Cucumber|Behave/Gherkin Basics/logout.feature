Feature: Logout Functionality
  To ensure users can safely log out of the application
  As a registered user
  I want to logout from my account
  So that my session is securely ended

  Scenario: Successful logout from dashboard
    Given the user is logged in
    When the user clicks on the "Logout" link
    Then the user should be redirected to the login page
    And a confirmation message "You have been logged out" should be displayed


