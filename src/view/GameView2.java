package view;

import interface_adapter.search_bar.SearchBarViewModel;

import javax.swing.*;
import java.awt.*;

public class GameView2 extends JFrame {
    private JTextField searchBar;
    private JButton playButton, submitButton, skipButton;
    private JTextArea guessList; // display previous guesses
    public GameView2() {
        this.setTitle("Game View");
        this.setSize(600, 800);
        setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchBar = new JTextField();
        this.add(searchBar, BorderLayout.SOUTH);

        playButton = new JButton("Play");
        skipButton = new JButton("Skip");
        submitButton = new JButton("Submit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(skipButton);
        buttonPanel.add(submitButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        guessList = new JTextArea();
        this.add(guessList, BorderLayout.CENTER);

        // Add ActionListener to buttons and search bar
        // Implement game logic and audio handling
    }

    // For Testing purposes
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView2 app = new GameView2();
            app.setVisible(true);
        });
    }



}
