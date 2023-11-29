package view;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.play_again.PlayAgainController;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class EndView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "End View";

    private final JButton playAgain;
    private final JButton leaderboard;
    private final LeaderboardController leaderboardController;
    private final LeaderboardViewModel leaderboardViewModel;
    private final PlayAgainController playAgainController;


    public EndView(LeaderboardController leaderboardController, LeaderboardViewModel leaderboardViewModel, PlayAgainController playAgainController){
        this.setSize(600,800);
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        this.leaderboardController = leaderboardController;
        this.leaderboardViewModel = leaderboardViewModel;
        this.playAgainController = playAgainController;

        playAgain = new JButton("Play Again");
        leaderboard = new JButton("Leaderboard");

        playAgain.setBackground(Color.GREEN);

        leaderboard.setBackground(Color.GREEN);

        leaderboardViewModel.addPropertyChangeListener(this);

        playAgain.addActionListener(this);
        leaderboard.addActionListener(this);

        playAgain.setBounds(100,100, 400, 200);
        leaderboard.setBounds(100,400,400,200);


        playAgain.setFont(new Font("Comic Sans", Font.BOLD, 25));
        leaderboard.setFont(new Font("Comic Sans", Font.BOLD, 25));

        playAgain.setOpaque(true);
        leaderboard.setOpaque(true);
        leaderboard.setBorderPainted(false);
        playAgain.setBorderPainted(false);
        this.add(playAgain);
        this.add(leaderboard);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == leaderboard){
            leaderboardController.execute();
        }
        if(evt.getSource() == playAgain){
            playAgainController.execute();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() instanceof LeaderboardState) {
            LeaderboardState state = (LeaderboardState) evt.getNewValue();
            new LeaderboardView(state.getData());
        }
    }
}
