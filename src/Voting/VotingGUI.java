package Voting;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import EVoting.VotingSystem;

public class VotingGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VotingSystem votingSystem;
	private JTextField voterIDFieldReg, voterIDFieldVote;
	private JPasswordField passwordFieldReg, passwordFieldVote;
	private JComboBox<String> candidateComboBox;
	private JButton registerButton, voteButton, countVotesButton;

	public VotingGUI() {
		votingSystem = new VotingSystem();

		setTitle("E-Voting System");
		setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();

		// Registration Tab
		JPanel regPanel = new JPanel(new FlowLayout());
		voterIDFieldReg = new JTextField(15);
		passwordFieldReg = new JPasswordField(15);
		registerButton = new JButton("Register");
		regPanel.add(new JLabel("Voter ID:"));
		regPanel.add(voterIDFieldReg);
		regPanel.add(new JLabel("Password (8 chars):"));
		regPanel.add(passwordFieldReg);
		regPanel.add(registerButton);
		tabbedPane.addTab("Register", regPanel);


		JPanel votePanel = new JPanel(new FlowLayout());
		voterIDFieldVote = new JTextField(15);
		passwordFieldVote = new JPasswordField(15);
		candidateComboBox = new JComboBox<>();
		voteButton = new JButton("Vote");
		votePanel.add(new JLabel("Voter ID:"));
		votePanel.add(voterIDFieldVote);
		votePanel.add(new JLabel("Password:"));
		votePanel.add(passwordFieldVote);
		votePanel.add(new JLabel("Candidate:"));
		votePanel.add(candidateComboBox);
		votePanel.add(voteButton);
		tabbedPane.addTab("Vote", votePanel);

		// Results Tab
		JPanel resultPanel = new JPanel(new FlowLayout());
		countVotesButton = new JButton("Count Votes");
		resultPanel.add(countVotesButton);
		tabbedPane.addTab("Results", resultPanel);

		add(tabbedPane, BorderLayout.CENTER);
		setSize(400, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);


		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String voterID = voterIDFieldReg.getText();
				String password = new String(passwordFieldReg.getPassword());
				if (votingSystem.registerVoter(voterID, password)) {
					JOptionPane.showMessageDialog(VotingGUI.this, "Registration Successful!");
				} else {
					JOptionPane.showMessageDialog(VotingGUI.this, "Error: Voter ID exists or invalid password.");
				}
			}
		});

		voteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String voterID = voterIDFieldVote.getText();
				String password = new String(passwordFieldVote.getPassword());
				String candidate = (String) candidateComboBox.getSelectedItem();
				if (votingSystem.vote(voterID, password, candidate)) {
					JOptionPane.showMessageDialog(VotingGUI.this, "Vote Cast Successfully!");
				} else {
					JOptionPane.showMessageDialog(VotingGUI.this, "Error: Invalid credentials or already voted.");
				}
			}
		});

		countVotesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(VotingGUI.this, votingSystem.getResults());
			}
		});

		setVisible(true);
		initializeCandidates();
	}


	private void initializeCandidates() {
		int numCandidates = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the number of candidates:"));
		for (int i = 1; i <= numCandidates; i++) {
			String candidateName = JOptionPane.showInputDialog(this, "Enter candidate name " + i + ":");
			votingSystem.addCandidate(candidateName);
			candidateComboBox.addItem(candidateName);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(VotingGUI::new);
	}
}