package view;

import interface_adapter.search_bar.SearchBarViewModel;
import view.SearchBarView;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private SearchBarView searchBarView;
    private SearchBarViewModel searchBarViewModel;

    private JButton playButton, submitButton, skipButton;

    private JTextArea guessList; // display previous guesses

    public GameView() {
        this.setTitle("Game View");
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        searchBarView = new SearchBarView(searchBarViewModel);
        this.add(searchBarView, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play");
        skipButton = new JButton("Skip");
        submitButton = new JButton("Submit");

        buttonPanel.add(playButton);
        buttonPanel.add(skipButton);
        buttonPanel.add(submitButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Add other feature views
    }

    // For Testing purposes
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView gameView = new GameView();
            gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameView.setVisible(true);
        });
    }
}
