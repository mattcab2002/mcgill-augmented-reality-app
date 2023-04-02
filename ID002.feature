Feature: User can sign up for an account

  Scenario: User signs up for an account
    Given I am on the app's homepage
    When I click on the sign-up button
    Then I should be redirected to the sign-up page
    When I enter my name, email, and password
    And I click on the sign-up button
    Then I should see a confirmation message
    And I should be logged in to my new account