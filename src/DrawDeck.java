import java.util.Collections;

public class DrawDeck extends CardPile{
    /** Creates the standard starting deck. */
    public DrawDeck() throws Exception {
        Card.typeEnum[] colors = new Card.typeEnum[]
                {
                        Card.typeEnum.BLUE,
                        Card.typeEnum.GREEN,
                        Card.typeEnum.RED,
                        Card.typeEnum.YELLOW
                };
        for (Card.typeEnum color : colors) {
            for (int k = 1; k <= 12; k += 1) {
                cards.add(new Card(color, k));
                cards.add(new Card(color, k));
            }
            cards.add(new Card(Card.typeEnum.SKIP));
            cards.add(new Card(Card.typeEnum.WILD));
            cards.add(new Card(Card.typeEnum.WILD));
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
