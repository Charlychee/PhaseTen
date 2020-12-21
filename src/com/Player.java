package com;

public class Player {

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

    /** Indicates whether it's this player's turn. */
    protected boolean currentTurn;

    /** Indicates whether this player has drawn a card. */
    protected boolean hasDrawn;

    public Player() {
        hand = new Hand();
        currentPhase = Phase.ORIGINAL_PHASES[0];
        id = numPlayers;
        numPlayers += 1;
    }

    /** Draws a card from PILE. */
    public Card draw(CardPile pile) {
        if (!currentTurn) {
            throw new PTException("Not the player's turn.");
        } else if (hasDrawn) {
            throw new PTException("Player has already drawn.");
        }
        if(pile instanceof DrawDeck || pile instanceof DiscardPile) {
            Card drawnCard = pile.remove();
            hand.add(drawnCard);
            hasDrawn = true;
            return drawnCard;
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

    /** Gets the id of this player. */
    public int getID() {
        return id;
    }

    /** Sets this player's currentTurn. */
    public void setCurrentTurn(boolean turn) {
        currentTurn = turn;
    }

    /** Prints out a string of information for this player. */
    public String toString() {
        return hand.toString();
    }
}
