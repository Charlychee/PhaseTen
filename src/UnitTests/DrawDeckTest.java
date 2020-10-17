import static org.junit.Assert.*;

import com.Card;
import com.DrawDeck;
import com.PTException;
import org.junit.Test;

import java.util.LinkedList;

public class DrawDeckTest {

    @Test
    public void testCtor() {
        DrawDeck aDeck = new DrawDeck();
        assertEquals(108, aDeck.getSize());
    }

    @Test
    public void testShuffle() {
        DrawDeck aDeck = new DrawDeck();
        LinkedList<Card> cardsList = aDeck.getCards();
        aDeck.shuffle();
        boolean shuffled = false;
        for(int i = 0; i < aDeck.getSize(); ++i) {
            if(!cardsList.pop().equals(aDeck.remove())) {
                shuffled = true;
                break;
            }
        }
        assertTrue(shuffled);
    }

    //TODO: TEST DEAL

    @Test(expected = PTException.class)
    public void testAdd() {
        DrawDeck aDeck = new DrawDeck();
        Card aCard = new Card(Card.typeEnum.BLUE, 1);
        aDeck.add(aCard);
    }

    @Test(expected = PTException.class)
    public void testRemove() {
        DrawDeck aDeck = new DrawDeck();
        Card aCard = new Card(Card.typeEnum.BLUE, 1);
        aDeck.remove(aCard);
    }

}
