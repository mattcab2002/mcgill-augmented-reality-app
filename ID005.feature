Feature: User can edit their account information

  Scenario: User edits their account information
    Given I am logged in to my account
    When I click on the edit account button
    Then I should be redirected to the account edit page
    When I update my name, email, or password
    And I click on the save button
    Then I should see a confirmation message
    And my account information should be updated
    And I should be able to log in with my updated information