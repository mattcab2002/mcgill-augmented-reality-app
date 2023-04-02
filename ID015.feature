Feature: User can remove locations from their favorites

  Scenario: User removes a location from their favorites
    Given I am logged in to my account
    And I am on the favorites page
    When I select a specific location from my favorites list
    Then I should see information about the location, including its name and address
    And I should see a remove from favorites button
    When I click on the remove from favorites button
    Then the location should be removed from my favorites
    And I should see a confirmation message that the location has been removed
    When I navigate back to the favorites page
    Then I should not see the location in my favorites list