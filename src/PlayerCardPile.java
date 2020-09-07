import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class PlayerCardPile extends CardPile {
    enum typeEnum {
        NUM_SET,
        COLOR_SET,
        RUN,
        EVEN_ODD
    }
    static final HashMap<Card.typeEnum, Integer> colors = new HashMap<>() {
        {
            put(Card.typeEnum.BLUE, 0);
            put(Card.typeEnum.GREEN, 1);
            put(Card.typeEnum.RED, 2);
            put(Card.typeEnum.YELLOW, 3);
        }
    };
    final typeEnum type;
    int typeData = 0;

    public PlayerCardPile(typeEnum _type, LinkedList<Card> _cards) throws Exception {
        if(_cards.isEmpty()) {
            throw new Exception();
        }
        cards = _cards;
        size = _cards.size();
        type = _type;
        switch(_type) {
            case RUN:
                if(!isRun()) {
                    throw new Exception();
                }
                break;
            case NUM_SET:
                typeData = _cards.get(0).getValue();
                if(!isNumSet()) {
                    throw new Exception();
                }
                break;
            case EVEN_ODD:
                typeData = _cards.get(0).getValue() % 2;
                if(!isEvenOdd()) {
                    throw new Exception();
                }
                break;
            case COLOR_SET:
                typeData = colors.get(_cards.get(0).getType());
                if(!isColorSet()) {
                    throw new Exception();
                }
                break;
        }
    }

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
    private boolean isNumSet() {
        for(Card card : cards) {
            if(card.getValue() != typeData) {
                return false;
            }
        }
        return true;
    }
    private boolean isColorSet() {
        return false;
    }
    private boolean isEvenOdd() {
        for(Card card : cards) {
            if(card.getValue() % 2 != typeData) {
                return false;
            }
        }
        return true;
    }

}
