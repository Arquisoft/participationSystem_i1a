Feature: Being able to vote a proposal
	As a logged user
	I want to vote a proposal
	Because i want to make it happen
	
Scenario: vote a proposal 
    Given Im a logged user "dani" with password "password"
     When i open the proposal "Title2"
     And i upvote the proposal
     Then the number of positive votes will be "1"