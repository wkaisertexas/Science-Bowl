package GUI;

import javax.swing.*;
import java.awt.*;

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

        // starts the JFrame
        frame = new JFrame("Science Bowl Timer");
        frame.setContentPane(this.main);
        frame.pack();
        frame.setVisible(true);

        setBonusTeam(false);
    }


    // mutator methods

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
