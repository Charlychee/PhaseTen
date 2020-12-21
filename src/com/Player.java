package com;

public class Player {

    /** The cards in this player's hand. */
    private Hand hand;

    /** Indicates whether the player has completed their phase. */
    private boolean completedPhase;

    /** Current phase. */
    private Phase currentPhase;

    /** Array of piles the player has created */
    private PlayerCardPile[] cardPiles;

    public Player() {
        hand = new Hand();
        currentPhase = Phase.ORIGINAL_PHASES[0];
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
        hand.remove(card);
        pile.add(card);
    }

    /** Gets the player's hand. */
    public Hand getHand() {
        return hand;
    }

    public boolean getCompletedPhase() {
        return completedPhase;
    }

    public PlayerCardPile[] getCardPiles() {
        return cardPiles;
    }

    /** Prints out a string of information for this player. */
    public String toString() {
        return hand.toString();
    }
}
