Feature: User can log in to their account

  Scenario: User logs in to their account
    Given I am on the app's login page
    When I enter my email and password
    And I click on the login button
    Then I should be redirected to my account page
    And I should see a welcome message with my name
    And I should have access to the social feature