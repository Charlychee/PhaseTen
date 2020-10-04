import com.PTException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.CardPile;
import com.Card;

import java.util.LinkedList;

import static org.junit.Assert.*;

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
        assertTrue(aPile.getCards().isEmpty());
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
        //TODO: Fix removing equivalent Card
        CardPile aPile = new CardPile(cards);
        aPile.remove(new Card(Card.typeEnum.RED, 1));
        assertEquals(aPile.getSize(), 2);
    }

    @Test(expected = PTException.class)
    public void testRemoveNoExistCard() {
        CardPile aPile = new CardPile(cards);
        aPile.remove(new Card(Card.typeEnum.BLUE, 1));
    }

}
