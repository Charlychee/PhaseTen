import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import com.Hand;
import com.Card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class HandTest {

    Hand theHand;

    Card blueThree = new Card(Card.typeEnum.BLUE, 3);
    Card blueTwo = new Card(Card.typeEnum.BLUE, 2);
    Card redOne = new Card(Card.typeEnum.RED, 1);
    Card redFive = new Card(Card.typeEnum.RED, 5);
    Card greenFour = new Card(Card.typeEnum.GREEN, 3);
    Card greenSix = new Card(Card.typeEnum.GREEN, 6);
    Card SKIP1 = new Card(Card.typeEnum.SKIP);
    Card SKIP2 = new Card(Card.typeEnum.SKIP);
    Card WILD = new Card(Card.typeEnum.WILD);
    Card WILD2 = new Card(Card.typeEnum.WILD);
    LinkedList<Card> cards = new LinkedList<>(Arrays.asList(blueThree, blueTwo, redOne, redFive, greenFour,
            greenSix, SKIP1, SKIP2, WILD, WILD2)
    );

    @Before
    public void setUp() {
        Collections.shuffle(cards);
        theHand = new Hand(cards);
    }

    @Test
    public void valueOrderTest() {
        theHand.sortBy(Hand.typeEnum.VALUE);
        Card.typeEnum theType = theHand.remove().getType();
        assertTrue(theType == Card.typeEnum.WILD || theType == Card.typeEnum.SKIP);
    }

    @Test
    public void colorOrderTest() {
        theHand.sortBy(Hand.typeEnum.COLOR);
        Card.typeEnum theType = theHand.remove().getType();
        assertTrue(theType == Card.typeEnum.RED || theType == Card.typeEnum.BLUE ||
                theType == Card.typeEnum.GREEN || theType == Card.typeEnum.YELLOW);
    }

    @Test
    public void evenOddOrderTest() {
        theHand.sortBy(Hand.typeEnum.EVENODD);
        int size = theHand.getSize();
        int expected = 0;
        int i;
        for(i = 0; i < size; ++i) {
            Card theCard = theHand.remove();
            int value = theCard.getValue() % 2;
            if (value != expected) {
                if(expected == 0) {
                    ++expected;
                }
                else if(expected == 1) {
                    expected = -1;
                }
                else if(expected == -1) {
                    break;
                }
            }
        }
        assertEquals(size, i);
    }

}
