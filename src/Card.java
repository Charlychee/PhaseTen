public class Card implements Comparable<Card>{

    /** Compares the values of the cards*/
    @Override
    public int compareTo(Card o) {
        return Integer.compare(value, o.value);
    }

    /** Represents the type of card*/
    enum typeEnum {
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
    private final static int minValue = 1;
    /** The maximum value of a color card*/
    private final static int maxValue = 12;

    /**
     * Sets up card color and value. For color cards.
     * @param _type The color of the card
     * @param _value The value of the card
     */
    public Card(typeEnum _type, int _value) throws Exception {
        if (_type != typeEnum.WILD && _type != typeEnum.SKIP
                && _value <= maxValue && _value >= minValue) {
            type = _type;
            value = _value;
        }
        else throw new Exception("Invalid Card");
    }

    /**
     * Sets up card type and value. For WILD or SKIP cards only.
     * @param _type The type of the card
     * @throws Exception Card must be WILD or SKIP
     */
    public Card(typeEnum _type) throws Exception {
        if (_type == typeEnum.WILD || _type == typeEnum.SKIP) {
            type = _type;
            value = -1;
        }
        else throw new Exception("Invalid Card");
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

    /**
     * Prints the color and value
     * @return A string of color and value of the Card
     */
    public String toString()
    {
        if (type != typeEnum.WILD && type != typeEnum.SKIP)
            return "Color: " + type + ", Value: " + value;
        else
            return "Type: " + type;
    }

}
