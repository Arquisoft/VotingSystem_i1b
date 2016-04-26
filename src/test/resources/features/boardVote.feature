Feature: The electoral board submits a vote to the system

	Scenario: The electoral board submits successfully a vote
	Given the electoral board chooses the option of submit a vote
	When the electoral board selects a correct voting option
	Then the vote selected by the board is added to the database