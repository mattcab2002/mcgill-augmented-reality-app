Feature: User can delete their account

  Scenario: User deletes their account
    Given I am logged in to my account
    When I click on the delete account button
    Then I should see a confirmation message
    When I confirm the deletion
    Then I should be redirected to the homepage
    And I should see a message confirming that my account has been deleted
    And I should not have access to the social feature