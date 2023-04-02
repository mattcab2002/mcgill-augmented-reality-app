Feature: User can send and receive friend requests

  Scenario: User sends and receives friend requests
    Given I am logged in to my account
    And I am on the social feature page
    When I search for a user by name or email
    Then I should see a list of matching users
    When I click on the add friend button for a specific user
    Then a friend request should be sent to the user
    And the user should receive a notification of the friend request
    When the user accepts my friend request
    Then I should receive a notification of the accepted request
    And the user should be added to my friends list
    And I should be added to the user's friends list