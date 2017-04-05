Feature: Being able to vote a proposal
As a logged user
I want to vote a proposal
Because i want to make it happened
  Scenario: vote a proposal
  
    Given Im a logged user with name "pepe" and password "1234"
     When i click "vote a proposal"
     Then it will be voted
      And other logged users will see it