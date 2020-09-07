public class Game {

    /** Array of players in the game. */
    private Player[] players;

    /** The main draw deck. */
    private DrawDeck deck;

    /** The discard pile. */
    private DiscardPile discard;

    /** Index of the current player. */
    private int currPlayer;

    /** Creates a new game with NUMHUMAN human players, NUMCPU CPU players, and a _DECK. */
    public Game(int numHuman, int numCPU, DrawDeck _deck) {
        players = new Player[numHuman + numCPU];
        for (int i = 0; i < numHuman; i += 1) {
            players[i] = new HumanPlayer();
        }
        for (int i = 0; i < numCPU; i += 1) {
            players[numHuman + i] = new CPUPlayer();
        }
        deck = _deck;
        discard = new DiscardPile();
        currPlayer = 0;
    }
}
