Feature: As a user I want to be able to
  extract car reg numbers and perform a search
  then compare results.

  @carSearch
  Scenario: Extract Car Reg Numbers, Search and Compare Results
    Given I extract the plate numbers
    When I am on webuyanycar homepage
    And I accept all cookies
    Then I fetch and compare vehicle details using the registration number


