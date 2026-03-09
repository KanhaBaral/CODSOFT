package com.numbergame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGame extends JFrame {
    private final Random random = new Random();

    private int minRange = 1;
    private int maxRange = 100;
    private int target;
    private int maxAttempts = 10;
    private int attemptsLeft;

    private int roundsPlayed = 0;
    private int roundsWon = 0;
    private int totalAttemptsUsed = 0;

    private final JLabel feedbackLabel = new JLabel("Welcome! Set range and start a round.");
    private final JLabel attemptsLabel = new JLabel();
    private final JLabel scoreLabel = new JLabel();
    private final JLabel roundsLabel = new JLabel();

    private final JTextField guessField = new JTextField(10);
    private final JButton guessButton = new JButton("Guess");
    private final JButton newRoundButton = new JButton("New Round");

    private final JSpinner minSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
    private final JSpinner maxSpinner = new JSpinner(new SpinnerNumberModel(100, 2, Integer.MAX_VALUE, 1));
    private final JSpinner attemptsSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 1));

    public NumberGame() {
        super("Number Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        pack();
        setLocationRelativeTo(null);
    }

    private void initUI() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Min:"));
        top.add(minSpinner);
        top.add(new JLabel("Max:"));
        top.add(maxSpinner);
        top.add(new JLabel("Max attempts:"));
        top.add(attemptsSpinner);
        JButton applySettings = new JButton("Apply Settings");
        top.add(applySettings);

        applySettings.addActionListener(e -> applySettings());

        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        center.add(new JLabel("Enter your guess:"), gbc);
        gbc.gridx = 1; center.add(guessField, gbc);
        gbc.gridx = 2; center.add(guessButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
        center.add(feedbackLabel, gbc);

        gbc.gridy = 2; center.add(attemptsLabel, gbc);
        gbc.gridy = 3; center.add(scoreLabel, gbc);
        gbc.gridy = 4; center.add(roundsLabel, gbc);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(newRoundButton);
        JButton exitButton = new JButton("Exit");
        bottom.add(exitButton);

        guessButton.addActionListener(e -> onGuess());
        guessField.addActionListener(e -> onGuess());
        newRoundButton.addActionListener(e -> startNewRound());
        exitButton.addActionListener(e -> System.exit(0));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);

        updateScoreLabels();
        setControlsForRound(false);
    }

    private void applySettings() {
        try {
            int min = (Integer) minSpinner.getValue();
            int max = (Integer) maxSpinner.getValue();
            int attempts = (Integer) attemptsSpinner.getValue();
            if (min >= max) {
                JOptionPane.showMessageDialog(this, "Min must be less than Max.", "Invalid Range", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.minRange = min;
            this.maxRange = max;
            this.maxAttempts = attempts;
            feedbackLabel.setText(String.format("Settings applied: range %d..%d, attempts %d", minRange, maxRange, maxAttempts));
            setControlsForRound(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid settings: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startNewRound() {
        this.attemptsLeft = maxAttempts;
        this.target = random.nextInt(maxRange - minRange + 1) + minRange;
        roundsPlayed++;
        feedbackLabel.setText(String.format("New round started: Guess a number between %d and %d", minRange, maxRange));
        guessField.setText("");
        setControlsForRound(true);
        updateScoreLabels();
    }

    private void setControlsForRound(boolean running) {
        guessField.setEnabled(running);
        guessButton.setEnabled(running);
        newRoundButton.setEnabled(!running);
        minSpinner.setEnabled(!running);
        maxSpinner.setEnabled(!running);
        attemptsSpinner.setEnabled(!running);
        if (!running) {
            attemptsLabel.setText("Round not active. Click New Round to start.");
        } else {
            attemptsLabel.setText(String.format("Attempts left: %d", attemptsLeft));
        }
    }

    private void onGuess() {
        if (!guessField.isEnabled()) return;
        String text = guessField.getText().trim();
        int guess;
        try {
            guess = Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid integer.");
            return;
        }
        attemptsLeft--;
        totalAttemptsUsed++;

        if (guess == target) {
            roundsWon++;
            feedbackLabel.setText(String.format("Correct! The number was %d. You won this round.", target));
            setControlsForRound(false);
            updateScoreLabels();
            int option = JOptionPane.showConfirmDialog(this, "Play another round?", "Round Won", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) startNewRound();
            return;
        }

        if (guess < target) {
            feedbackLabel.setText("Too low.");
        } else {
            feedbackLabel.setText("Too high.");
        }

        if (attemptsLeft <= 0) {
            feedbackLabel.setText(String.format("Out of attempts! The number was %d.", target));
            setControlsForRound(false);
            updateScoreLabels();
            int option = JOptionPane.showConfirmDialog(this, "Play another round?", "Round Over", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) startNewRound();
            return;
        }

        attemptsLabel.setText(String.format("Attempts left: %d", attemptsLeft));
    }

    private void updateScoreLabels() {
        scoreLabel.setText(String.format("Total attempts used: %d", totalAttemptsUsed));
        roundsLabel.setText(String.format("Rounds: %d played, %d won", roundsPlayed, roundsWon));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGame ng = new NumberGame();
            ng.setVisible(true);
        });
    }
}
