import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.*;

public class GUI {
    private final Board board;
    private final Game game;
    JToolBar toolPanel;
    JFrame window;
    JPanel footer;
    JLabel footerText;
    BoardPanel boardDrawing;

    public GUI(Board board, Game game) {
        this.board = board;
        this.game = game;
        window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(new Dimension(450,500));
        toolPanel = new JToolBar();
        toolPanel.setLayout(new GridLayout(1,3));
        JButton play;
        play = new JButton("Play");
        toolPanel.add(play);
        toolPanel.add( new JButton("Menu"));
        footer = new JPanel();
        footerText = new JLabel("Player 1 turn");
        footer.add(footerText);
        this.window.setLayout(new BorderLayout());
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        boardDrawing = new BoardPanel(this.board,this.game);
        boardDrawing.addMouseListener(pickPlace());
        middlePanel.add(boardDrawing);
        window.add(middlePanel);
        window.add(toolPanel, BorderLayout.NORTH);
        window.add(footer,BorderLayout.SOUTH);
        window.setVisible(true);
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
                        boardDrawing.repaint();
                    }
                }
            }
    };}
}
