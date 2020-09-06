import java.util.LinkedList;

public class CardPile {

    private LinkedList<Card> cards;
    private int size;

    public CardPile() {}
    public CardPile(LinkedList<Card> _cards) {
        cards = _cards;
        size = _cards.size();
    }

    public LinkedList<Card> getCards() {
        return new LinkedList<>(cards);
    }

    public int getSize() {
        return size;
    }

    public void add(Card card) {
        cards.push(card);
        ++size;
    }

    public void remove() {
        cards.pop();
        --size;
    }

    public void remove(Card card) {
        cards.remove(card);
        --size;
    }


}
