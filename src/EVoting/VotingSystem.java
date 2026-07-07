package EVoting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VotingSystem {
	private List<Voter> registeredVoters;
	private Map<String, String> voterCredentials;
	private List<String> candidates;
	private Map<String, Integer> voteCounts;
	private Set<String> votedVoters;

	public VotingSystem() {
		registeredVoters = new ArrayList<>();
		voterCredentials = new HashMap<>();
		candidates = new ArrayList<>();
		voteCounts = new HashMap<>();
		votedVoters = new HashSet<>();
	}

	public boolean registerVoter(String voterID, String password) {
		if (voterCredentials.containsKey(voterID) || password.length() < 8) {
			return false;
		}
		Voter newVoter = new Voter(voterID, password);
		registeredVoters.add(newVoter);
		voterCredentials.put(voterID, password);
		return true;
	}

	public boolean authenticateVoter(String voterID, String password) {
		return voterCredentials.containsKey(voterID) && voterCredentials.get(voterID).equals(password);
	}

	public void addCandidate(String candidateName) {
		candidates.add(candidateName);
		voteCounts.put(candidateName, 0);
	}

	public boolean vote(String voterID, String password, String candidate) {
		if (!authenticateVoter(voterID, password)) {
			return false;
		}
		if (!voteCounts.containsKey(candidate)) {
			return false;
		}
		if (votedVoters.contains(voterID)) {
			return false;
		}

		voteCounts.put(candidate, voteCounts.get(candidate) + 1);
		votedVoters.add(voterID);
		return true;
	}

	public Map<String, Integer> getResults() {
		return voteCounts;
	}

	public List<Voter> getRegisteredVoters() {
		return registeredVoters;
	}
}