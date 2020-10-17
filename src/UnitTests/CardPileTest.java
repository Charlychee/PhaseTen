import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;
import static org.junit.Assert.*;

import com.CardPile;
import com.Card;
import com.PTException;

public class CardPileTest {

    protected LinkedList<Card> cards;

    @Before
    public void setUp() {
        cards = new LinkedList<>();
        cards.push(new Card(Card.typeEnum.RED,1));
        cards.push(new Card(Card.typeEnum.RED,2));
        cards.push(new Card(Card.typeEnum.RED,3));
    }

    @Test
    public void testDefCtor() {
        CardPile aPile = new CardPile();
        assertTrue("Default Ctor doesn't create empty card pile.", aPile.getCards().isEmpty());
        assertEquals(aPile.getSize(), 0);
    }

    @Test
    public void testCtor() {
        CardPile aPile = new CardPile(cards);
        Object[] cardArray = cards.toArray();
        Object[] pileCardArray = aPile.getCards().toArray();
        assertArrayEquals(cardArray, pileCardArray);
        assertEquals(aPile.getSize(), 3);
    }

    @Test
    public void testAdd() {
        CardPile aPile = new CardPile(cards);
        aPile.add(new Card(Card.typeEnum.RED, 4));
        cards.push(new Card(Card.typeEnum.RED,4));

        Object[] cardArray = cards.toArray();
        Object[] pileCardArray = aPile.getCards().toArray();

        assertArrayEquals(cardArray, pileCardArray);
        assertEquals(aPile.getSize(), 4);
    }

    @Test
    public void testRemove() {
        CardPile aPile = new CardPile(cards);
        assertTrue(aPile.remove().equals(new Card(Card.typeEnum.RED, 3)));
    }

    @Test
    public void testRemoveCard() {
        CardPile aPile = new CardPile(cards);
        aPile.remove(new Card(Card.typeEnum.RED, 1));
        assertEquals(aPile.getSize(), 2);
        assertEquals(aPile.getSize(), aPile.getCards().size());
    }

    @Test(expected = PTException.class)
    public void testRemoveNoExistCard() {
        CardPile aPile = new CardPile(cards);
        aPile.remove(new Card(Card.typeEnum.BLUE, 1));
    }

}
