package es.uniovi.asw.DBUpdate.modelo;

public class Voter {
	private String nif;
	private String name;
	private String email;
	private Integer electoralBoard;
	private String password;
	private boolean hasVoted;
	private boolean eVoter;
	
	public Voter(String nif, String name, String email, Integer electoralBoard, String password) {
		super();
		this.nif = nif;
		this.name = name;
		this.email = email;
		this.electoralBoard = electoralBoard;
		this.password = password;
	}
	
	public Voter(String nif, String name, String email, Integer electoralBoard, String password, 
			boolean hasVoted, boolean eVoter) {
		this(nif, name, email, electoralBoard, password);
		this.hasVoted = hasVoted;
		this.eVoter = eVoter;
	}
	
	private String getNif() {
		return nif;
	}
	private void setNif(String nif) {
		this.nif = nif;
	}
	private String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	private String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	private Integer getElectoralBoard() {
		return electoralBoard;
	}
	private void setElectoralBoard(Integer electoralBoard) {
		this.electoralBoard = electoralBoard;
	}
	private String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	private boolean getHasVoted() {
		return hasVoted;
	}
	private void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}
	
	private boolean isEVoter() {
		return eVoter;
	}
	
	private void setEVoter(boolean eVoter) {
		this.eVoter = eVoter;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voter other = (Voter) obj;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Voter [nif=" + nif + ", name=" + name + ", email=" + email + "]";
	}
	
}
