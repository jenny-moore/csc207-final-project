package view;

import interface_adapter.search_bar.SearchBarViewModel;
import view.SearchBarView;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private SearchBarView searchBarView;
    private SearchBarViewModel searchBarViewModel;

    public GameView() {
        this.setTitle("Game View");
        this.setSize(600, 800);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        searchBarView = new SearchBarView(searchBarViewModel);
        this.add(searchBarView, BorderLayout.SOUTH);
        // Add other feature views
    }
}
