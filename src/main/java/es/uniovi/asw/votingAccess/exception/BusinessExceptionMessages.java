package es.uniovi.asw.votingAccess.exception;

public class BusinessExceptionMessages {

	public static final String NOT_IN_CENSUS = 
			"The given NIF does not correspond to any voter registered in the census";
	public static final String ALREADY_EVOTER = 
			"The user is already registered as e-voter";
	public static final String NOT_EVOTER = 
			"The user is not registered as e-voter";
	public static final String WRONG_PASSWORD = 
			"The given password is incorrect";
	public static final String IS_EVOTER =
			"The voter is an e-voter. He can't vote in person";
    public static final String HAS_ALREADY_VOTED =
            "The voter has already voted, he can't vote again";
	public static final String WRONG_ELECTORAL_BOARD = 
			"The voter belongs to the Electoral Board number %d, not %d";
}
