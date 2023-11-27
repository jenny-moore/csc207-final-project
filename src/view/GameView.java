package view;

import entity.Track;
import interface_adapter.search_bar.SearchBarController;
import interface_adapter.search_bar.SearchBarState;
import interface_adapter.search_bar.SearchBarViewModel;

import java.util.stream.Collectors;
import java.util.List;
import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameView extends JFrame implements PropertyChangeListener {
    private JTextField searchTextField;
    private JList<String> resultList;
    private JButton playButton, submitButton, skipButton;
    private SearchBarViewModel searchBarViewModel;
    private SearchBarController searchBarController;
    private JPanel centerPanel; // Panel to hold both resultList and guessList
    private JPanel guessPanel; // Panel to hold guess rectangles
    private JPanel[] guessRectangles; // Array of panels for each guess

    public GameView(SearchBarViewModel searchBarViewModel, SearchBarController searchBarController) {
        this.searchBarViewModel = searchBarViewModel;
        this.searchBarController = searchBarController;

        System.out.println("im here in game view");
        searchBarViewModel.addPropertyChangeListener(this);

        this.setTitle("Game View");
        this.setSize(600, 800);
        // setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK); // TODO: How to set entire app background color to black?

        // Setup for searchTextField
        searchTextField = new JTextField();
        searchTextField.setForeground(Color.WHITE);
        searchTextField.setBackground(Color.BLACK);
        this.add(searchTextField, BorderLayout.NORTH);

        // Setup for centerPanel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.BLACK); // NONE OF THIS IS WORKING UGH WHY
        centerPanel.setOpaque(true);
        this.add(centerPanel, BorderLayout.CENTER);

        // Setup for resultList
        resultList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setPreferredSize(new Dimension(600, 100)); // Set preferred size to control the size
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setForeground(Color.WHITE);
        centerPanel.add(scrollPane);

        // Setup for guessPanel
        guessPanel = new JPanel();
        guessPanel.setLayout(new GridLayout(6, 1, 5, 5));
        guessPanel.setBackground(Color.BLACK);

        guessRectangles = new JPanel[6];
        for (int i = 0; i < guessRectangles.length; i++) {
            guessRectangles[i] = new JPanel();
            guessRectangles[i].setBackground(Color.WHITE);
            guessPanel.add(guessRectangles[i]);
        }
        centerPanel.add(guessPanel);
        // this.add(guessPanel, BorderLayout.CENTER); // Use this if adding directly to the frame

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
                        System.out.println("Key pressed: Search text is now: " + text);

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
                // Play track here
            }
        });

        // After setting up the GUI and making the JFrame visible
        this.setVisible(true);

        // Now request focus
        searchTextField.requestFocusInWindow();

        // Add ActionListener to buttons and guess panel
        // Implement game logic and audio handling
    }

    private void onSearch(String query) {
        // set it as a state
        // call search controller with the query
        System.out.println("im here in onSearch");
        searchBarController.execute(query);

        // searchBarViewModel.getState().setCurrentSearchQuery(query);
        // Trigger search in ViewModel
        List<Track> tracks = searchBarViewModel.getState().getTracks();
        System.out.println(tracks);
        updateSearchResults(tracks); // Update Jlist Model
    }

    private void updateSearchResults(java.util.List<Track> tracks) {
        System.out.println("im here in updateSearchResults");
        DefaultListModel<String> model = new DefaultListModel<>();

        for (int i = 0; i < Math.min(5, tracks.size()); i++) { // Limit to 5 tracks
            model.addElement(tracks.get(i).toString());
        }

        resultList.setModel(model);
    }


    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("im here in propertyChange");
        // note: gets called after every character is entered
        if (evt.getNewValue() instanceof SearchBarState) {
            SearchBarState state = (SearchBarState) evt.getNewValue();
            List<Track> tracks = state.getTracks();
            updateSearchResults(tracks);
        }
    }


}
