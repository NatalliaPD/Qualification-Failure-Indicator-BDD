Feature: Failure date as Valid Until date in red on evaluation that's a part of task
  
  " As an admin or supervisor
  I would like to see an indicator to note when a user has failed an evaluation. 
  That indicator should be a change in due date to the date of the failure and the Valid Until date will be red."

  Background: 
    Given Admin user logs in <username> and <password>
    

  @RegressionTest
  Scenario Outline: User should find change in due date to the date of the failure and the Valid Until date in red
    When Admin user navigates to Report
    And clicks Status
    And selects Task List
    And selects employee
    And moves employee to Available
    And expands Task Filters
    Then should be able to select a task
    And moves task to Available
    And runs Task Status Report
    And expands task
    Then clicks on evaluation
    Then Evaluation failure date should be the new Valid Until date in red

    Examples: 
      | username | password                 |
      | NataDyer | V2KyZnaiBVdt9Dp1GpVWwA== |
