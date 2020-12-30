package com;

public class Game {

    /** Array of players in the game. */
    private Player[] players;

    /** The main draw deck. */
    private DrawDeck deck;

    /** The discard pile. */
    private DiscardPile discard;

    /** Index of the current player. */
    private int currPlayer;

    // TODO: Ensure that at least 1 player is passed into the game.
    // TODO: Create games to specify names for players
    /** Creates a new game with NUMHUMAN human players, NUMCPU CPU players. */
    public Game(int numHuman, int numCPU) {
        players = new Player[numHuman + numCPU];
        for (int i = 0; i < numHuman; i += 1) {
            players[i] = new HumanPlayer();
        }
        for (int i = 0; i < numCPU; i += 1) {
            players[numHuman + i] = new CPUPlayer();
        }
        deck = new DrawDeck();
        discard = new DiscardPile();
        currPlayer = 0;
        players[0].currentTurn = true;
    }

    public void startGame() {
        deck.shuffle();
        deck.deal(10, players);
        discard.add(deck.remove());
    }

    /** Returns the card at the top of the discard pile without removing it. */
    public Card getDiscardTop() {
        return discard.peek();
    }
    /** Returns the current player having a turn. */
    public Player getCurrentPlayer() {
        return players[currPlayer];
    }

    /** Returns the players. */
    public Player[] getPlayers() {
        return players;
    }

    /** Returns the draw deck. */
    public DrawDeck getDeck() {
        return deck;
    }

    /** Returns the discard pile. */
    public DiscardPile getDiscard() {
        return discard;
    }

    /** Switches currPlayer to the next person's turn and returns the currPlayer. */
    public Player switchTurn() {
        currPlayer = (currPlayer + 1) % players.length;
        Player curr = getCurrentPlayer();
        curr.setCurrentTurn(true);
        return curr;
    }

    /** Returns a string of information for this game. */
    public String toString() {
        String info = "Deck: \n" + deck.toString() + "Discard: \n" + discard.toString() + "Players: \n";
        for (Player p : players) {
            info += p.toString();
        }
        return info;
    }
}
