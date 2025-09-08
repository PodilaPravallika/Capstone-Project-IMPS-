Feature: Premium Calculation

  Scenario Outline: Validate premium calculation with different inputs
    Given user is on the Premium Calculation page
    When user enters age "<age>"
    And user enters coverage amount "<coverage>"
    And user selects policy type "<policyType>"
    And user submits the premium calculation
    Then calculated premium should be displayed

    Examples:     
	  | age | coverage | policyType        |
	  | 25  | 100000   | Life Insurance    |
	  | 35  | 200000   | Health Insurance  |
	  | 55  | 300000   | Vehicle Insurance |
       

  Scenario Outline: Validate invalid inputs for premium calculation
    Given user is on the Premium Calculation page
    When user enters age "<age>"
    And user enters coverage amount "<coverage>"
    And user selects policy type "<policyType>"
    And user submits the premium calculation
    Then error message should be displayed

    Examples:
      | age | coverage | policyType 		 |
      | -5  | 100000   | Life Insurance      |
      | 40  | -20000   | Health Insurance    |
