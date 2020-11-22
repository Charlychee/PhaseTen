package com;

public abstract class Player {

    /** The cards in this player's hand. */
    private Hand hand;

    /** Indicates whether the player has completed their phase. */
    private boolean completedPhase;

    /** Current phase. */
    private Phase currentPhase;

    public Player() {
        hand = new Hand();
        currentPhase = Phase.ORIGINAL_PHASES[0];
    }

    /** Draws a card from PILE. */
    public void draw(CardPile pile) {
        Card drawnCard = pile.remove();
        hand.add(drawnCard);
    }

    /** Gets the player's hand. */
    public Hand getHand() {
        return hand;
    }

    /** Prints out a string of information for this player. */
    public String toString() {
        return hand.toString();
    }
}
