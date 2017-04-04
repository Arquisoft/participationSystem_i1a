Feature: Being able to make a proposal
	As a logged user
	I want to write a proposal
	Because i have an interesting idea to propose
Scenario: Make a proposal

    Given Im a logged user with name "pepe" and password "1234"
    When i click "Write a proposal" button
    And i title it as "Football Tournament"
    And i choose the topic "Sports"
    And i write "new Footbal Tournament"
    And i click "Finish"
    Then it will be publish
    And it should be able to be voted
    And it should be able to be commented