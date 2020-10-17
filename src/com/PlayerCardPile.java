package com;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
/** Represents a card play. */
public class PlayerCardPile extends CardPile {
    // TODO: needs to consider the necessary size of the play in the checks.
    // TODO: need to consider wild cards
    // TODO: Integrate constructor with Phases
    /** Represents the type of card play. */
    public enum typeEnum {
        NUM_SET,
        COLOR_SET,
        RUN,
        EVEN_ODD
    }

    /** Maps colors to integers. */
    static final MyHashMap<Card.typeEnum, Integer> COLORS = new MyHashMap<>() {
        {
            put(Card.typeEnum.BLUE, 0);
            put(Card.typeEnum.GREEN, 1);
            put(Card.typeEnum.RED, 2);
            put(Card.typeEnum.YELLOW, 3);
        }
    };

    /** The type of card play. */
    final typeEnum type;

    /** The card type data. */
    int typeData = 0;


    /** Creates a com.PlayerCardPile of type _TYPE with the specified _CARDS with a length of at least EXPECTEDSIZE. */
    public PlayerCardPile(typeEnum _type, LinkedList<Card> _cards, int expectedSize) {
        if(_cards.isEmpty()) {
            throw new PTException("The given card list is empty.");
        }
        if (_cards.size() < expectedSize) {
            throw new PTException("The size of the given card list is less than the necessary size.");
        }
        cards = _cards;
        size = _cards.size();
        type = _type;
        switch(_type) {
            case RUN:
                if(!isRun()) {
                    throw new PTException("Given cards are not a run.");
                }
                break;
            case NUM_SET:
                Collections.sort(_cards);
                typeData = _cards.get(size - 1).getValue();
                if(!isNumSet()) {
                    throw new PTException("Given cards are not a number set.");
                }
                break;
            case EVEN_ODD:
                Collections.sort(_cards);
                if (_cards.get(size - 1).getValue() == -1) {
                    throw new PTException("Even/odd set cannot contain all wilds or skips.");
                }
                typeData = _cards.get(size - 1).getValue() % 2;
                if(!isEvenOdd()) {
                    throw new PTException("Given cards are not an even/odd.");
                }
                break;
            case COLOR_SET:
                Collections.sort(_cards);
                if (_cards.get(size - 1).getValue() == -1) {
                    throw new PTException("Color set cannot contain all wilds or skips.");
                }
                typeData = COLORS.get(_cards.get(size - 1).getType());
                if(!isColorSet()) {
                    throw new PTException("Given cards are not a color set.");
                }
                break;
        }
    }

    /** Checks if the com.PlayerCardPile is a run. Assumes that the card pile is already sorted. */
    private boolean isRun(){
        int leadingWilds = 0;
        // Finds how many wilds are at the start of the run.
        for (int i = 0; i < size; i += 1) {
            if (cards.get(i).getType() == Card.typeEnum.WILD) {
                leadingWilds += 1;
            } else {
                break;
            }
        }
        if (leadingWilds == size) {
            throw new PTException("Cannot create a run of all wild cards.");
        }
        int expected = cards.get(leadingWilds).getValue();
        // Doesn't allow for wilds before the number 1.
        if (expected - leadingWilds <= 0) {
            return false;
        }
        // Checks that the run is in the right order + there are no wilds after 12.
        for (int i = leadingWilds; i < size; i += 1) {
            if(expected > 12 || (cards.get(i).getValue() != expected
                    && cards.get(i).getType() != Card.typeEnum.WILD)) {
                return false;
            }
            ++expected;
        }
        return true;
    }

    /** Checks if the com.PlayerCardPile is a number set. */
    private boolean isNumSet() {
        if (typeData == -1) {
            throw new PTException("Number set cannot contain all wilds or skips.");
        }
        for(Card card : cards) {
            if(card.getValue() != typeData && card.getType() != Card.typeEnum.WILD) {
                return false;
            }
        }
        return true;
    }

    /** Checks if the com.PlayerCardPile is a color set. */
    private boolean isColorSet() {
        Card.typeEnum needed = COLORS.getKey(typeData);
        for (Card card : cards) {
            if (card.getType() != needed && card.getType() != Card.typeEnum.WILD) {
                return false;
            }
        }
        return true;
    }

    /** Checks if the com.PlayerCardPile is a even/odd set. */
    private boolean isEvenOdd() {
        for(Card card : cards) {
            if(card.getValue() % 2 != typeData && card.getType() != Card.typeEnum.WILD) {
                return false;
            }
        }
        return true;
    }

    /** Returns the typeData of this PlayerCardPile. */
    public int getTypeData() {
        return typeData;
    }
}
