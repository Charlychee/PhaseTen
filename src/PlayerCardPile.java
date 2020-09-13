import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
/** Represents a card play. */
public class PlayerCardPile extends CardPile {
    // TODO: needs to consider the necessary size of the play in the checks.
    /** Represents the type of card play. */
    enum typeEnum {
        NUM_SET,
        COLOR_SET,
        RUN,
        EVEN_ODD
    }

    /** Maps colors to integers. */
    static final HashMap<Card.typeEnum, Integer> COLORS = new HashMap<>() {
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

    /** Creates a PlayerCardPile of type _TYPE with the specified _CARDS. */
    public PlayerCardPile(typeEnum _type, LinkedList<Card> _cards) {
        if(_cards.isEmpty()) {
            throw new PTException("The given card list is empty.");
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
                typeData = _cards.get(0).getValue();
                if(!isNumSet()) {
                    throw new PTException("Given cards are not a number set.");
                }
                break;
            case EVEN_ODD:
                typeData = _cards.get(0).getValue() % 2;
                if(!isEvenOdd()) {
                    throw new PTException("Given cards are not an even/odd.");
                }
                break;
            case COLOR_SET:
                typeData = COLORS.get(_cards.get(0).getType());
                if(!isColorSet()) {
                    throw new PTException("Given cards are not a color set.");
                }
                break;
        }
    }

    /** Checks if the PlayerCardPile is a run. */
    private boolean isRun(){
        Collections.sort(cards);
        int expected = cards.get(0).getValue();
        for(Card card : cards) {
            if(card.getValue() != expected) {
                return false;
            }
            ++expected;
        }
        return true;
    }

    /** Checks if the PlayerCardPile is a number set. */
    private boolean isNumSet() {
        for(Card card : cards) {
            if(card.getValue() != typeData) {
                return false;
            }
        }
        return true;
    }

    /** Checks if the PlayerCardPile is a color set. */
    private boolean isColorSet() {
        return false;
    }

    /** Checks if the PlayerCardPile is a even/odd set. */
    private boolean isEvenOdd() {
        for(Card card : cards) {
            if(card.getValue() % 2 != typeData) {
                return false;
            }
        }
        return true;
    }

}
