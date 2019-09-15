package GUI;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import logical.Game;
import logical.Player;
import logical.Question;

public class Reader implements KeyListener {
    private JLabel title;
    private JPanel teamAPanel;
    private JPanel timePanel;
    private JPanel teamBPanel;
    private JPanel questionPannel;
    private JPanel statistics;
    private JPanel main;
    private JProgressBar questionTimer;
    private JLabel questionTimerLabel;
    private JLabel timeLeft;
    private JLabel teamAScore;
    private JLabel teamBScore;
    private JLabel questionInfoLabel;
    private JButton helpButton;
    private JLabel answer;
    private JTable teamATable;
    private JTable teamBTable;
    private JLabel questionNumberLabel;
    private JTextPane questionTextPane;

    private JFrame frame;

    public Question question;
    public boolean tossup; // this keeps track of whether the current question being displayed is a tossup or a bonus

    public Help helper;

    // game variables
    private Player[] aTeam = new Player[4];
    private Player[] bTeam = new Player[4];

    private String modifierKey; // this is a key for all of the actions (Nothing for correct) but others for each

    private Game g;

    public Reader(Player[] aTeam, Player[] bTeam, Game g){

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // This should call a separate frame that will load that will tell the user how to use the program
                helper = new Help();

            }
        });

        this.aTeam = aTeam;
        this.bTeam = bTeam;
        this.g = g;

        // this creates the JFrame
        frame = new JFrame("Reader Title");
        frame.setContentPane(this.main);
        frame.pack();
        frame.setVisible(true);

        addPlayersNames();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // DEBUG: code remove
        System.out.println("Key typed: " + keyEvent.getKeyChar());

        switch (keyEvent.getKeyCode()){
            // cases for team A
            case KeyEvent.VK_Q:
                questionAnswered(aTeam[0], modifierKey, false);
            case KeyEvent.VK_W:
                questionAnswered(aTeam[1], modifierKey, false);
            case KeyEvent.VK_E:
                questionAnswered(aTeam[2], modifierKey, false);
            case KeyEvent.VK_R:
                questionAnswered(aTeam[3], modifierKey, false);

            // cases for team B
            case KeyEvent.VK_A:
                questionAnswered(bTeam[0], modifierKey, true);
            case KeyEvent.VK_S:
                questionAnswered(bTeam[1], modifierKey, true);
            case KeyEvent.VK_D:
                questionAnswered(bTeam[2], modifierKey, true);
            case KeyEvent.VK_F:
                questionAnswered(bTeam[3], modifierKey, true);

            default:
                break;
        }
    }

    // mutator methods
    @Override
    public void keyPressed(KeyEvent keyEvent){
        // this code will look at the key and it will check and see if it is one of the modifier keys
        System.out.println("Testing");
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_B:
                modifierKey = "b";
            case KeyEvent.VK_I:
                modifierKey = "i";
            case KeyEvent.VK_C:
                modifierKey = "c";
            case KeyEvent.VK_X:
                modifierKey = "x";
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // this will run some code to check if the modifier key has been released
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_B:
                // this checks to see if B was the previous modifier
                if(modifierKey.equals("b")){
                    modifierKey = "";
                }
            case KeyEvent.VK_I:
                // this checks to see if I was the previous modifier
                if(modifierKey.equals("i")){
                    modifierKey = "";
                }
            case KeyEvent.VK_C:
                // this checks to see if c was the previous modifier
                if(modifierKey.equals("c")) {
                    modifierKey = "";
                }
            case KeyEvent.VK_X:
                // this checks to see if x was the previous modifier
                if(modifierKey.equals("x")){
                    modifierKey = "";
                }
            default:
                break;
        }
    }

    public void questionAnswered(Player p, String modifierKey, boolean team){
        // team == false is team A
        // team == true is team B
        if(p == null){
            System.err.println("Typed in the Wrong Key");
            return; // in this case a typo was made by the user
        }

        if(modifierKey.equals("b")){
            question.blurter = p;
            if(team){
                // teamB
                g.updateTeamBScore(-4);
            } else{
                // teamA
                g.updateTeamAScore(-4);
            }
        } else if(modifierKey.equals("i")){
            question.incorrectInterupter = p;
            if(team){
                // teamB
                g.updateTeamBScore(-4);
            } else{
                // teamA
                g.updateTeamAScore(-4);
            }
        } else if(modifierKey.equals("c")){
            question.staller = p;
        } else if(modifierKey.equals("x")){
            question.answeredIncorrectly = p;
        } else {
            // this is the case when there is no modifying key meaning that the player go the things correct
            question.answeredCorrectly = p;
            if(team){
                // this is the case for team B
                g.updateTeamBScore(4);
            } else{
                // this is the case for teamA
                g.updateTeamAScore(4);
            }

            // TODO: Implement a method here that will either go to the bonus or to the next question (We know that this is a tossup because this is the only thing where tossups are available (Have to add some code to make sure this only works for tossups
            goToBonus(team);
        }

    }

    public void setQuestionNumber(int questionNumber){
        questionNumberLabel.setText("Question: " + questionNumber + " / 25");
    }

    public void updateQuestion(Question q){
        // this sets the text of the questionTextArea label
        questionInfoLabel.setText("Type: Tossup Format: " + q.tossupTypeToString() + " Category: " + q.category);

        // this sets the text of the body of the questionTextArea
        questionTextPane.setText(q.tossupQuestion);

        // this sets the label that contains the correct answer
        answer.setText("Answer: " + q.tossupAnswer);

        // this sets the local question to the class variable question
        question = q;

        restHighlights(); // this fixes things if they were affected before
    }

    public void goToBonus(boolean team){
        // TODO: find some way to implement score changes for bonuses


        // this will highlight the team answering the bonus question
        highlightTeam(team);

        // this will change the text in order to display the bonus question

        // this takes care of the top label
        questionInfoLabel.setText("Type: Bonus Format: " + question.bonusTypeToString() + " Category: " + question.category);

        // this takes care of the text pane
        questionTextPane.setText(question.bonusQuestion);

        // this takes care of the answer
        answer.setText("Answer: " + question.bonusAnswer);
    }

    public void highlightTeam(boolean team){
        // false is Team A
        // true is Team B
        if(team){

            // this fill fade out team A
            teamAScore.enable(false);

            // this will highlight team B
            teamBScore.setForeground(Color.green.darker());

        }else{
            // this will fade out team B
            teamBScore.enable(false);

            // this will highlight team A
            teamAScore.setForeground(Color.green.darker());
        }
    }

    public void restHighlights(){
        // this will just reset the highlights/ foregrounds to normal

        teamAScore.enable(true);
        teamBScore.enable(true);

        teamAScore.setForeground(new Color(0, 0, 0));
        teamBScore.setForeground(new Color(0, 0, 0));
    }

    public void updateTeamAScore(int score){
        teamAScore.setText("Team A: " + score);
    }

    public void updateTeamBScore(int score){
        teamBScore.setText("Team B: " + score);
    }

    // Setup methods
    private void createUIComponents() {
        setUpTables();
    }

    public void setUpTables(){
        // this will set up the dimensionality for table
        String[] columnNames = {"Position", "Name", "Positive Points", "Negative Points", "Overall Points"};
        // this sets up the Team A table
        String[][] teamATableData = {
                {"A1", "", "", "", ""},
                {"AC", "", "", "", ""},
                {"A3", "", "", "", ""},
                {"A4", "", "", "", ""}
        };
        // there could be a way to get around this is we were able to use table models in order to set data

        // this sets up the Team B table
        String[][] teamBTableData = {
                {"B1", "", "", "", ""},
                {"BC", "", "", "", ""},
                {"B3", "", "", "", ""},
                {"B4", "", "", "", ""}
        };

        teamATable = new JTable(teamATableData, columnNames);
        teamBTable = new JTable(teamBTableData, columnNames);
    } // I need to make this so that it puts everyone's names in each spot

    public void addPlayersNames(){
        // this will update the tables in order to add the first names of each of the players so we can see them
        // Team A
        for(int i = 0; i < aTeam.length; i++){
            if(aTeam[i] != null){
                teamATable.setValueAt(aTeam[i].first, i, 1);
            }
        }

        // Team B
        for(int i = 0; i < bTeam.length; i++){
            if(bTeam[i] != null){
                teamBTable.setValueAt(bTeam[i].first, i, 1);
            }
        }
    }


}
