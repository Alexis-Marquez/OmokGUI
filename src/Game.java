//import MenuGUI.java.MenuGUI;

import java.awt.*;

public class Game {
    MenuGUI menu;
    GUI gui;
    Board board = new Board(16);
    int numberPlayers;

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    boolean game;
    public Player getCurrentTurn() {
        return currentTurn;
    }

    public void nextTurn() {
        if(player1.equals(currentTurn)){
            currentTurn = player2;
        }
        else currentTurn = player1;
        if(currentTurn.getClass()==CpuPlayer.class){
            CpuPlayer cpu = (CpuPlayer) currentTurn;
            cpu.pickPlace();
            nextTurn();
        }
    }

    private Player currentTurn;
    Player player1;
    Player player2;
    public Game(){
        game = false;
        menu = new MenuGUI(this);
        gui = new GUI(board, this, menu.getFrame());

    }
    public static void main(String[] args) {
            Game game = new Game();
        }

    public void init(){
        numberPlayers = menu.getPlayerNum();
        if(numberPlayers==1){
            player1 = new HumanPlayer("Player 1", '1',Color.BLACK);
            player2 = new CpuPlayer("Player 2", this.board,Color.WHITE);
        }
        else {
            player1 = new HumanPlayer("Player 1", '1',Color.BLACK);
            player2 = new HumanPlayer("Player 2", '2', Color.WHITE);
        }
        currentTurn = player1;
    }
}
