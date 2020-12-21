package com;

public abstract class Player {

    /** The cards in this player's hand. */
    protected Hand hand;

    /** Indicates whether the player has completed their phase. */
    protected boolean completedPhase;

    /** Current phase. */
    protected Phase currentPhase;

    /** Array of piles the player has created */
    protected PlayerCardPile[] cardPiles;

    /** Number of players create. */
    protected static int numPlayers = 0;

    /** Id of this player. */
    protected int id;

    public Player() {
        hand = new Hand();
        currentPhase = Phase.ORIGINAL_PHASES[0];
        id = numPlayers;
        numPlayers += 1;
    }

    /** Draws a card from PILE. */
    public void draw(CardPile pile) {
        if(pile instanceof DrawDeck || pile instanceof DiscardPile) {
            Card drawnCard = pile.remove();
            hand.add(drawnCard);
        }
        else throw new PTException("Cannot draw from CardPile that is not DrawDeck or DiscardPile");
    }

    /** Adds card into PILE. (Also used for discard) */
    public void addToPile(Card card, CardPile pile) {
        // Need to check if card is in hand, before removing it.
    }

    /** Gets the player's hand. */
    public Hand getHand() {
        return hand;
    }

    /** Gets the id of this player. */
    public int getID() {
        return id;
    }

    /** Prints out a string of information for this player. */
    public String toString() {
        return hand.toString();
    }
}
