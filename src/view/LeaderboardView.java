package view;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class LeaderboardView extends JFrame {
    int[] data;
    JPanel topPanel;
    JPanel bottomPanel;
    JPanel centerPanel;

    int gamesPlayed;
    int gamesWon;

    public LeaderboardView(int[] data){
        this.data = data;
        this.setTitle("Leaderboard");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(560,800);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        getGames();

        topPanel = new JPanel();
        bottomPanel = new JPanel();
        centerPanel = new JPanel();
        setUpTopPanel();
        setUpBottomPanel();
        setUpCenterPanel();

        this.add(bottomPanel,BorderLayout.SOUTH);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void getGames() {
        gamesPlayed = 0;
        gamesWon = 0;
        for(int i = 1; i < data.length; i++){
            gamesPlayed += data[i];
            if(i > 1){
                gamesWon += data[i];
            }
        }
    }

    private void setUpBottomPanel() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String c = String.valueOf(gamesWon) + "/" + String.valueOf(gamesPlayed);
        double percentage = (double) gamesWon / gamesPlayed * 100;
        String roundedPercentage = decimalFormat.format(percentage);
        bottomPanel.setLayout(new GridLayout());
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setPreferredSize(new Dimension(560, 200));

        JLabel correct = new JLabel("<html><center><font size=6 color=white>"+ c
                + "</font><br><font size=4 color=gray>Correct</font><center></html>");
        correct.setFont(new Font("MV Boli", Font.PLAIN, 30));
        correct.setHorizontalAlignment(JLabel.CENTER);
        correct.setVerticalAlignment(JLabel.CENTER);


        JLabel correctper = new JLabel("<html><center><font size=6 color=white>"+ roundedPercentage
                + "%</font><br><font size=4 color=gray>Correct %</font><center></html>");
        correctper.setFont(new Font("MV Boli", Font.PLAIN, 30));
        correctper.setHorizontalAlignment(JLabel.CENTER);
        correctper.setVerticalAlignment(JLabel.CENTER);

        JLabel score = new JLabel("<html><center><font size=6 color=white>"+ String.valueOf(data[0])
                + "</font><br><font size=4 color=gray>Score</font><center></html>");
        score.setFont(new Font("MV Boli", Font.PLAIN, 30));
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setVerticalAlignment(JLabel.CENTER);

        bottomPanel.add(score);
        bottomPanel.add(correctper);
        bottomPanel.add(correct);
    }

    private void setUpTopPanel() {
        JLabel label = new JLabel("Leaderboard");
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(560, 100));
        label.setForeground(Color.white);
        label.setFont(new Font("MV Boli", Font.PLAIN,20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(label, BorderLayout.CENTER);
    }

    private void setUpCenterPanel() {
        int[] newData = new int[data.length - 1];
        System.arraycopy(data, 1, newData, 0, newData.length);

        centerPanel.setBackground(Color.BLACK);
        centerPanel.setPreferredSize(new Dimension(560, 500));
        centerPanel.setLayout(new GridLayout(1,7,10,0));

        int vmax = getMax(newData);

        JPanel g0 = new JPanel();
        JPanel g1 = new JPanel();
        JPanel g2 = new JPanel();
        JPanel g3 = new JPanel();
        JPanel g4 = new JPanel();
        JPanel g5 = new JPanel();
        JPanel g6 = new JPanel();
        JPanel[] panels = {g0,g1,g2,g3,g4,g5,g6};
        for(int i = 0; i < panels.length; i++){
            centerPanelHelper(panels[i], newData[i], i, vmax);
            centerPanel.add(panels[i]);
        }
    }

    private void centerPanelHelper(JPanel panel, int data, int i, int vmax) {
        panel.setPreferredSize(new Dimension(80,500));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.black);

        JLabel labeltop = new JLabel(String.valueOf(data));
        labeltop.setHorizontalAlignment(JLabel.CENTER);
        labeltop.setVerticalAlignment(JLabel.BOTTOM);
        labeltop.setForeground(Color.white);
        labeltop.setFont(new Font("MV Boli", Font.PLAIN,20));

        JLabel midlabel = new JLabel();

        JLabel labelbottom;
        if(i==0){
            labelbottom = new JLabel("X");
            labelbottom.setForeground(Color.red);
        }else {
            labelbottom = new JLabel(String.valueOf(i));
            labelbottom.setForeground(Color.gray);
        }
        labelbottom.setHorizontalAlignment(JLabel.CENTER);
        labelbottom.setVerticalAlignment(JLabel.TOP);
        labelbottom.setFont(new Font("MV Boli", Font.PLAIN,20));

        int height = Math.round(300 * ((float) data / vmax));
        if(height < 30){
            height = 30;
        }
        midlabel.setPreferredSize(new Dimension(80, height));
        if(i==0){
            midlabel.setBackground(Color.red);
        }else {
            midlabel.setBackground(Color.green);
        }
        midlabel.setOpaque(true);

        labeltop.setPreferredSize(new Dimension(80, (300-height) + 100));
        labelbottom.setPreferredSize(new Dimension(80, 100));

        panel.add(labeltop, BorderLayout.NORTH);
        panel.add(midlabel, BorderLayout.CENTER);
        panel.add(labelbottom, BorderLayout.SOUTH);
    }


    private int getMax(int[] newdata) {
        int max = newdata[0];
        for(int i = 1; i < newdata.length; i++){
            if(newdata[i] > max){
                max = newdata[i];
            }
        }
        return max;
    }
}

