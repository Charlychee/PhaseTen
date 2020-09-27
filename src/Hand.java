import java.util.Collections;
import java.util.Comparator;

public class Hand extends CardPile{

    enum typeEnum {
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
        if (c1.getValue()%2 == c2.getValue()%2) {
            // Both numbers are odd or both numbers are even
            return 0;
        }
        // One is odd, the other one is even
        if (c1.getValue()%2 == 0) {
            return -1;
        }
        return 1;
    };

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
