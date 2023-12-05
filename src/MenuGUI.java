import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class MenuGUI {
    private Color BROWN = new Color(100, 69, 19);

    public JFrame getFrame() {
        return frame;
    }

    private final Game game;
    private final JFrame frame;
    private final JPanel panel;
    private final JButton newGameButton;
    private final JPanel messagePanel; // New panel for messages
    private final JLabel messageLabel; // Label to display messages
    public boolean gameOngoing = false;

    public MenuGUI(Game game) {
        this.game = game;
        frame = new JFrame("Omok");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1)); // Increased rows for the new message panel
        panel.setBackground(BROWN);

        newGameButton = createStyledButton("Start New Game", Color.BLACK);
        JButton rulesButton = createStyledButton("Rules", Color.BLACK);
        JButton quitButton = createStyledButton("Quit", Color.BLACK);
        JButton strategyButton = createStyledButton("CPU Strategy", Color.BLACK);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOngoing) startNewGame();
                else continueGame();
            }
        });
        newGameButton.setMnemonic(KeyEvent.VK_N);

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRules();
            }
        });
        rulesButton.setMnemonic(KeyEvent.VK_R);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        quitButton.setMnemonic(KeyEvent.VK_Q);

        strategyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectStrategy();
            }
        });

        // Initialize the message panel
        messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        messagePanel.setBackground(Color.WHITE);

        // Initialize the message label
        messageLabel = new JLabel();
        messagePanel.add(messageLabel);

        panel.add(newGameButton);
        panel.add(rulesButton);
        panel.add(strategyButton);
        panel.add(quitButton);
        panel.add(messagePanel); // Add the message panel to the main panel

        frame.add(panel);
        frame.setVisible(true);
    }

    public void startNewGame() {
        Object[] options = {"1", "2"};
        int choice = JOptionPane.showOptionDialog(null, "Select Number of Players", "Number of Players",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 1) {
            // If 2 players are selected, create a smaller panel for additional options
            createTwoPlayerOptionsPanel();
        } else {
            // If 1 player is selected, continue with the regular game start
            gameOngoing = true;
            newGameButton.setText("Continue");
            game.init(choice);
            panel.setVisible(false);
            game.gui.setVisibility(true);
            game.gui.boardDrawing.repaint();
        }
    }

    private JPanel createTwoPlayerOptionsPanel() {
        JPanel playerPanel = createPlayerPanel();
        JPanel opponentPanel = createOpponentPanel();

        // Include both player and opponent panels in a parent panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(3, 1)); // Increased rows for the new message panel
        optionsPanel.add(playerPanel);
        optionsPanel.add(opponentPanel);

        // Add an empty panel for messages
        JPanel messagePanel = createMessagePanel();
        optionsPanel.add(messagePanel);

        // Show the option dialog
        Object[] inputFields = {optionsPanel};
        int option = JOptionPane.showConfirmDialog(null, inputFields, "Enter Player and Opponent Information", JOptionPane.OK_CANCEL_OPTION);

        // Process the selected option
        if (option == JOptionPane.OK_OPTION) {
            // Retrieve player's information
            JTextField playerHostField = (JTextField) playerPanel.getComponent(1);
            JTextField playerIPField = (JTextField) playerPanel.getComponent(3);
            String playerHost = playerHostField.getText();
            String playerIP = playerIPField.getText();

            // Add logic to connect to the online game server using the provided information
            setMessage("Connected as host at: " + playerHost + " with IP: " + playerIP);
            // Implement the rest of the online game logic based on your requirements
            // ...
            gameOngoing = true;
            newGameButton.setText("Continue");
            panel.setVisible(false);
            game.gui.setVisibility(true);
            game.gui.boardDrawing.repaint();
        } else {
            // User canceled the input
            setMessage("Online game connection canceled.");
        }

        return optionsPanel;
    }

    private JPanel createMessagePanel() {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        messagePanel.setBackground(Color.WHITE);
        return messagePanel;
    }


    private JPanel createPlayerPanel() {
        JTextField playerHostField = new JTextField();
        JTextField playerIPField = new JTextField();

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 2));
        playerPanel.setBackground(Color.WHITE);
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));

        playerPanel.add(new JLabel("Host Name:"));
        playerPanel.add(playerHostField);
        playerPanel.add(new JLabel("IP:"));
        playerPanel.add(playerIPField);

        return playerPanel;
    }

    private JPanel createOpponentPanel() {
        JTextField opponentHostField = new JTextField();
        JTextField opponentIPField = new JTextField();

        JPanel opponentPanel = new JPanel();
        opponentPanel.setLayout(new GridLayout(2, 2));
        opponentPanel.setBackground(Color.WHITE);
        opponentPanel.setBorder(BorderFactory.createTitledBorder("Opponent"));

        opponentPanel.add(new JLabel("Host Name:"));
        opponentPanel.add(opponentHostField);
        opponentPanel.add(new JLabel("IP:"));
        opponentPanel.add(opponentIPField);

        return opponentPanel;
    }

    private void continueGame() {
        panel.setVisible(false);
        game.gui.setVisibility(true);
    }

    private void selectStrategy() {
        Object[] strategyOptions = {"Smart", "Random"};
        int choice = JOptionPane.showOptionDialog(null, "Select Your Strategy", "Strategy", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, strategyOptions, strategyOptions[0]);

        String strategy = (choice == 0) ? "Smart" : "Random";
        game.strategy = strategy;
        gameOngoing = false;
        setMessage("You selected the " + strategy + " strategy.");
        newGameButton.setText("New Game");
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

    public void setVisibility(boolean x) {
        panel.setVisible(x);
    }

    public void setButtonText(String text) {
        newGameButton.setText(text);
    }

    // Method to set the message text
    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuGUI(new Game());
            }
        });
    }
}
