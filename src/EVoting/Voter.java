package EVoting;


public class Voter {
	private String voterID;
	private String password;

	public Voter(String voterID, String password) {
		this.voterID = voterID;
		this.password = password;
	}

	public String getVoterID() {
		return voterID;
	}

	public String getPassword() {
		return password;
	}
}