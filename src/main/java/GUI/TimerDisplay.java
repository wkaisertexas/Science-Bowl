package GUI;

import kotlin.math.MathKt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerDisplay {
    private JLabel teamALabel;
    private JLabel teamBLabel;
    private JLabel teamAScoreLabel;
    private JLabel teamBScoreLabel;
    private JProgressBar progress;
    private JPanel main;
    private JLabel roundTimerLabel;
    private JLabel roundTimer;

    private JFrame frame;

    public TimerDisplay(){
        // adds a resize component listener
        main.addComponentListener(
          new ComponentAdapter(){
              public void componentResized(ComponentEvent e) {
                updateTextSize();
              }
          });
        // starts the JFrame
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        if(gs.length == 1){ // I still need to make it go fullscreen
            frame = new JFrame(gs[0].getDefaultConfiguration());
        }else{
            frame = new JFrame(gs[1].getDefaultConfiguration());

            // This makes the frame full size
            Rectangle bounds = gs[1].getDefaultConfiguration().getBounds();
            frame.setSize(bounds.x, 1080);
        }
        // starts the JFrame
        frame.setTitle("Science Bowl Timer");
        frame.setContentPane(this.main);
        updateTextSize();
        frame.setVisible(true);
        setBonusTeam(false);
    }


    // mutator methods

    public void updateTextSize(){
        Font labelFont = roundTimer.getFont();
        // Updating the timer
        Dimension frameSize = frame.getSize();
            // this needs to find the min of the width or the height for the resize
            double minWidth = frameSize.getWidth() / 6;
            double minHeight = frameSize.getHeight() / 5;

            roundTimer.setFont(new Font(labelFont.getName(), Font.BOLD, MathKt.roundToInt(Math.min(minHeight, minWidth))));

        // Updating the team 1 score
            minWidth = frameSize.getWidth() / 24;
            minHeight = frameSize.getHeight() / 4;
        teamALabel.setFont(new Font(labelFont.getName(), Font.BOLD, MathKt.roundToInt(Math.min(minHeight, minWidth))));
        teamBLabel.setFont(new Font(labelFont.getName(), Font.BOLD, MathKt.roundToInt(Math.min(minHeight, minWidth))));

        teamAScoreLabel.setFont(new Font(labelFont.getName(), Font.BOLD, MathKt.roundToInt(Math.min(minHeight, minWidth * 4))));
        teamBScoreLabel.setFont(new Font(labelFont.getName(), Font.BOLD, MathKt.roundToInt(Math.min(minHeight, minWidth * 4))));
    }

    public void setBonusTeam(boolean team){
        // false is A
        // true is B
        if(team){  // this condition means that team B got the bonus so team A should be greyed out and team B highlighted
            // this greys out team A
            teamALabel.setForeground(Color.lightGray);
            teamAScoreLabel.setForeground(Color.lightGray);

            // this highlights team B
            teamBLabel.setForeground(Color.green.darker());
            teamBScoreLabel.setForeground(Color.green.darker());
        } else{  // this condition means that team A got the bonus so team B should be greyed out and team A highlighted
            // this greys out team B
            teamBLabel.setForeground(Color.lightGray);
            teamBScoreLabel.setForeground(Color.lightGray);

            // this highlights team A
            teamALabel.setForeground(Color.green.darker());
            teamAScoreLabel.setForeground(Color.green.darker());
        }
    }

    public void resetGUIColoration(){
        teamALabel.setForeground(new Color(0,0,0));
        teamAScoreLabel.setForeground(new Color(0,0,0));

        teamBLabel.setForeground(new Color(0,0,0));
        teamBScoreLabel.setForeground(new Color(0,0,0));
    }

    public void setTimer(long numberOfSecondsLeft) {
        if (numberOfSecondsLeft % 60 >= 10) {
            roundTimer.setText("0" + Math.floorDiv(numberOfSecondsLeft, 60) + ":" + numberOfSecondsLeft % 60);
        } else {
            roundTimer.setText("0" + Math.floorDiv(numberOfSecondsLeft, 60) + ":" + "0" + numberOfSecondsLeft % 60);
        }
    }

    public void startQuestionCountdownClock(boolean tossup){
        // TODO: Migrate this method to be a part of game


        if(tossup){
            // tossup condition

        } else{
            // bonus condition


        }





    }

    // Score setting methods
    public void updateTeamAScore(int score) {
        if (score < 10) {
            teamAScoreLabel.setText("0" + score);
        } else {
            teamAScoreLabel.setText(score + "");
        }
    }

    public void updateTeamBScore(int score){
        if(score < 10) {
            teamAScoreLabel.setText("0" + score);
        } else {
            teamAScoreLabel.setText(score + "");
        }
    }

}
