package GUI;

import com.company.Main;
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
    private JTextField saveLocation;
    private JLabel saveLocationLabel;
    private JButton selectFolder;

    private JFrame frame;
    private JFileChooser fileChooser = new JFileChooser();

    public Main m;

    public boolean startGame = false;

    private Player[] teamA = new Player[4];
    private Player[] teamB = new Player[4];

    public Setup(Main m) {
        // this starts all of the listeners and stuff
        selectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // This occurs when the selectFile file JButton is pressed
                System.out.println("Select File Button Pressed");

                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showOpenDialog(frame);

                databaseFilePath.setText(fileChooser.getSelectedFile().getPath());
            }
        });
        selectFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Select Folder Button Pressed");

                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.showOpenDialog(frame);

                saveLocation.setText(fileChooser.getSelectedFile().getPath());
            }
        });
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Start Game Button Pressed");
                frame.setVisible(false);
                m.startGame();
                // frame.dispose();
            }
        });
        difficulty.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                System.out.println("Difficulty Slider has Moved");
                difficultySliderReadout.setText("Target Accuracy: " + difficulty.getValue() + "%");
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

        // this saves the main class as a localized variable
        this.m = m;
    }

    // mutator methods

    public void newGame(){
        // this makes all of the class that allow for the new setup
        frame.setVisible(true);
    }

    public void playerSetupSelector(String position){
        // this will load a separate GUI that will allow a person to correct their errors.
        PlayerSelector ps = new PlayerSelector(this, position);
    }

    public void setPlayer(String position, Player player){
        switch(position){
            case "A1" :
                teamA[0] = player;
                a1Label.setText("A1: " + player.first + ", " + player.last);
                break;
            case "AC" :
                teamA[1] = player;
                acLabel.setText("AC: " + player.first + ", " + player.last);
                break;
            case "A3" :
                teamA[2] = player;
                a3Label.setText("A3: " + player.first + ", " + player.last);
                break;
            case "A4":
                teamA[3] = player;
                a4Label.setText("A4: " + player.first + ", " + player.last);
                break;
            case "B1" :
                teamB[0] = player;
                b1Label.setText("B1: " + player.first + ", " + player.last);
                break;
            case "BC" :
                teamB[1] = player;
                bcLabel.setText("BC: " + player.first + ", " + player.last);
                break;
            case "B3" :
                teamB[2] = player;
                b3Label.setText("B3: " + player.first + ", " + player.last);
                break;
            case "B4" :
                teamB[3] = player;
                b4Label.setText("B4: " + player.first + ", " + player.last);
                break;
            default :
                System.err.println("Error there is no position named: " + position);
        }
    }

    // accessor methods
    public Double getTargetAccuracy(){
        return .01 * difficulty.getValue();
    }

    public Player[] getTeamA(){return teamA;}
    public Player[] getTeamB(){return teamB;}

    public String getDatabasePath(){
        return databaseFilePath.getText(); // I still need to add verification to make sure this is a valid file
    }

    public String getSaveLocation(){
        return saveLocation.getText(); // I still need to add verification to make sure this is a valid location
    }

} // end of setup
