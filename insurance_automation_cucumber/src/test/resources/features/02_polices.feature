Feature: Insurance Policies Management

  Scenario Outline: Add a new policy
    Given I am on the Insurance Dashboard
    When I click on "Manage Policies"
    And I click on "New Policy"
    And I fill the policy form with type "<Type>", holder "<Holder>", age "<Age>", coverage "<Coverage>", and start date "<StartDate>"
    And I submit the form
    Then I should see "<Holder>" policy in the list

    Examples:
      | Type    | Holder | Age | Coverage | StartDate   |
      | LIFE    | ram    | 56  | 200000   | 09-02-2025  |
      | HEALTH  | rani   | 45  | 150000   | 10-10-2025  |
      | VEHICLE | john   | 30  | 500000   | 11-01-2025  |

  Scenario Outline: Edit an existing policy
    Given I am on the Manage Policies page
    When I click on the Edit button of "<Holder>"
    And I update the age to "<NewAge>" and coverage to "<NewCoverage>"
    And I submit the form
    Then I should see "<Holder>" policy updated with age "<NewAge>" and coverage "<NewCoverage>"

    Examples:
      | Holder | NewAge | NewCoverage |
      | ram    | 58     | 210000      |
      | rani   | 47     | 160000      |

  Scenario Outline: Delete an existing policy
    Given I am on the Manage Policies page
    When I click on the Delete button of "<Holder>"
    Then the policy for "<Holder>" should be removed from the list

    Examples:
      | Holder |
      | ram    |
      | rani   |
