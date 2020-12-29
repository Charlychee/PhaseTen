package com;

/** Represents a PhaseTen com.Card. */
public class Card implements Comparable<Card>{

    /** Represents the type of card*/
    public enum typeEnum {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        WILD,
        SKIP
    }

    /** Holds the type of card*/
    private final typeEnum type;

    /** Holds the value of the card*/
    private final int value;

    /** The minimum value of a color card*/
    private final static int MIN_VALUE = 1;
    /** The maximum value of a color card*/
    private final static int MAX_VALUE = 12;

    /**
     * Sets up card color and value. For color cards.
     * @param _type The color of the card
     * @param _value The value of the card
     */
    public Card(typeEnum _type, int _value) {
        if (_type != typeEnum.WILD && _type != typeEnum.SKIP
                && _value <= MAX_VALUE && _value >= MIN_VALUE) {
            type = _type;
            value = _value;
        }
        else throw new PTException("Invalid com.Card");
    }

    /**
     * Sets up card type and value. For WILD or SKIP cards only.
     * @param _type The type of the card
     */
    public Card(typeEnum _type) {
        if (_type == typeEnum.WILD || _type == typeEnum.SKIP) {
            type = _type;
            value = -1;
        }
        else throw new PTException("Invalid com.Card");
    }

    /** Checks if card O has the same type and value as this card. */
    public boolean equals(Card o) {
        return type == o.type && value == o.value;
    }

    /** Compares the values of the cards*/
    @Override
    public int compareTo(Card o) {
        return Integer.compare(value, o.value);
    }

    /**
     * Returns the type
     * @return The type of the card
     */
    public typeEnum getType() {
        return type;
    }

    /**
     * Returns the value
     * @return The value of the card
     */
    public int getValue() {
        return value;
    }

    // TODO: Replace instances of card.getType() == typeEnum.WILD with this method
    /** Returns whether the card is of type WILD. */
    public boolean isWild() {
        return type == typeEnum.WILD;
    }

    /**
     * Prints the color and value
     * @return A string of color and value of the com.Card
     */
    public String toString()
    {
        if (type != typeEnum.WILD && type != typeEnum.SKIP)
            return "Color: " + type + ", Value: " + value;
        else
            return "Type: " + type;
    }

}
