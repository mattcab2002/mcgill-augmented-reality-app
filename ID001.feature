Feature: Admin can store data in a database

  Scenario: Admin stores data in the database
    Given I am logged in as an admin
    When I access the database
    Then I should see that it is empty
    When I add data to the database
    Then I should see that it is stored in the database