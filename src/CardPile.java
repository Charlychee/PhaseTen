import java.util.LinkedList;

/** Represents a collection of cards. Class that's extended by DrawDeck,
 *  DiscardPile, Hand, and PlayerCardPile.
 */
public class CardPile {

    /** Holds all the cards in the pile. */
    protected LinkedList<Card> cards;

    /** The size of the card pile. */
    protected int size;

    /** Creates an empty CardPile. */
    public CardPile() {
        cards = new LinkedList<>();
    }

    /** Creates a CardPile with _CARDS. */
    public CardPile(LinkedList<Card> _cards) {
        cards = _cards;
        size = _cards.size();
    }

    /** Adds a Card to the top of the CardPIle and updates size. */
    public void add(Card card) {
        cards.push(card);
        ++size;
    }

    /** Removes a Card from the top of the CardPile and updates size. */
    public Card remove() {
        --size;
        return cards.pop();
    }

    /** Removes specified Card from the CardPile and updates size. */
    public void remove(Card card) {
        cards.remove(card);
        --size;
    }

    /** Returns a copy of the cards in the CardPile. */
    public LinkedList<Card> getCards() {
        return new LinkedList<>(cards);
    }

    /** Returns the size of the CardPile. */
    public int getSize() {
        return size;
    }

    /** Prints out the cards in this card pile. */
    public String toString() {
        String info = "";
        for (Card c : cards) {
            info += c.toString() + "\n";
        }
        return info;
    }


}
