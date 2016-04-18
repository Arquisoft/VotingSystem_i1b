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
}
