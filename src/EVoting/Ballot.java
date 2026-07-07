package EVoting;

public class Ballot {
    private String voterID;
    private String candidateID;

    public Ballot(String voterID, String candidateID) {
        this.voterID = voterID;
        this.candidateID = candidateID;
    }

    public void submitBallot() {
        System.out.println("Ballot submitted by: " + voterID);
    }
}
