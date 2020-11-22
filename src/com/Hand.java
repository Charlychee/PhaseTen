package com;

import java.util.Comparator;
import java.util.LinkedList;

public class Hand extends CardPile{

    public enum typeEnum {
        VALUE,
        COLOR,
        EVENODD
    }

    // TODO: I HAVE NO CLUE IF I'M DOING THIS CORRECTLY
    /** Compare by value*/
    private final Comparator<Card> valueOrder = Comparator.naturalOrder();

    /** Compare by color*/
    private final Comparator<Card> colorOrder = Comparator.comparing(Card::getType);

    /** Compare by even/odd*/
    private final Comparator<Card> evenOddOrder = (c1, c2) -> {
        int valueOne = c1.getValue();
        int valueTwo = c2.getValue();
        if (valueOne%2 == valueTwo%2) {
            // Both numbers are odd or both numbers are even
            if (valueOne < valueTwo) {
                return -1;
            }
            else {
                return 1;
            }
        }
        // One is odd, the other one is even
        if (valueOne%2 == 0) {
            return -1;
        }
        if (valueOne == -1) {
            //Move WILD and SKIP to back
            return 1;
        }
        if (valueTwo == -1) {
            //Move WILD and SKIP to back after odds
            return -1;
        }
        if (valueOne == valueTwo) {
            return 0;
        }
        return 1;
    };

    public Hand() {
        super();
    }

    public Hand(LinkedList<Card> _cards) {
        super(_cards);
    }

    /**
     * Sorts the cards field by a certain type
     * @param type The type to sort cards by
     */
    public void sortBy(typeEnum type) {
        switch(type) {
            case VALUE:
                cards.sort(valueOrder);
                break;
            case COLOR:
                cards.sort(colorOrder);
                break;
            case EVENODD:
                cards.sort(evenOddOrder);
                break;
        }
    }
}
