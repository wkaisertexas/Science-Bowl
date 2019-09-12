package GUI;

import javax.swing.*;

public class Help {
    private JPanel main;
    private JLabel title;
    private JTextPane testingTextPane;

    private JFrame frame;

    public Help(){
        frame = new JFrame("Science Bowl Question Helper");

        frame.setContentPane(main);
        frame.pack();
        frame.setVisible(true);
    }


}
