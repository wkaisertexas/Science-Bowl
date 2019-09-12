package GUI;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logical.Player;
import logical.Question;

public class Reader {
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
    private JTextArea questionTextArea;
    private JLabel questionInfoLabel;
    private JButton helpButton;
    private JLabel answer;
    private JTable teamATable;
    private JTable teamBTable;

    private JFrame frame;

    public Question question;

    public Help helper;

    // game variables
    private Player[] aTeam;
    private Player[] bTeam;


    public Reader(Player[] aTeam, Player[] bTeam){

        // this creates the JFrame
        frame = new JFrame("Reader Title");
        frame.setContentPane(this.main);


        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // This should call a separate frame that will load that will tell the user how to use the program
                helper = new Help();

            }
        });

        this.aTeam = aTeam;
        this.bTeam = bTeam;

        // these are the things that have to be done in order to finish all of the graphical user interface
        setUpTables();
    }












    // mutator methods
    public void updateQuestion(Question q){
        // this sets the text of the questionTextArea label
        questionInfoLabel.setText("Type: Tossup Format: " + q.tossupTypeToString() + " Category:" + q.category);

        // this sets the text of the body of the questionTextArea
        questionTextArea.setText(q.tossupQuestion);

        // this sets the label that contains the correct answer
        answer.setText("Answer: " + q.tossupAnswer);

        // this sets the local question to the class variable question
        question = q;
    }




    public void updateTeamAScore(int score){

    }

    public void updateTeamBScore(int score){}



    // setup methods
    public void setUpTables(){


        // this sets up the Team B table



    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        // this will set up the dimensionality for table
        String[] columnNames = {"Position", "Name", "Positive Points", "Negative Points", "Overall Points"};
        // this sets up the Team A table
        String[][] teamATableData = {
                {"A1", "", "", "", ""},
                {"AC", "", "", "", ""},
                {"A3", "", "", "", ""},
                {"A4", "", "", "", ""}
        };
        teamATable = new JTable(teamATableData, columnNames);

    }
}
