package view;
import interface_adapter.play_again.PlayAgainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "home";
    final JButton playButton = new JButton("Start");

    private final PlayAgainController playAgainController;

    public HomeView(PlayAgainController playAgainController){

        this.playAgainController = playAgainController;

        JLabel title = new JLabel("Start");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        playButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(playButton)){

                            playAgainController.execute();
                        }
                    }
                }
        );

        this.setSize(800,600);
        this.add(title);
        this.add(playButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
