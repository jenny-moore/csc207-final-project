package view;

import entity.Track;
import interface_adapter.search_bar.SearchBarController;
import interface_adapter.search_bar.SearchBarState;
import interface_adapter.search_bar.SearchBarViewModel;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GameView extends JFrame {
    private JTextField searchTextField;
    private JList<String> resultList;
    private JButton playButton, submitButton, skipButton;
    private JTextArea guessList; // display previous guesses
    private SearchBarViewModel searchBarViewModel;
    private SearchBarController searchBarController;

    public GameView() {
        this.setTitle("Game View");
        this.setSize(600, 800);
        setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK); // not working btw

        searchTextField = new JTextField();
        this.add(searchTextField, BorderLayout.SOUTH);
        this.add(new JScrollPane(resultList), BorderLayout.CENTER);

        // Add a DocumentListener to searchTextField
        Document searchFieldDoc = searchTextField.getDocument();
        searchFieldDoc.addDocumentListener(new SearchDocumentListener());

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

    private class SearchDocumentListener implements DocumentListener {
        private Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("im here");
                onSearch();
            }
        }); // 300ms delay before triggering search

        @Override
        public void insertUpdate(DocumentEvent e) {
            handleSearchRequest();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleSearchRequest();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            handleSearchRequest();
        }

        private void handleSearchRequest() {
            if (timer.isRunning()) {
                timer.restart();
            } else {
                timer.start();
            }
        }
    }

    private void onSearch() {
        searchTextField.addActionListener(e -> {
            String query = searchTextField.getText();
            // set it as a state
            // call search controller with the query
            searchBarController.execute(query);

            // searchBarViewModel.getState().setCurrentSearchQuery(query);

            // Trigger search in ViewModel
            java.util.List<Track> tracks = searchBarViewModel.getState().getTracks();
            System.out.println(tracks.size());
        });

        resultList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String selectedTrack = resultList.getSelectedValue();
                searchTextField.setText(selectedTrack);
                // Play track here
            }
        });
    }

    private void updateSearchResults(java.util.List<Track> tracks) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Track track : tracks) {
            model.addElement(track.getTitle() + " - " + track.getArtist());
        }
        resultList.setModel(model);
    }


    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SearchBarState) {
            SearchBarState state = (SearchBarState) evt.getNewValue();
            java.util.List<Track> tracks = state.getTracks(); // TODO: Check about length of list
            updateSearchResults(tracks);
        }
    }

        // For Testing purposes
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                GameView app = new GameView();
                app.setVisible(true);
            });
        }

}
