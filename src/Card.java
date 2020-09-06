public class Card {
    enum typeEnum {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        WILD,
        SKIP
    }

    private final typeEnum type;
    private final int value;

    public Card(typeEnum _color, int _value) {
        type = _color;
        value = _value;
    }

    public typeEnum getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public String toString()
    {
        return "Color: " + type + ", Value: " + value;
    }

}
