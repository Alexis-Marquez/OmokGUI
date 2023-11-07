//import MenuGUI.java.MenuGUI;

import java.awt.*;

public class Game {
    Board board = new Board(16);
    int numberPlayers;

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
        //MenuGUI menu = new MenuGUI();
//        do {
//            numberPlayers = menu.getPlayerNum();
//        }while(numberPlayers==0);
        numberPlayers = 2;
        if(numberPlayers==1){
            player1 = new HumanPlayer("Player 1", '1',Color.WHITE);
            player2 = new CpuPlayer("Player 2", this.board,Color.BLACK);
        }
        else {
            player1 = new HumanPlayer("Player 1", '1',Color.WHITE);
            player2 = new HumanPlayer("Player 2", '2',Color.BLACK);
        }
        currentTurn = player1;
        GUI gui = new GUI(board, this);
    }
    public static void main(String[] args) {
            new Game();
        }

    public void playCPU(Board board){

        CpuPlayer cpu = (CpuPlayer) player2;
    }
}
