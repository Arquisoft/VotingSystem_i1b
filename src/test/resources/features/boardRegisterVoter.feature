Feature: The electoral board marks that a voter has already voted

	Scenario: A voter is marked successfully as already voted
	Given there exist a vote with NIF 55824978L, who has not already voted and is not registered as e voter
	When the electoral board introduces the NIF to mark it as already voted
	Then the voter with the NIF is marked as voted