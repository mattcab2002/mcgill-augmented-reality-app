Feature: User can log out of their account

  Scenario: User logs out of their account
    Given I am logged in to my account
    When I click on the logout button
    Then I should be redirected to the login page
    And I should see a message confirming that I have logged out
    And I should not have access to the social feature