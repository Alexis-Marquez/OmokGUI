//import MenuGUI.java.MenuGUI;
//
//public class Game {
//    public static void main(String[] args) {
//        Board board = new Board(15);
//        int numberPlayers;
//        Player player1;
//        Player player2;
//        MenuGUI menu = new MenuGUI();
//        numberPlayers = menu.getPlayerNum();
//        if(numberPlayers==1){
//            player1 = new HumanPlayer("Player 1");
//            player2 = new CpuPlayer("Player 2", board);
//        }
//        else {
//            player1 = new HumanPlayer("Player 1");
//            player2 = new HumanPlayer("Player 1");
//        }
//        int counter = 0;
//        while(counter<=board.size()*board.size()) {
//            play(player1, board);
//            counter++;
//            if(board.isWin()){
//                System.out.println("Player 1 has won!");
//                menu.draw();
//                break;
//            }
//            if(counter>=board.getBOARD_SIZE()*board.getBOARD_SIZE()){
//                System.out.println("Tied game");
//                menu.draw();
//                break;
//            }
//            menu.draw();
//            play(player2, board, menu);
//            counter++;
//            if(board.isWin()){
//                System.out.println("Player 2 has won!");
//                menu.draw();
//                break;
//            }
//            if(counter>=board.getBOARD_SIZE()*board.getBOARD_SIZE()){
//                System.out.println("Tied game");
//                menu.draw();
//                break;
//            }
//            menu.draw();
//        }
//
//    }
//    public static void play(Player player, Board board){
//        if(){
//            return;
//        }
//        boolean valid=false;
//        while(!valid) {
//            int[] play = menu.getInputCoordinate();
//            int x = play[0];
//            int y = play[1];
//            System.out.println(x + ", " + y);
//            if (!board.placeStone(x, y, player)) {
//                System.out.println("Try again");
//            }
//            else{
//                valid = true;
//            }
//        }
//    }
//}
