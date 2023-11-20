package view;

import entity.Track;
import interface_adapter.search_bar.SearchBarViewModel;

import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Display the output data from the presenter
public class SearchBarView extends JFrame{
    private JFrame frame;
    private JTextField searchTextField;
    private JButton searchButton;
    private JList<String> resultList;
    private SearchBarViewModel viewModel;

    public SearchBarView(SearchBarViewModel viewModel) {
        this.viewModel = viewModel;
        initializeUI();
    }
    
    private void initializeUI() {
        this.setTitle("Search Bar");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        searchTextField = new JTextField(20);
        
        JPanel northPanel = new JPanel(); 
        northPanel.add(searchTextField);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(resultList), BorderLayout.CENTER);
        
        this.pack();
        this.setLocationRelativeTo(null); // Center on screen
        this.setVisible(true);
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

}
