import java.util.HashMap;
import java.util.LinkedList;

public class PlayerCardPile extends CardPile {
    enum typeEnum {
        NUM_SET,
        COLOR_SET,
        RUN,
        EVEN_ODD
    }
    static final HashMap<Integer, String> colors = new HashMap<>() {
        {
            put(0, "Blue");
            put(1, "Green");
            put(2, "Red");
            put(3, "Yellow");
        }
    };
    final typeEnum type;
    int typeData;

    public PlayerCardPile(typeEnum _type, LinkedList<Card> _cards) throws Exception {
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
                if(!isNumSet()) {
                    throw new Exception();
                }
                break;
            case EVEN_ODD:
                if(!isEven() || !isOdd()) {
                    throw new Exception();
                }
                break;
            case COLOR_SET:
                if(!isColorSet()) {
                    throw new Exception();
                }
                break;
        }

    }

    private boolean isRun(){
        return true;
    }
    private boolean isNumSet() {
        return true;
    }
    private boolean isColorSet() {
        return true;
    }
    private boolean isEven() {
        return true;
    }
    private boolean isOdd() {
        return true;
    }

}
