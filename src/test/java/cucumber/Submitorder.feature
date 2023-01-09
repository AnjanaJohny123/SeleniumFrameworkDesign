
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background: 
Given I landed on Ecommerce page


 @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password<password>
    When I add the product <productName>  to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name           |    password   |  productName  |
      | amal@gmail.com |    AmalJohn1@ |   ZARA COAT 3 |
      
