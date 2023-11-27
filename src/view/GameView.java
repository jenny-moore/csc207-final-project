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
    private JTextField searchTextField; // Text field for user to enter search query
    private JList<String> resultList; // List to store all search results
    private JButton playButton, submitButton, skipButton; // Buttons for user to play, submit guess, and skip
    private SearchBarViewModel searchBarViewModel;
    private SearchBarController searchBarController;
    private JPanel centerPanel; // Panel to hold both resultList and guessList
    private JPanel guessPanel; // Panel to hold guess rectangles
    private JPanel[] guessRectangles; // Array of panels for each guess
    private JLabel[] guessLabels; // Array of labels for each guess

    public GameView(SearchBarViewModel searchBarViewModel, SearchBarController searchBarController) {
        this.searchBarViewModel = searchBarViewModel;
        this.searchBarController = searchBarController;

        searchBarViewModel.addPropertyChangeListener(this);

        this.setTitle("Game View");
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        Font font = new Font("SansSerif", Font.PLAIN, 16); // Choose the desired font and size
        this.setFont(font);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.BLACK); // Set the background color to match the frame

        int topPadding = 10; // Padding for the search bar
        searchPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, 0, 0, 0));

        // Setup for searchTextField
        searchTextField = new JTextField();
        searchTextField.setForeground(Color.WHITE);
        searchTextField.setBackground(Color.BLACK);
        Font searchTextFont = new Font("SansSerif", Font.PLAIN, 18); // Choose the desired font and size
        searchTextField.setFont(searchTextFont);
        searchTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        searchPanel.add(searchTextField, BorderLayout.CENTER);
        this.add(searchPanel, BorderLayout.NORTH);
        // this.add(searchTextField, BorderLayout.NORTH);

        // Setup for centerPanel
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
            // TODO: Set font and size (resultFont should be sufficient?)
            guessLabels[i].setForeground(Color.BLACK);
            guessRectangles[i].add(guessLabels[i]);
        }
        centerPanel.add(guessPanel);

        // Add an invisible spacer (to push the guessPanel towards the top / north of the centerPanel)
        Dimension minSize = new Dimension(0, 10); // Minimum size
        Dimension prefSize = new Dimension(0, 20); // Preferred size (adjust this to control the space)
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 150); // Maximum size
        centerPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        // TODO: Include progress bar (or at least something to track progression of song / number of guesses)

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

        skipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: add logic for skipButton here
                // make sure to update guess label
            }
        });

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: add logic for playButton here
                // make sure to update guess label
            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: add logic for submitButton here
                // make sure to update guess label
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

        for (int i = 0; i < Math.min(10, tracks.size()); i++) { // Limit to 5 tracks
            model.addElement(tracks.get(i).toString());
        }

        resultList.setModel(model);
    }


    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("im here in propertyChange");
        if (evt.getNewValue() instanceof SearchBarState) {
            SearchBarState state = (SearchBarState) evt.getNewValue();
            List<Track> tracks = state.getTracks();
            updateSearchResults(tracks);
        }
    }


}
