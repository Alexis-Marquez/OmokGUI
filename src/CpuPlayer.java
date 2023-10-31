import java.util.Random;

public class CpuPlayer extends Player {
        private Board board;
        private char symbol;
        public CpuPlayer(){
            this.symbol = '1';
        }
        public CpuPlayer(char symbol, Board board){
            this.symbol=symbol;
            this.board=board;
        }
    public boolean pickPlace() {
        Random rand = new Random();
        int upperbound = board.size();
        int randX = rand.nextInt(upperbound);
        int randY = rand.nextInt(upperbound);
        System.out.println(randY+", "+randX);
        if(board.isOccupied(randX,randY)){
            board.placeStone(randX, randY, this);
            return true;
        }
        else{
            this.pickPlace();
        }
        return false;
    }
}
