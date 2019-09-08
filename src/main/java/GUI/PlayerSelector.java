package GUI;

import logical.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;


public class PlayerSelector {
    private JPanel main;
    private JPanel newPlayer;
    private JLabel newPlayerLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel graduationYearLabel;
    private JComboBox<String> graduationYear;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JButton createPlayerButton;

    private JFrame frame;

    private Setup s;
    private String position;

    public PlayerSelector(Setup s, String position) {
        // this creates the JFrame
        frame = new JFrame();
        frame.setContentPane(this.main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,600);
        frame.setLocation(250, 200);
        frame.setVisible(true);

        setGraduationYearProp();

        this.s = s;
        this.position = position;

        createPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // I need to find some way to pass this data back into the other GUI
                System.out.println("Creating New Player: " + firstNameInput.getText() + ", " + lastNameInput.getText() + ", " + getGraduationYear());

                s.setPlayer(position, new Player(firstNameInput.getText(), lastNameInput.getText(), getGraduationYear()));

                frame.dispose();
            }
        });
    }

    public void setGraduationYearProp(){
        // this creates the Selection Options for the JCombo Box
        int schoolYearDate;
        if(LocalDate.now().getMonth().getValue() >= 6){
            schoolYearDate = LocalDate.now().getYear() + 1;
        } else{
            schoolYearDate = LocalDate.now().getYear();
        }

        // this sets everything
        String[] elements = new String[4];
        elements[0] = "Class of " + schoolYearDate;
        elements[1] = "Class of " + (schoolYearDate + 1);
        elements[2] = "Class of " + (schoolYearDate + 2);
        elements[3] = "Class of " + (schoolYearDate + 3);

        DefaultComboBoxModel<String> model =  new DefaultComboBoxModel<>(elements);
        graduationYear.setModel(model);
    }


    public int getGraduationYear(){
        int schoolYearDate;
        if(LocalDate.now().getMonth().getValue() >= 6){
            schoolYearDate = LocalDate.now().getYear() + 1;
        } else{
            schoolYearDate = LocalDate.now().getYear();
        }
        return schoolYearDate + graduationYear.getSelectedIndex();
    }

}
