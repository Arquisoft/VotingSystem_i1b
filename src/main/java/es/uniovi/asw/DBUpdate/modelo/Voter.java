package es.uniovi.asw.DBUpdate.modelo;

public class Voter {
	private String nif;
	private String name;
	private String email;
	private Integer electoralBoard;
	private String password;
	private boolean hasVoted;
	private boolean eVoter;
	
	public Voter(String nif){
		this.nif = nif;
	}
	
	public Voter(String nif, String name, String email, Integer electoralBoard, String password) {
		this(nif);
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
	
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getElectoralBoard() {
		return electoralBoard;
	}
	public void setElectoralBoard(Integer electoralBoard) {
		this.electoralBoard = electoralBoard;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getHasVoted() {
		return hasVoted;
	}
	public void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}
	
	public boolean isEVoter() {
		return eVoter;
	}
	
	public void setEVoter(boolean eVoter) {
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
