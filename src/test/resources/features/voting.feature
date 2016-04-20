Feature: A voter logs in and votes electronically

	Scenario: The NIF introduced by the voter does not correspond to a NIF in the census
	Given the NIF of the voter who tries to vote does not exist
	When the voter introduces his NIF and password
	Then the application shows a message showing the problem
	
	Scenario: The voter is not registered to vote electronically
	Given the NIF and password of the voter is correct but the voter is not registered to vote electronically
	When the voter inputs his NIF and password
	Then the application tells the voter he is not registered
	
	Scenario: The password introduced by the voter is wrong
	Given the NIF introduced by the voter exists
	When the voter introduces a wrong password
	Then the program shows the error to the voter
	
	Scenario: The user logs in and successfully submits a vote
	Given the user has already logged in
	When the user submits his vote
	Then the option selected by the user is added to the database
	And the voter who voted is marked as already voted, so he can't vote again