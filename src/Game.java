import MenuGUI.java.MenuGUI;

public class Game {
    public static void main(String[] args) {
        Board board = new Board(15);
        int numberPlayers;
        Player player1;
        Player player2;
        MenuGUI menu = new MenuGUI();
        numberPlayers = menu.getPlayerNum();
        if(numberPlayers==1){
            player1 = new HumanPlayer("Player 1", '1', menu.getColor1());
            player2 = new CpuPlayer("Player 2", board, menu.getColor2());
        }
        else {
            player1 = new HumanPlayer("Player 1", '1',menu.getColor1());
            player2 = new HumanPlayer("Player 1", '2',menu.getColor2());
        }
        while(!board.isFull()) {
            play(player1, board);
            if (board.isWin()) {
                break;
            }
            play(player2, board);
            if (board.isWin()) {
                break;
            }
        }
        }

    public static void play(Player player, Board board){
        if(){
            return;
        }
        boolean valid=false;
        while(!valid) {
            int[] play = menu.getInputCoordinate();
            int x = play[0];
            int y = play[1];
            System.out.println(x + ", " + y);
            if (!board.placeStone(x, y, player)) {
                System.out.println("Try again");
            }
            else{
                valid = true;
            }
        }
    }
}
