import com.PTException;
import org.junit.Test;
import com.Card;
import static org.junit.Assert.*;

public class CardTest {

    static Card redCard1 = new Card(Card.typeEnum.RED, 1);
    static Card redCard2 = new Card(Card.typeEnum.RED, 2);
    static Card blueCard12 = new Card(Card.typeEnum.BLUE, 12);
    static Card blueCardCopy = new Card(Card.typeEnum.BLUE, 12);
    static Card yellowCard2 = new Card(Card.typeEnum.YELLOW, 2);
    static Card greenCard11 = new Card(Card.typeEnum.GREEN, 11);
    static Card wildCard = new Card(Card.typeEnum.WILD);
    static Card skipCard = new Card(Card.typeEnum.SKIP);
    /** Tests creating WILD card with Card color ctor */
    @Test(expected = PTException.class)
    public void cardColorCtorWild() {
        Card aCard = new Card(Card.typeEnum.WILD, 0);
    }

    /** Tests creating SKIP card with Card color ctor */
    @Test(expected = PTException.class)
    public void cardColorCtorSkip() {
        Card aCard = new Card(Card.typeEnum.SKIP, 0);
    }

    /** Tests creating color card under min with Card color ctor */
    @Test(expected = PTException.class)
    public void cardColorCtorMin() {
        Card aCard = new Card(Card.typeEnum.RED, 0);
    }

    /** Tests creating color card over max with Card color ctor */
    @Test(expected = PTException.class)
    public void cardColorCtorMax() {
        Card aCard = new Card(Card.typeEnum.RED, 13);
    }

    @Test
    public void testProperCardCtor() {
        assertEquals("Card had an unexpected card type.", Card.typeEnum.RED, redCard1.getType());
        assertEquals("Card had an unexpected value.", 1, redCard1.getValue());

        assertEquals("Card had an unexpected card type.", Card.typeEnum.BLUE, blueCard12.getType());
        assertEquals("Card had an unexpected value.", 12, blueCard12.getValue());

        assertEquals("Card had an unexpected card type.", Card.typeEnum.YELLOW, yellowCard2.getType());
        assertEquals("Card had an unexpected value.", 2, yellowCard2.getValue());

        assertEquals("Card had an unexpected card type.", Card.typeEnum.GREEN, greenCard11.getType());
        assertEquals("Card had an unexpected value.", 11, greenCard11.getValue());

        assertEquals("Card had an unexpected card type.", Card.typeEnum.WILD, wildCard.getType());
        assertEquals("Card had an unexpected value.", -1, wildCard.getValue());

        assertEquals("Card had an unexpected card type.", Card.typeEnum.SKIP, skipCard.getType());
        assertEquals("Card had an unexpected value.", -1, skipCard.getValue());
    }

    @Test
    public void testCompareTo() {
        assertTrue(redCard1.compareTo(blueCard12) < 0);
        assertTrue(greenCard11.compareTo(yellowCard2) > 0);
        assertTrue(wildCard.compareTo(skipCard) == 0);
        assertTrue(blueCard12.compareTo(wildCard) > 0);
        assertTrue(skipCard.compareTo(yellowCard2) < 0);
        assertTrue(redCard2.compareTo(yellowCard2) == 0);
    }

    @Test
    public void testEquals() {
        assertFalse(redCard1.equals(redCard2));
        assertFalse(yellowCard2.equals(redCard2));
        assertFalse(wildCard.equals(skipCard));
        assertTrue(blueCard12.equals(blueCardCopy));
    }

}
