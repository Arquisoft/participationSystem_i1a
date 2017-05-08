Feature: Being able to make a proposal
	As a logged user
	I want to write a proposal
	Because i have an interesting idea to propose

Scenario: Make a proposal
    Given Im a logged user "dani" with password "password"
    When i write "Football Tournament" as the proposal title
    And i write "new Footbal Tournament" as the proposal description
    And i set "Sports" as the proposal topic
    And i submit the proposal
    Then a proposal with title "Football Tournament" will be published