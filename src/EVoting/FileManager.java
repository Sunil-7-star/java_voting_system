package EVoting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
	private static final String FILE_PATH = "votes.txt";

	public static void saveVotes(Map<String, Integer> voteCounts) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
				writer.write(entry.getKey() + "," + entry.getValue());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, Integer> loadVotes() {
		Map<String, Integer> voteCounts = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				voteCounts.put(parts[0], Integer.parseInt(parts[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return voteCounts;
	}
}