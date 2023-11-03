import java.awt.*;

public abstract class Player {
    protected char symbol;
    protected Color color;

    protected Player(Color color) {
        this.color = color;
    }

    public abstract char getSymbol();

    public abstract Color getColor();
}