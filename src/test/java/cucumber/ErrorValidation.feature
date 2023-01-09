
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Negative test of logging into the Ecommerce page
    Given I landed on Ecommerce page
    When Logged in with username <name> and password<password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name           | password    |  
      | amal@gmail.com | amlJohn1@   |   
