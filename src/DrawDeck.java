import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DrawDeck extends CardPile{
    /** Creates the standard starting deck. */
    public DrawDeck() {
        Card.typeEnum[] colors = new Card.typeEnum[]
                {
                        Card.typeEnum.BLUE,
                        Card.typeEnum.GREEN,
                        Card.typeEnum.RED,
                        Card.typeEnum.YELLOW
                };
        for (int i = 0; i < colors.length; i += 1) {
            for (int k = 1; k <= 12; k += 1) {
                cards.add(new Card(colors[i], k));
                cards.add(new Card(colors[i], k));
            }
            cards.add(new Card(Card.typeEnum.SKIP, 0));
            cards.add(new Card(Card.typeEnum.WILD, 0));
            cards.add(new Card(Card.typeEnum.WILD, 0));
        }
    }

    /** Shuffles the cards in the deck. */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /** Deals NUMCARDS cards to PLAYERS. */
    public void deal(int numCards, Player[] players) {
        for (Player p : players) {
            for (int i = 0; i < 10; i += 1) {
                p.draw(this);
            }
        }
    }
}
