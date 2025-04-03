Feature: User gets list of posts

  Scenario: Get list of posts
    Given There is a list of posts available
    When I ask for the complete list
    Then The list has at least one result

  Scenario: Create new post
    Given I want to create a new post
    When I create a new post
    Then The response code should be 201

  Scenario: Update post
    Given I want to update a post
    When I update a post
    Then The post is updated