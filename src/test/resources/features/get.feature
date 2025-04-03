Feature: User gets list of posts

  Scenario: Get list of posts
    Given There is a list of posts available
    When I ask for the complete list
    Then The list has at least one result