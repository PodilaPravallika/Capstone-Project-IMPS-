Feature: Login Functionality for Insurance App

  Scenario Outline: Login attempts
    Given the user is on the login page
    When the user enters username "<username>" and password "<password>"
    And clicks the login button
    Then <result>

    Examples:
      | username | password   | result                                                                 |
      | admin    | admin123   | the user should be redirected to the homepage "http://localhost:8081/insurance-mysql-crud/dashboard" |
      | wrong    | wrong123   | an error message "Invalid credentials" should be displayed             |
