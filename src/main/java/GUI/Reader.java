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
    private JButton pauseResume;

    private JFrame frame;

    public Question question;
    public boolean tossup; // this keeps track of whether the current question being displayed is a tossup or a bonus
    public boolean bonusTeam;

    public Help helper;

    // game variables
    private Player[] aTeam;
    private Player[] bTeam;

    private String modifierKey = ""; // this is a key for all of the actions (Nothing for correct) but others for each

    private Game g;

    public Reader(Player[] aTeam, Player[] bTeam, Game g){

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // This should call a separate frame that will load that will tell the user how to use the program
                helper = new Help();
            }
        });

        pauseResume.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
               if(g.pauseOrResumeGame()){
                   pauseResume.setText("Resume");
               }else{
                   pauseResume.setText("Pause");
               }
           }
        });

        this.aTeam = aTeam;
        this.bTeam = bTeam;
        this.g = g;

        // this creates the JFrame
        frame = new JFrame("Reader Title");
        frame.setContentPane(this.main);
        frame.pack();
        frame.addKeyListener(this);
        main.addKeyListener(this);
        statistics.addKeyListener(this);
        teamATable.addKeyListener(this);
        teamBTable.addKeyListener(this);
        questionTextPane.addKeyListener(this);
        frame.setVisible(true);
        addPlayersNames();
    }

    // mutator methods
    public void questionAnswered(Player p, String modifierKey, boolean team){
        // team == false is team A
        // team == true is team B
        if(p == null){
            System.err.println("Typed in the Wrong Key");
            return; // in this case a typo was made by the user
        }

        switch (modifierKey) {
            case "b":
                question.blurter = p;
                if (team) {
                    // teamB
                    g.updateTeamBScore(-4);
                } else {
                    // teamA
                    g.updateTeamAScore(-4);
                }
                break;
            case "i":
                question.incorrectInterupter = p;
                if (team) {
                    // teamB
                    g.updateTeamBScore(-4);
                } else {
                    // teamA
                    g.updateTeamAScore(-4);
                }
                break;
            case "c":
                question.staller = p;
                break;
            case "x":
                question.answeredIncorrectly = p;
                break;
            default:
                // this is the case when there is no modifying key meaning that the player go the things correct
                question.answeredCorrectly = p;
                if (team) {
                    // this is the case for team B
                    g.updateTeamBScore(4);
                } else {
                    // this is the case for teamA
                    g.updateTeamAScore(4);
                }

                goToBonus(team);
                break;
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

        // this changes the question mode to bonus
        tossup = false; // this makes it so none of the rules that govern tossups apply when it is time to use a bonus
        bonusTeam = team;
        // TODO: Implements stuff mentioned above into keycodes

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

    public void setTimer(long numberOfSecondsLeft){
        if(numberOfSecondsLeft % 60 >= 10) {
            timeLeft.setText("0" + Math.floorDiv(numberOfSecondsLeft, 60) + ":" + numberOfSecondsLeft % 60);
        }else{
            timeLeft.setText("0" + Math.floorDiv(numberOfSecondsLeft, 60) + ":0" + numberOfSecondsLeft % 60);
        }
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

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // DEBUG: code remove
        char event = keyEvent.getKeyChar();

        if(!tossup){
            switch (event){
                case ' ':
                    // this is the case where the bonus was correct
                    if (bonusTeam){
                        // this means that team B has got the bonus correct

                        // this updates the score
                        g.updateTeamBScore(10);
                    } else{
                        // this means that team A has gotten the bonus correct

                        // this updates the score
                        g.updateTeamAScore(10);
                    }

            }
        }

        switch (event){
            // cases for team A
            case 'q':
                System.out.println("Q case recognized");
                questionAnswered(aTeam[0], modifierKey, false);
            case 'w':
                questionAnswered(aTeam[1], modifierKey, false);
            case 'e':
                questionAnswered(aTeam[2], modifierKey, false);
            case 'r':
                questionAnswered(aTeam[3], modifierKey, false);

                // cases for team B
            case 'a':
                questionAnswered(bTeam[0], modifierKey, true);
            case 's':
                questionAnswered(bTeam[1], modifierKey, true);
            case 'd':
                questionAnswered(bTeam[2], modifierKey, true);
            case 'f':
                questionAnswered(bTeam[3], modifierKey, true);

            default:
                System.err.println("Key not recognized");
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // this code will look at the key and it will check and see if it is one of the modifier keys
        char event = keyEvent.getKeyChar();
        switch (event){
            case 'b':
                modifierKey = "b";
            case 'i':
                modifierKey = "i";
            case 'c':
                modifierKey = "c";
            case 'x':
                modifierKey = "x";
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // this will run some code to check if the modifier key has been released
        char event = keyEvent.getKeyChar();
        switch (event){
            case 'b':
                // this checks to see if B was the previous modifier
                if(modifierKey.equals("b")){
                    modifierKey = "";
                }
            case 'i':
                // this checks to see if I was the previous modifier
                if(modifierKey.equals("i")){
                    modifierKey = "";
                }
            case 'c':
                // this checks to see if c was the previous modifier
                if(modifierKey.equals("c")) {
                    modifierKey = "";
                }
            case 'x':
                // this checks to see if x was the previous modifier
                if(modifierKey.equals("x")){
                    modifierKey = "";
                }
            default:
                break;
        }
    }

    // shortcut listener

}
