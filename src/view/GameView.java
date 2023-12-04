package view;

import app.GameUseCaseFactory;
import entity.Track;
import interface_adapter.PlaySong.PlayController;
import interface_adapter.PlaySong.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseState;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessState;
import interface_adapter.guess.GuessViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.search_bar.SearchBarController;
import interface_adapter.search_bar.SearchBarState;
import interface_adapter.search_bar.SearchBarViewModel;
import interface_adapter.skip.SkipController;
import javazoom.jl.decoder.JavaLayerException;

import java.util.stream.Collectors;
import java.util.List;
import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameView extends JPanel implements PropertyChangeListener {

    public final String viewName = "game";

    private JTextField searchTextField; // Text field for user to enter search query
    private JList<String> resultList; // List to store all search results
    private JButton playButton, submitButton, skipButton; // Buttons for user to play, submit guess, and skip
    private SearchBarViewModel searchBarViewModel;
    private SearchBarController searchBarController;
    private GuessViewModel guessViewModel;
    private GuessController guessController;
    private ChooseViewModel chooseViewModel;
    private SkipController skipController;
    private JPanel centerPanel; // Panel to hold both resultList and guessList
    private JPanel guessPanel; // Panel to hold guess rectangles
    private JPanel[] guessRectangles; // Array of panels for each guess
    private JLabel[] guessLabels; // Array of labels for each guess

    public GameView(SearchBarViewModel searchBarViewModel, SearchBarController searchBarController, GuessViewModel guessViewModel, GuessController guessController, SkipController skipController, ChooseViewModel chooseViewModel, PlayController playController) {
        this.searchBarViewModel = searchBarViewModel;
        this.searchBarController = searchBarController;

        this.guessViewModel = guessViewModel;
        this.guessController = guessController;
        this.chooseViewModel = chooseViewModel;
        this.skipController = skipController;

        searchBarViewModel.addPropertyChangeListener(this);
        guessViewModel.addPropertyChangeListener(this);

        this.setSize(600, 800);
        this.setBackground(Color.BLACK);
        Font font = new Font("SansSerif", Font.PLAIN, 16); // Choose the desired font and size
        this.setFont(font);

        /* Setup for SEARCH PANEL */
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.BLACK); // Set the background color to match the frame

        int topPadding = 10; // Padding for the search bar
        searchPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, 0, 0, 0));

        // Setup for searchTextField
        searchTextField = new JTextField();
        searchTextField.setForeground(Color.WHITE);
        searchTextField.setBackground(Color.BLACK);
        Font searchTextFont = new Font("Sans Serif", Font.PLAIN, 18); // Choose the desired font and size
        searchTextField.setFont(searchTextFont);
        searchTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        searchTextField.setPreferredSize(new Dimension(this.getWidth(), 30)); // Adjust the height as needed

        searchPanel.add(searchTextField, BorderLayout.CENTER);
        this.add(searchPanel, BorderLayout.NORTH);

        /* Setup for CENTER PANEL */
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.BLACK);
        // centerPanel.setOpaque(true);
        this.add(centerPanel, BorderLayout.CENTER);

        // Setup for resultList
        resultList = new JList<>();
        resultList.setBackground(Color.BLACK);
        resultList.setForeground(Color.WHITE);
        Font resultFont = new Font("SansSerif", Font.PLAIN, 16); // Choose the desired font and size
        resultList.setFont(resultFont);

        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        scrollPane.setBorder(null);
        centerPanel.add(scrollPane);

        // Setup for guessPanel
        guessPanel = new JPanel();
        guessPanel.setLayout(new GridLayout(6, 1, 0, 5));
        guessPanel.setPreferredSize(new Dimension(600, 200));
        guessPanel.setBackground(Color.BLACK);

        guessRectangles = new JPanel[6];
        guessLabels = new JLabel[6];
        for (int i = 0; i < guessRectangles.length; i++) {
            guessRectangles[i] = new JPanel();
            guessRectangles[i].setBackground(Color.WHITE);
            guessPanel.add(guessRectangles[i]);

            // Initialize each label and add it to the corresponding panel
            guessLabels[i] = new JLabel("");
            guessLabels[i].setForeground(Color.BLACK);
            guessRectangles[i].add(guessLabels[i]);
        }
        centerPanel.add(guessPanel);

        // Add an invisible spacer (to push the guessPanel towards the top / north of the centerPanel)
        Dimension minSize = new Dimension(0, 10); // Minimum size
        Dimension prefSize = new Dimension(0, 20); // Preferred size (adjust this to control the space)
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 150); // Maximum size
        centerPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        playButton = new JButton("Play");
        skipButton = new JButton("Skip");
        submitButton = new JButton("Submit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(skipButton);
        buttonPanel.add(submitButton);
        buttonPanel.setBackground(Color.BLACK);
        this.add(buttonPanel, BorderLayout.SOUTH);

        searchTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchBarState currentState = searchBarViewModel.getState();
                        String text = searchTextField.getText() + e.getKeyChar();
                        currentState.setCurrentSearchQuery(text);
                        searchBarViewModel.setState(currentState);

                        onSearch(text);

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        resultList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String selectedTrack = resultList.getSelectedValue();
                searchTextField.setText(selectedTrack);
            }
        });

        skipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuessState currentState = guessViewModel.getState();
                int currentGuessIndex = currentState.getGuesses() - 1;

                // Update guess label to "Skipped"
                if (currentGuessIndex >= 0 && currentGuessIndex < guessLabels.length) {
                    guessLabels[currentGuessIndex].setText("Skipped");
                    guessLabels[currentGuessIndex].setForeground(Color.GRAY);

                    guessLabels[currentGuessIndex].revalidate();
                    guessLabels[currentGuessIndex].repaint();
                }

                skipController.execute(currentState.getCurrentSong().getTitle(), currentState.getGuesses(), currentState.getMaxGuesses());
            }
        });

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuessState currentGuessState = guessViewModel.getState();
                // Update guess label

                if (currentGuessState.getGuesses() < 6) {
                    ChooseState currentChooseState = chooseViewModel.getState();
                    playController.execute(currentChooseState.getTrack(), currentGuessState.getGuesses());

                }

            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuessState currentState = guessViewModel.getState();
                String guess = resultList.getSelectedValue();
                currentState.setGuess(guess);
                System.out.println("guess: " + guess);
                int currentGuessIndex = currentState.getGuesses() - 1;
                if (currentGuessIndex >= 0 && currentGuessIndex < guessLabels.length) {
                    guessLabels[currentGuessIndex].setText("❌ " + guess.toString());
                    guessLabels[currentGuessIndex].setForeground(Color.RED);

                    guessLabels[currentGuessIndex].revalidate();
                    guessLabels[currentGuessIndex].repaint();
                }
                Track song = currentState.getCurrentSong();
                System.out.println("correct answer: " + song.toString());
                guessController.execute(song.toString(), guess, currentState.getGuesses(), currentState.getMaxGuesses());
            }
        });

        // After setting up the GUI and making the JFrame visible
        this.setVisible(true);

        // Now request focus
        searchTextField.requestFocusInWindow();

}

    private void onSearch(String query) {
        // set it as a state
        // call search controller with the query
        searchBarController.execute(query);

        // Trigger search in ViewModel
        List<Track> tracks = searchBarViewModel.getState().getTracks();
        updateSearchResults(tracks); // Update Jlist Model
    }

    private void updateSearchResults(java.util.List<Track> tracks) {
        DefaultListModel<String> model = new DefaultListModel<>();

        for (int i = 0; i < Math.min(10, tracks.size()); i++) { // Limit to 10 tracks
            model.addElement(tracks.get(i).toString());
        }
        // Note: If the arraylist is empty, search results shows nothing

        resultList.setModel(model);
    }

    /* Reset the game once play again is selected from end view */
    public void resetGame() {
        resetGuessLabels();
        resetSearchBar();
    }
    public void resetGuessLabels() {
        for (JLabel label : guessLabels) {
            label.setText("");
        }
    }

    public void resetSearchBar() {
        searchTextField.setText("");
        resultList.setModel(new DefaultListModel<>());
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SearchBarState) {
            SearchBarState state = (SearchBarState) evt.getNewValue();
            List<Track> tracks = state.getTracks();
            updateSearchResults(tracks);
        }
    }

}
