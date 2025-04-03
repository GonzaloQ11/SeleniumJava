Feature: Card Browser

  Scenario: Cards display complete information
    Given the card project is launched
    When I view the list of cards
    Then each card displays a name, image, and link

  Scenario: Navigate to a character's detail page
    Given the card project is launched
    When I click on a card
    Then I should be navigated to the character detail page

  Scenario: Character details are shown correctly
    Given the card project is launched
    When I open a character card
    Then the name, gender, species, and status are displayed

  Scenario: Navigate back to the homepage
    Given the card project is launched
    When I open a character card
    And I click the Home button
    Then I should be redirected to the homepage

  Scenario: Add button opens the new card form
    Given the card project is launched
    When I click the Add button
    Then the new card form should be visible

  Scenario: Scroll to top using the Top button
    Given the card project is launched
    When I scroll to the bottom
    And I click the Top button
    Then I should be redirected to the top of the page
