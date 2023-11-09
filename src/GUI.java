import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.*;

public class GUI {
    private final Board board;
    private final Game game;
    JToolBar toolPanel;
    MenuGUI menu;
    JFrame window;
    JPanel gamePanel;
    JPanel footer;
    JLabel footerText;
    BoardPanel boardDrawing;

    public GUI(Board board, Game game, JFrame window) {
        this.board = board;
        this.game = game;
        this.window = window;
        gamePanel = new JPanel();
        gamePanel.setSize(new Dimension(450, 500));
        JPanel buttons = new JPanel();
        toolPanel = new JToolBar();
        toolPanel.setLayout(new GridLayout(1,3));
        JButton play;
        play = new JButton("New game");
        buttons.add(play);
        JButton goToMenu = new JButton("Menu");
        buttons.add(goToMenu);
        buttons.setSize(100,50);
        toolPanel.add(buttons);
        goToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToMenu();
            }
        });
        play.setIcon(getNormalIcon("src/playButton.jpg"));
        play.setToolTipText("Play a new game");
        play.setMnemonic(KeyEvent.VK_N);
        goToMenu.setIcon(getNormalIcon("src/goBack.jpeg"));
        goToMenu.setToolTipText("Go back to the main menu");
        goToMenu.setMnemonic(KeyEvent.VK_M);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.menu.startNewGame();
            }
        });
        footer = new JPanel();
        footerText = new JLabel("Player 1 turn");
        footer.add(footerText);
        this.gamePanel.setLayout(new BorderLayout());
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        boardDrawing = new BoardPanel(this.board,this.game);
        boardDrawing.addMouseListener(pickPlace());
        middlePanel.add(boardDrawing);
        gamePanel.add(middlePanel);
        gamePanel.add(toolPanel, BorderLayout.NORTH);
        gamePanel.add(footer,BorderLayout.SOUTH);
        window.add(gamePanel);

    }

    private MouseAdapter pickPlace(){
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                BigDecimal x = BigDecimal.valueOf((double) e.getX() / 25);
                BigDecimal xCord = x.setScale(0, RoundingMode.HALF_UP);
                BigDecimal y = BigDecimal.valueOf((double) e.getY() / 25);
                BigDecimal yCord = y.setScale(0, RoundingMode.HALF_UP);
                if(board.isOccupied(xCord.intValue(),yCord.intValue())){
                    if(board.isFull()){
                        footerText.setText("All places full, It's a draw!");
                    }
                    footerText.setText("Place is Occupied, try another intersection!");
                }
                else {
                    if (!board.isWin()) {
                        board.placeStone(xCord.intValue(), yCord.intValue(), game.getCurrentTurn());
                        board.setWon(board.isWonBy(yCord.intValue(), xCord.intValue(), game.getCurrentTurn()));
                        if (board.isWin()) {
                            footerText.setText(game.getCurrentTurn().name + " has Won!");
                        } else {
                            game.nextTurn();
                            footerText.setText(game.getCurrentTurn().name + "'s turn");
                        }
                        boardDrawing.repaint();
                    }
                    else{
                        footerText.setText(game.getCurrentTurn().name + " has Won!");
                        game.menu.setButtonText("Start New Game");
                        game.menu.gameOngoing = false;
                        boardDrawing.repaint();
                    }
                }
            }
    };}
    public void goBackToMenu(){
        game.menu.setVisibility(true);
        gamePanel.setVisible(false);
    }

    public void setVisibility(boolean b) {
        gamePanel.setVisible(b);
    }
    private Icon getNormalIcon(String path){
        Image img = new ImageIcon(path).getImage();
        Image newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
