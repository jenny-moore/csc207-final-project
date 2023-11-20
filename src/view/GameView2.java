package view;

import javax.swing.*;
import java.awt.*;

public class GameView2 extends JFrame {
    private JTextField searchBar;
    private JButton playButton, submitButton;
    private JTextArea guessList; // display previous guesses
    public GameView2() {
        this.setTitle("Game View");
        this.setSize(600, 800);
        setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchBar = new JTextField();
        this.add(searchBar, BorderLayout.SOUTH);

        playButton = new JButton("Play");
        submitButton = new JButton("Submit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(submitButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        guessList = new JTextArea();
        this.add(guessList, BorderLayout.CENTER);

        // Add ActionListener to buttons and search bar
        // Implement game logic and audio handling
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView2 app = new GameView2();
            app.setVisible(true);
        });
    }



}
