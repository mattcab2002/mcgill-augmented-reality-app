Feature: User can replace their weekly schedule

  Scenario: User replaces their weekly schedule
    Given I am logged in to my account
    And I am on the schedule page
    When I click on the replace schedule button
    Then I should be able to select a new file from my computer
    And I should see a confirmation message that my schedule has been replaced
    And my new schedule should be visible on the page
    When I navigate to a specific day in my new schedule
    Then I should see a list of classes and their corresponding locations and times
    And I should be able to navigate to the correct location for each of my classes