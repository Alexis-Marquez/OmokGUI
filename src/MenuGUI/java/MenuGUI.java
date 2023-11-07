package MenuGUI.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {
    private Color color1;
    private Color color2;

    public int getPlayerNum() {
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }
    //Add all the methods for the Menu:
    // - Ask number of players
    // - Ask strategy
    // - Ask for piece color (Optional)
    //Add fields:
    // - Number of players
    // - Strategy
    // - Color (optional)
    // Add getters for all fields
}


class OmokMenu1 {
    private static final Color BROWN = new Color(100, 69, 19);

    private JFrame frame;
    private JPanel panel;
    private JButton newGameButton;
    private JButton rulesButton;
    // private JButton setModeButton;
    private JButton quitButton;
    private JButton strategyButton;

    public OmokMenu1() {
        frame = new JFrame("Omok");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBackground(BROWN);

        newGameButton = createStyledButton("Start New Game", Color.BLACK);
        rulesButton = createStyledButton("Rules", Color.BLACK);
        // setModeButton = createStyledButton("Set Mode", Color.BLACK);
        quitButton = createStyledButton("Quit", Color.BLACK);
        strategyButton = createStyledButton("CPU Strategy", Color.BLACK);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNumberOfPlayers();
            }
        });

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRules();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // setModeButton.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // setMode();
        // }
        // });

        strategyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectStrategy();
            }
        });

        panel.add(newGameButton);
        panel.add(rulesButton);
        panel.add(strategyButton);
        // panel.add(setModeButton);
        panel.add(quitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void selectNumberOfPlayers() {
        Object[] options = { "1", "2" };
        int choice = JOptionPane.showOptionDialog(null, "Select Number of Players", "Number of Players",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            selectPlayerColor("1");
        } else if (choice == 1) {
            selectPlayerColor("2");
        }
    }

    private void selectPlayerColor(String numPlayers) {
        Object[] colorOptions = { "Black", "White" };
        int choice = JOptionPane.showOptionDialog(null, "Select Your Color (Player " + numPlayers + ")", "Player Color",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, colorOptions, colorOptions[0]);

        String color = (choice == 0) ? "Black" : "White";

        JOptionPane.showMessageDialog(null, "You selected " + color + " as your color (Player " + numPlayers + ").");
    }

    private void selectStrategy() {
        Object[] strategyOptions = { "Smart", "Random" };
        int choice = JOptionPane.showOptionDialog(null, "Select Your Strategy", "Strategy", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, strategyOptions, strategyOptions[0]);

        String strategy = (choice == 0) ? "Smart" : "Random";

        JOptionPane.showMessageDialog(null, "You selected the " + strategy + " strategy.");
    }

    private JButton createStyledButton(String text, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(textColor);
        return button;
    }

    private void displayRules() {
        String rules = "Players alternate turns placing a stone of their\n" +
                "color on an empty intersection.\n" +
                "The winner is the first player to form an unbroken\n" +
                "line of five stones of their color horizontally,\n" +
                "vertically, or diagonally.";
        JOptionPane.showMessageDialog(null, rules, "Rules",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // private void setMode() {
    // JFrame modeFrame = new JFrame("Select Game Mode");
    // modeFrame.setSize(300, 150);
    // modeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    // modeFrame.getContentPane().setBackground(BROWN);

    // JPanel modePanel = new JPanel();
    // modePanel.setLayout(new GridLayout(1, 3));
    // modePanel.setBackground(BROWN);

    // JButton easyButton = createStyledButton("Easy", Color.BLACK);
    // JButton mediumButton = createStyledButton("Medium", Color.BLACK);
    // JButton hardButton = createStyledButton("Hard", Color.BLACK);

    // easyButton.addActionListener(new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // JOptionPane.showMessageDialog(null, "Easy mode selected.");
    // modeFrame.dispose();
    // }
    // });

    // mediumButton.addActionListener(new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // JOptionPane.showMessageDialog(null, "Medium mode selected.");
    // modeFrame.dispose();
    // }
    // });

    // hardButton.addActionListener(new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // JOptionPane.showMessageDialog(null, "Hard mode selected.");
    // modeFrame.dispose();
    // }
    // });

    // modePanel.add(easyButton);modePanel.add(mediumButton);modePanel.add(hardButton);modeFrame.add(modePanel);modeFrame.setVisible(true);

    //
}

