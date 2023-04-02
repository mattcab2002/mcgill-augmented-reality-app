Feature: User can get information about buildings at McGill

  Scenario: User gets information about buildings
    Given I am logged in to my account
    And I am on the buildings page
    When I select a specific building from the list
    Then I should see information about the building, including its name, address, and availability
    And I should be able to navigate to the building's location
    When I click on the availability tab
    Then I should see information about the building's availability, including its open hours and any special closures or events