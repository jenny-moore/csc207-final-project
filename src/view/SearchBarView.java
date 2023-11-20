package view;

import entity.Track;
import interface_adapter.search_bar.SearchBarController;
import interface_adapter.search_bar.SearchBarViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

// Display the output data from the presenter
public class SearchBarView extends JFrame{
    private JTextField searchTextField;
    private JPanel panel;
    private JList<String> resultList;
    private SearchBarViewModel viewModel;

    public SearchBarView(SearchBarViewModel viewModel) {
        this.viewModel = viewModel;
        initializeUI();
    }
    
    private void initializeUI() {
        this.setTitle("Search Bar"); // title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        searchTextField = new JTextField(20);

        // Add a DocumentListener to searchTextField
        Document searchFieldDoc = searchTextField.getDocument();
        searchFieldDoc.addDocumentListener(new SearchDocumentListener());


        JPanel northPanel = new JPanel(); 
        northPanel.add(searchTextField);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(resultList), BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null); // Center on screen
        this.setVisible(true);
    }

    private class SearchDocumentListener implements DocumentListener {
        private Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSearch();
            }
        }); // 600ms delay before triggering search

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
            viewModel.getState().setCurrentSearchQuery(query);

            // Trigger search in ViewModel
            List<Track> tracks = viewModel.getState().getTracks();
            updateSearchResults(tracks);
        });

        resultList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                String selectedTrack = resultList.getSelectedValue();
                searchTextField.setText(selectedTrack);
                // Play track here
            }
        });
    }

    private void updateSearchResults(List<Track> tracks) {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Track track : tracks) {
            model.addElement(track.getTitle() + " - " + track.getArtist());
        }
        resultList.setModel(model);
    }

    // For testing purposes
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchBarViewModel viewModel = new SearchBarViewModel();
            new SearchBarView(viewModel);
        });
    }

}
