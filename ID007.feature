Feature: User can input their desired location

  Scenario: User inputs their desired location
    Given I am on the app's homepage
    When I enter my desired location
    And I click on the search button
    Then I should be redirected to the results page
    And I should see a list of locations related to my search query
    And I should be able to navigate to a selected location