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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GameView extends JFrame implements PropertyChangeListener {
    private JTextField searchTextField;
    private JList<String> resultList;
    private JButton playButton, submitButton, skipButton;
    private JTextArea guessList; // display previous guesses
    private SearchBarViewModel searchBarViewModel;
    private SearchBarController searchBarController;

    public GameView(SearchBarViewModel searchBarViewModel, SearchBarController searchBarController) {
        this.searchBarViewModel = searchBarViewModel;
        this.searchBarController = searchBarController;

        System.out.println("im here in game view");
        searchBarViewModel.addPropertyChangeListener(this);

        this.setTitle("Game View");
        this.setSize(600, 800);
        setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK); // not working btw

        searchTextField = new JTextField();
        this.add(searchTextField, BorderLayout.NORTH);
        resultList = new JList<>();
        this.add(new JScrollPane(resultList), BorderLayout.WEST);

        // Add a DocumentListener to searchTextField
//        Document searchFieldDoc = searchTextField.getDocument();
//        searchFieldDoc.addDocumentListener(new SearchDocumentListener());

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

//                        if (e.getSource() == searchTextField) {
//                            // System.out.println("I am here babyyy");
//                            // onSearch();
//                            // searchBarController.execute(searchTextField.getText());
//                        }
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

        // Add ActionListener to buttons and search bar
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
        for (Track track : tracks) {
            model.addElement(track.toString());
        }
        resultList.setModel(model);
    }


    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("im here in propertyChange");
        if (evt.getNewValue() instanceof SearchBarState) {
            SearchBarState state = (SearchBarState) evt.getNewValue();
            java.util.List<Track> tracks = state.getTracks(); // Should get at most five
            updateSearchResults(tracks);
        }
    }


}
