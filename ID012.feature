Feature: User can send and receive messages with their friends

  Scenario: User sends and receives messages with their friends
    Given I am logged in to my account
    And I am on the social feature page
    When I select a friend from my friends list
    Then I should see a chat window with my friend
    When I send a message to my friend
    Then my friend should receive the message
    And my friend should be able to respond to the message
    When I receive a message from my friend
    Then I should see the message in the chat window
    And I should be able to respond to the message
    When I click on my friend's name in the chat window
    Then I should be able to navigate to my friend's location