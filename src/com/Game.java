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
    }

    public void startGame() {
        deck.shuffle();
        System.out.println("Deck: \n" + deck.toString());
        deck.deal(10, players);
    }

    /** Returns the players. */
    public Player[] getPlayers() {
        return players;
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
