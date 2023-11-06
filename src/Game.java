import MenuGUI.java.MenuGUI;

import java.awt.*;

public class Game {
    Board board = new Board(15);
    int numberPlayers;

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Player currentTurn) {
        this.currentTurn = currentTurn;
    }

    private Player currentTurn;
    Player player1;
    Player player2;
    Player currentPlayer;
    public Game(){
        MenuGUI menu = new MenuGUI();
        do {
            numberPlayers = menu.getPlayerNum();
        }while(numberPlayers==0);
        if(numberPlayers==1){
        }
        else {
            player1 = new HumanPlayer("Player 1", '1',menu.getColor1());
            player2 = new HumanPlayer("Player 1", '2',menu.getColor2());
        }
    }
    public static void main(String[] args) {


        }

    public void playCPU(Board board, MenuGUI menu){
        new GUI(board, this);
        Player player1 = new HumanPlayer("Player 1", '1', menu.getColor1());
        CpuPlayer playerCPU = new CpuPlayer("Player 2", board, menu.getColor2());
        setCurrentTurn(player1);
        while(!board.isFull()) {
            if(currentTurn.equals(playerCPU)) {
                playerCPU.pickPlace();
            }
            if (board.isWin()) {
                break;
            }
        }
    }
}
