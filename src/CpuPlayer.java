import java.awt.*;
import java.util.Random;

public class CpuPlayer extends Player {
        private Board board;
        private String name;
        public CpuPlayer(Color color){
            super(color);
            this.name = "Player 2";
            this.symbol = 'x';
        }
        public CpuPlayer(String name, Board board, Color color){
            super(color);
            this.name=name;
            this.board=board;
            this.symbol = 'x';
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
    public String getName() {
        return name;
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }
    public Color getColor(){return this.color;}
}
