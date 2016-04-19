Feature: A voter registers to vote electronically

	Scenario: The voter does not exist in the census
	Given There is not any voter in the census with the DNI introduced by the voter
	When the voter gives it's non-existing NIF
	Then the voter receives a message telling he is not in census
	
	Scenario: The voter is already register for voting electronically
	Given The voter is already registered for the electronic vote
	When the voter gives it's NIF
	Then the voter receives a message telling he is already registered
	
	Scenario: The voter exists and it is not registered for online voting
	Given the voter exists but not registered for online voting
	When the voter introduces it's NIF
	Then the voter is registered for online voting
    