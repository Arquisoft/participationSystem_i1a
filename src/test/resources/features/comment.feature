Feature: Being able to comment a proposal
As a logged user
I want to write a comment
Because i have an something to say about a proposal
  Scenario: Make a proposal
  
    Given Im a logged user "pepe" with password "1234"
     When i click "See proposals"
      And i choose proposal "Proposal1"
      And i click "Comment"
      And i write "new Comment"
     Then it will be publish
      And it should be able to be commented