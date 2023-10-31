/**
 * A player in an Omok game. It holds the name of the player and
 * can be used to identify a specific player throughout the game.
 * The Player class helps to keep track of the moves made by each
 * player during the game.
 */
public class HumanPlayer extends Player{

    /** Name of this player. */
    private final String name;
    private final char Symbol;

    /** Create a new player with the given name. */
    public HumanPlayer(String name) {
        this.name = name; this.Symbol='1';
    }
    public HumanPlayer(String name, char Symbol) {
        this.name = name; this.Symbol=Symbol;
    }

    /** Return the name of this player. */
    public String name() {
        return name;
    }
    public char getSymbol(){return this.Symbol;}
}
