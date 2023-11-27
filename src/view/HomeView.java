package view;
import interface_adapter.play_again.PlayAgainController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "home";
    final JButton playButton = new JButton("Start");
    final JLabel label = new JLabel("Guess the Song", SwingConstants.CENTER);


    private final PlayAgainController playAgainController;

    public HomeView(PlayAgainController playAgainController){

        this.playAgainController = playAgainController;



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


        this.setSize(500, 400);
        playButton.setBounds(150, 120, 200, 70);
        label.setBounds(150, 70, 200, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Comic_Sans", Font.PLAIN, 20));

        Color borderColor = new Color(8, 115, 54);
        Color fillColor = new Color(8, 115, 54);
        this.setBorder(BorderFactory.createLineBorder(borderColor, 10, true));
        this.setBackground(fillColor);


        this.add(playButton);
        this.add(label);
        this.setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
