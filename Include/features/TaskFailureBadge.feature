Feature: Task Failure Badge

  Background: 
    Given Admin user logs in <username> and <password>

  Scenario Outline: Task failure badge should display when a connected company's user
    failed tasks assigned via Organizational Tool

    And Admin User cicks on Reports
    Then should select View Results for employees of a Connected Company
    And selects available Project
    And selects employees
    And presses Run Report button
    When Admin user clicks Expand All button
    Then Task failure badge should be displayed on failed tasks

    Examples: 
      | username | password                 |
      | NataDyer | V2KyZnaiBVdt9Dp1GpVWwA== |
