package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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

    /** Formed numSets. */
    protected List<PlayerCardPile> numSets;

    /** Formed runs. */
    protected List<PlayerCardPile> runs;

    /** Formed even/odd sets. */
    protected List<PlayerCardPile> evenOdds;

    /** Formed color sets. */
    protected List<PlayerCardPile> colorSets;

    public Player() {
        hand = new Hand();
        phaseNumber = 1;
        currentPhase = Phase.ORIGINAL_PHASES[phaseNumber];
        id = numPlayers;
        numPlayers += 1;
        numSets = new LinkedList<>();
        runs = new LinkedList<>();
        evenOdds = new LinkedList<>();
        colorSets = new LinkedList<>();
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

    /** Adds the PlayerCardPile to the appropriate list of formed PlayerCardPiles. */
    public void addToList(PlayerCardPile.typeEnum type, PlayerCardPile pile) {
        switch (type) {
            case NUM_SET:
                numSets.add(pile);
                break;
            case RUN:
                runs.add(pile);
                break;
            case EVEN_ODD:
                evenOdds.add(pile);
                break;
            case COLOR_SET:
                colorSets.add(pile);
                break;
            default:
                throw new PTException("A problem occurred while trying to store the PlayerCardPile.");
        }
    }


    /** Creates any required PlayerCardPiles of TYPE out of CARDS in this phase. Does not remove cards from Player's hand.  */
    public void createPlayerCardPile(PlayerCardPile.typeEnum type, LinkedList<Card> cards) {
        // TODO: Add a check to ensure all the cards provided exist in player's hand.
        ArrayList<Integer> needed = currentPhase.get(type);
        int cardsSize = cards.size();

        // Checks that the PlayerCardPile satisfies at least one of the requirements of the phase.
        for (int i : needed) {
            if (cardsSize >= i) {
                addToList(type, new PlayerCardPile(type, cards, i));
                return;
            }
        }
        throw new PTException("Given cards does not satisfy any PlayerCardPiles needed in this phase.");
    }

    /** Clears all the created PlayerCardPiles. */
    private void clearPlayerCardPiles() {
        numSets.clear();
        runs.clear();
        evenOdds.clear();
        colorSets.clear();
    }

    /** Attempts to create the current phase out of the given PlayerCardPiles. If successful, removes used cards from
     *  the player's hand. If unsuccessful, removes all PlayerCardPiles the player attempted to make. */
    public void createCurrentPhase() {
        if (completedPhase) {
            throw new PTException("Unable to create the current phase because it was already completed.");
        }
        ArrayList<Integer> neededNumSets = currentPhase.get(PlayerCardPile.typeEnum.NUM_SET);
        ArrayList<Integer> neededRuns = currentPhase.get(PlayerCardPile.typeEnum.RUN);
        ArrayList<Integer> neededEvenOdds = currentPhase.get(PlayerCardPile.typeEnum.EVEN_ODD);
        ArrayList<Integer> neededColorSets = currentPhase.get(PlayerCardPile.typeEnum.COLOR_SET);
        if (numSets.size() != neededNumSets.size() || runs.size() != neededRuns.size() || evenOdds.size() !=
                neededEvenOdds.size() || colorSets.size() != neededColorSets.size()) {
            throw new PTException("Incorrect number of formed PlayerCardPiles to complete this phase.");
        }

        // Checks the created numSets
        numSets.sort(PlayerCardPile.ORDER);
        for (int i = 0; i < neededNumSets.size(); i += 1) {
            if (neededNumSets.get(i) > numSets.get(i).getSize()) {
                clearPlayerCardPiles();
                throw new PTException("Phase could not be completed.");
            }
        }
        // Checks the created runs
        runs.sort(PlayerCardPile.ORDER);
        for (int i = 0; i < neededRuns.size(); i += 1) {
            if (neededRuns.get(i) > runs.get(i).getSize()) {
                clearPlayerCardPiles();
                throw new PTException("Phase could not be completed.");
            }
        }
        // Checks the created evenOdds
        evenOdds.sort(PlayerCardPile.ORDER);
        for (int i = 0; i < neededEvenOdds.size(); i += 1) {
            if (neededEvenOdds.get(i) > evenOdds.get(i).getSize()) {
                clearPlayerCardPiles();
                throw new PTException("Phase could not be completed.");
            }
        }
        // Checks the created colorSets
        colorSets.sort(PlayerCardPile.ORDER);
        for (int i = 0; i < neededColorSets.size(); i += 1) {
            if (neededColorSets.get(i) > colorSets.get(i).getSize()) {
                clearPlayerCardPiles();
                throw new PTException("Phase could not be completed.");
            }
        }
        /* Getting to this point means that all player card piles successfully created the phase. Will now remove all
           cards used to make the piles from the hand. */
        // TODO: Is this the best way to remove from Hand? Will we need to override Hand's remove method?
        // TODO: Create a removeAll method? Would make this portion shorter
        // TODO: Need to make sure the same card isn't used in two different PlayerCardPile
        for (PlayerCardPile pile: numSets) {
            for (Card c : pile.getCards()) {
                hand.remove(c);
            }
        }
        for (PlayerCardPile pile: runs) {
            for (Card c : pile.getCards()) {
                hand.remove(c);
            }
        }
        for (PlayerCardPile pile: evenOdds) {
            for (Card c : pile.getCards()) {
                hand.remove(c);
            }
        }
        for (PlayerCardPile pile: colorSets) {
            for (Card c : pile.getCards()) {
                hand.remove(c);
            }
        }
        completedPhase = true;
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
