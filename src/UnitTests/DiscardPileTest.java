import com.Card;
import com.DiscardPile;
import com.PTException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiscardPileTest {

    @Test(expected = PTException.class)
    public void testRemoveCard() {
        DiscardPile aDiscardPile = new DiscardPile();
        aDiscardPile.remove(new Card(Card.typeEnum.BLUE, 1));
    }

    @Test(expected = PTException.class)
    public void testRemoveSkip() {
        DiscardPile aDiscardPile = new DiscardPile();
        aDiscardPile.add(new Card(Card.typeEnum.SKIP));
        aDiscardPile.remove(new Card(Card.typeEnum.SKIP));
    }

    @Test
    public void testRemove() {
        DiscardPile aDiscardPile = new DiscardPile();
        Card blueCard = new Card(Card.typeEnum.BLUE, 1);
        aDiscardPile.add(blueCard);
        assertEquals(aDiscardPile.remove(), blueCard);
    }
}
