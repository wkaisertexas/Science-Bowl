package GUI;

import javax.swing.*;

public class Help {
    private JPanel main;
    private JLabel title;
    private JTextPane helpText;
    private JTable keybinds;

    private JFrame frame;

    public Help(){
        frame = new JFrame("Science Bowl Question Helper");

        frame.setContentPane(main);
        frame.pack();
        frame.setVisible(true);
    }


    private void createUIComponents() {
        String[] rowTitles = {"Key", "Use"};
        String[][] keybindData = {
                {"B", "Blurt Key Modifier"},
                {"I", "Interrupt Key Modifier"},
                {"C", "Conversing Penalty Key Modifier"},
                {"X", "Incorrect"},

                {"Q", "A1"},
                {"W", "AC"},
                {"E", "A3"},
                {"R", "A4"},

                {"A", "B1"},
                {"S", "BC"},
                {"D", "B3"},
                {"F", "B4"}
        };
        keybinds = new JTable(keybindData, rowTitles);
    }
}
