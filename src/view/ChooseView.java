package view;
import interface_adapter.choose_genre.ChooseState;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.choose_genre.ChooseController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChooseView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "choose";

    private final ChooseViewModel chooseViewModel;
    private final ChooseController chooseController;

    private final JButton choose;
    private final JComboBox<String> genres;

    public ChooseView(ChooseController controller, ChooseViewModel chooseViewModel) {
        this.chooseController = controller;
        this.chooseViewModel = chooseViewModel;

        chooseViewModel.addPropertyChangeListener(this);

        this.setSize(600,800);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);

        JLabel title = new JLabel(ChooseViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Comic Sans", Font.BOLD, 25));
        title.setBounds(140,25, 200, 50);
        title.setForeground(Color.white);

        genres = new JComboBox(ChooseViewModel.GENRES);
        genres.setFont(new Font("Comic Sans", Font.BOLD, 15));
        genres.setBackground(Color.GREEN);
        genres.setBounds(75,100, 150, 50);

        choose = new JButton(ChooseViewModel.CHOOSE_BUTTON_LABEL);
        choose.setBackground(Color.GREEN);
        choose.setFont(new Font("Comic Sans", Font.BOLD, 15));
        choose.setOpaque(true);
        choose.setBorderPainted(false);
        choose.setBounds(250,100, 150, 50);

        this.add(title);
        this.add(genres);
        this.add(choose);
        this.setVisible(true);

        choose.addActionListener(this);
        genres.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(choose)) {
            ChooseState currentState = chooseViewModel.getState();
            chooseController.execute(currentState.getGenre());
        } else if (evt.getSource().equals(genres)){
            JComboBox cb = (JComboBox)evt.getSource();
            String genre = (String)cb.getSelectedItem();
            ChooseState state = chooseViewModel.getState();
            state.setGenre(genre);
            chooseViewModel.setState(state);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

}
