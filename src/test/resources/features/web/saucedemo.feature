@web @sauce
Feature: Checkout

  @tc-001
  Scenario: As a user i can login and checkout
    Given user is on sauce demo homepage
    When user login with valid username "standard_user"
    Then login should be success
    And user choose filter "Price (high to low)"
    Then user can validate the filter applied correctly by descending order
    And user add to cart product with price "$15.99"
    And user continue checkout by click cart icon
    And user input checkout information by input "eri","test","53113"
    And user finish checkout
    Then checkout should be success


  @tc-002
  Scenario: As a user I'm unable to login with locked account
    Given user is on sauce demo homepage
    When user login with valid username "locked_out_user"
    Then user should be locked

