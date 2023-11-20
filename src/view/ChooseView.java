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


        JLabel title = new JLabel(ChooseViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        genres = new JComboBox(ChooseViewModel.GENRES);

        JPanel buttons = new JPanel();
        choose = new JButton(ChooseViewModel.CHOOSE_BUTTON_LABEL);
        buttons.add(choose);


        choose.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(choose)) {
                            ChooseState currentState = chooseViewModel.getState();

                            chooseController.execute(
                                    currentState.getGenre()
                            );
                        }
                    }
                }
        );

        genres.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JComboBox cb = (JComboBox)e.getSource();
                        String genre = (String)cb.getSelectedItem();
                        ChooseState state = chooseViewModel.getState();
                        state.setGenre(genre);
                        chooseViewModel.setState(state);
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(genres);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")){
            ChooseState state = (ChooseState) evt.getNewValue();
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
            }}
    }

}
