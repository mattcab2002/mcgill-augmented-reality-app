Feature: User can upload their weekly schedule

  Scenario: User uploads their weekly schedule
    Given I am logged in to my account
    And I am on the schedule page
    When I click on the upload schedule button
    Then I should be able to select a file from my computer
    And I should see a confirmation message that my schedule has been uploaded
    And my schedule should be visible on the page
    When I navigate to a specific day in my schedule
    Then I should see a list of classes and their corresponding locations and times
    And I should receive reminders for each of my classes