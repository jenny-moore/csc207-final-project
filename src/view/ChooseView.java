package view;
import interface_adapter.choose_genre.ChooseState;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.choose_genre.ChooseController;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChooseView extends JFrame implements ActionListener, PropertyChangeListener {
    public final String viewName = "choose";

    private final ChooseViewModel chooseViewModel;
    private final ChooseController chooseController;

    private final JButton choose;
    private final JComboBox<String> genres;

    public ChooseView(ChooseController controller, ChooseViewModel chooseViewModel) {
        this.chooseController = controller;
        this.chooseViewModel = chooseViewModel;

        chooseViewModel.addPropertyChangeListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,800);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);

        JLabel title = new JLabel(ChooseViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        genres = new JComboBox(ChooseViewModel.GENRES);
        genres.setFont(new Font("Comic Sans", Font.BOLD, 15));
        genres.setBackground(Color.GREEN);

        choose = new JButton(ChooseViewModel.CHOOSE_BUTTON_LABEL);
        choose.setBackground(Color.GREEN);
        choose.setFont(new Font("Comic Sans", Font.BOLD, 15));
        choose.setOpaque(true);
        choose.setBorderPainted(false);
        //choose.setBounds(100,100, 400, 200);

        JPanel buttons = new JPanel();
        buttons.add(choose);
        buttons.add(title);
        buttons.add(genres);
        buttons.setVisible(true);

        choose.addActionListener(this);
        genres.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);
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
        if (evt.getPropertyName().equals("state")){
            ChooseState state = (ChooseState) evt.getNewValue();
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
            }}
    }

}
