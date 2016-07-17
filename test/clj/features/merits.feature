Feature: Merits
  Adding merits to a user

  Scenario: Add merit
    Given I on the merit add page
    And I add a merit to 'Bob'
    Then Bob should have a merit

