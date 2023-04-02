Feature: User can search different McGill locations

  Scenario: User searches for McGill locations
    Given I am on the app's homepage
    When I click on the McGill locations button
    Then I should be redirected to the McGill locations page
    When I select a specific location from the dropdown menu
    And I click on the search button
    Then I should see a map with the selected location highlighted
    And I should be able to navigate to the selected location