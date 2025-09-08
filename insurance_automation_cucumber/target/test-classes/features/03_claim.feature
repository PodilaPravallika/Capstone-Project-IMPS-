Feature: Insurance Claims Management

  Scenario Outline: Submit a new claim
    Given I am on the Claims page
    When I select policy "<Policy>"
    And I enter description "<Description>"
    And I enter claim amount "<Amount>"
    And I submit the claim
    Then I should see claim with description "<Description>" in the list

    Examples:
      | Policy              | Description | Amount |
      | 14 - john - VEHICLE | Sugar       | 2000   |
      | 12 - vasu - LIFE    | Accident    | 3000   |

  Scenario Outline: Delete an existing claim
    Given I am on the Claims page
    When I click on the Delete button of claim "<Description>"
    Then the claim with description "<Description>" should be removed from the list

    Examples:
      | Description |
      | Sugar       |
      | Accident    |
