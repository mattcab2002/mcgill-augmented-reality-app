Feature: User can add locations to their favorites

  Scenario: User adds a location to their favorites
    Given I am logged in to my account
    And I am on the locations page
    When I select a specific location from the list
    Then I should see information about the location, including its name and address
    And I should see an add to favorites button
    When I click on the add to favorites button
    Then the location should be added to my favorites
    And I should see a confirmation message that the location has been added
    When I navigate to my favorites page
    Then I should see the location in my favorites list
    And I should be able to navigate to the location from the favorites page