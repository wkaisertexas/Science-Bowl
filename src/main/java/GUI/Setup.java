package GUI;

import logical.Player;

import javax.swing.*;
import java.awt.event.*;

public class Setup {
    private JLabel title;
    private JPanel main;
    private JPanel playerPositions;
    private JPanel database;
    private JLabel databaseLabel;
    private JTextField databaseFilePath;
    private JSlider difficulty;
    private JLabel difficultySliderReadout;
    private JButton selectFile;
    private JButton startGameButton;
    private JPanel difficultySetting;
    private JPanel teamAPanel;
    private JPanel teamBPanel;
    private JLabel a1Label;
    private JLabel acLabel;
    private JLabel a3Label;
    private JLabel a4Label;
    private JLabel b1Label;
    private JLabel b4Label;
    private JLabel b3Label;
    private JLabel bcLabel;

    private JFrame frame;

    public boolean startGame = false;

    private Player[] teamA = new Player[4];
    private Player[] teamB = new Player[4];

    public Setup() {
        // this starts all of the listeners and stuff
        selectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // This occurs when the selectFile file JButton is pressed
                System.out.println("Select File Button Pressed");

                // This will launch a separate window that will allow the user to launch the file
                JOptionPane.showMessageDialog(null, "Sorry I haven't set this up yet. Will work on it when I have time");

            }
        });
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Start Game Button Pressed");
                startGame = true;
            }
        });
        difficulty.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                System.out.println("Difficulty Slider has Moved");
                difficultySliderReadout.setText("Target Accuracy: " + getTargetAccuracy() + "%");
            }
        });

        // Start of TeamA
        a1Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playerSetupSelector("A1");
            }
        });
        acLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playerSetupSelector("AC");
            }
        });
        a3Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playerSetupSelector("A3");
            }
        });
        a4Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playerSetupSelector("A4");
            }
        });

        // Start of TeamB
        b1Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playerSetupSelector("B1");
            }
        });
        bcLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playerSetupSelector("BC");
            }
        });
        b3Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playerSetupSelector("B3");
            }
        });
        b4Label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playerSetupSelector("B4");
            }
        });

        frame = new JFrame("Science Bowl App Setup");
        frame.setContentPane(this.main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 500);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // mutator methods
    public void playerSetupSelector(String position){
        // this will load a separate GUI that will allow a person to correct their errors.
        PlayerSelector ps = new PlayerSelector(this, position);
    }

    public void setPlayer(String position, Player player){
        switch(position){
            case "A1" :
                teamA[0] = player;
                break;
            case "AC" :
                teamA[1] = player;
                break;
            case "A3" :
                teamA[2] = player;
                break;
            case "A4":
                teamA[3] = player;
                break;
            case "B1" :
                teamB[0] = player;
                break;
            case "BC" :
                teamB[1] = player;
                break;
            case "B3" :
                teamB[2] = player;
                break;
            case "B4" :
                teamB[3] = player;
                break;
            default :
                System.err.println("Error there is no position named: " + position);
        }
    }

    // accessor methods
    public int getTargetAccuracy(){
        return difficulty.getValue();
    }

} // end of setup
