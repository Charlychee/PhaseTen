package com;

public abstract class Player {

    // TODO: Connect player to a game by adding a Game instance variable? Then the game's deck and discard could be stored here.
    /** The cards in this player's hand. */
    protected Hand hand;

    /** Indicates whether the player has completed their phase. */
    protected boolean completedPhase;

    /** Current phase. */
    protected Phase currentPhase;

    /** Number of current phase. */
    protected int phaseNumber;

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
        phaseNumber = 1;
        currentPhase = Phase.ORIGINAL_PHASES[phaseNumber];
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

    /** Adds CARD from hand into PILE. (Also used for discard) */
    public void addToPile(Card card, CardPile pile) {
        hand.remove(card);
        pile.add(card);
    }

    // TODO: Instead of having to make a copy of the card, consider the following:
    //  - create another equal method in Card that compares a card with a type and value
    //  - create a method in Hand that checks if it contains a card of a given type and value, and then returns it.
    /** Adds a card from hand that matches TYPE and VALUE into PILE. (Also used for discard). */
    public void discard(Card.typeEnum type, int value, CardPile pile) {
        if (!(pile instanceof DiscardPile)) {
            throw new PTException("Must discard into a CardPile of type DiscardPile.");
        }
        if (!currentTurn) {
            throw new PTException("Not the player's turn.");
        }
        if (!hasDrawn) {
            throw new PTException("Player must draw before discarding.");
        }
        Card copy;
        if (type == Card.typeEnum.SKIP || type == Card.typeEnum.WILD) {
            copy = new Card(type);
        } else {
            copy = new Card(type, value);
        }
        addToPile(copy, pile);
        setCurrentTurn(false);
        hasDrawn = false;
    }

    // TODO: Remove this getHand() function and all usages in PlayerTest
    /** Gets the player's hand. */
    public Hand getHand() {
        return hand;
    }

    /** Returns a string representation of the player's hand. */
    public String getHandDescription() {
        return hand.toString();
    }

    /** Add CARD to player's hand. */
    public void addToHand(Card card) {
        hand.add(card);
    }

    /** Returns the phase the player is currently on. */
    public Phase getCurrentPhase() {
        return currentPhase;
    }

    /** Returns a summary of the player's phase. */
    public String getPhaseSummary() {
        String phaseInfo = "Phase %s: %s";
        if (completedPhase) {
            return String.format(phaseInfo, phaseNumber, "completed");
        } else {
            return String.format(phaseInfo, phaseNumber, "incomplete");
        }
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
