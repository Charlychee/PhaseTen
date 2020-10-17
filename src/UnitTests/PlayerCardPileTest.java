import com.PTException;
import com.PlayerCardPile;
import org.junit.BeforeClass;
import org.junit.Test;
import com.Card;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class PlayerCardPileTest {
    protected static PlayerCardPile testPCP;
    protected static Card wild = new Card(Card.typeEnum.WILD);
    protected static Card red1 = new Card(Card.typeEnum.RED, 1);
    protected static Card blue1 = new Card(Card.typeEnum.BLUE, 1);
    protected static Card green2 = new Card(Card.typeEnum.GREEN, 2);
    protected static Card blue3 = new Card(Card.typeEnum.BLUE, 3);
    protected static Card yellow4 = new Card(Card.typeEnum.YELLOW, 4);
    protected static Card yellow5 = new Card(Card.typeEnum.YELLOW, 5);
    protected static Card green11 = new Card(Card.typeEnum.GREEN, 11);
    protected static Card green12 = new Card(Card.typeEnum.GREEN, 12);
    protected static Card skip = new Card(Card.typeEnum.SKIP);
    protected static LinkedList<Card> skipList = new LinkedList<>();
    protected static LinkedList<Card> improperPlay = new LinkedList<>();
    protected static LinkedList<Card> allWilds = new LinkedList<>();
    protected static LinkedList<Card> allGreen12 = new LinkedList<>();
    protected static LinkedList<Card> wildsBlue3 = new LinkedList<>();
    protected static LinkedList<Card> run3 = new LinkedList<>();
    protected static LinkedList<Card> improperWildRun1 = new LinkedList<>();
    protected static LinkedList<Card> improperWildRun2 = new LinkedList<>();
    protected static LinkedList<Card> improperWildRun3 = new LinkedList<>();

    @BeforeClass
    public static void setUp() {
        skipList.push(skip);
        skipList.push(green11);

        run3.push(blue3);
        run3.push(green2);
        run3.push(red1);

        improperPlay.push(yellow4);
        improperPlay.push(blue1);
        improperPlay.push(red1);

        improperWildRun1.push(blue3);
        improperWildRun1.push(green2);
        improperWildRun1.push(red1);
        improperWildRun1.push(wild);

        improperWildRun2.push(wild);
        improperWildRun2.push(green12);
        improperWildRun2.push(green11);

        improperWildRun3.push(yellow5);
        improperWildRun3.push(wild);
        improperWildRun3.push(red1);

        allWilds.push(wild);
        allWilds.push(wild);
        allWilds.push(wild);
        allWilds.push(wild);

        wildsBlue3.push(wild);
        wildsBlue3.push(blue3);
        wildsBlue3.push(wild);
        wildsBlue3.push(wild);

        allGreen12.push(green12);
        allGreen12.push(green12);
        allGreen12.push(green12);
        allGreen12.push(green12);


    }
    @Test(expected = PTException.class)
    public void testEmptyPCP() {
        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, new LinkedList<>(), 4);
    }

    @Test(expected = PTException.class)
    public void testSmallPCP() {
        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, run3, 4);
    }

    @Test
    public void testRuns() {
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, improperPlay, 3);
        } catch (PTException e) {
            assertEquals("Given cards are not a run.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, improperWildRun1, 4);
        } catch (PTException e) {
            assertEquals("Given cards are not a run.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, improperWildRun2, 3);
        } catch (PTException e) {
            assertEquals("Given cards are not a run.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, improperWildRun3, 3);
        } catch (PTException e) {
            assertEquals("Given cards are not a run.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, skipList, 1);
        } catch (PTException e) {
            assertEquals("Given cards are not a run.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, allWilds, 3);
        } catch (PTException e) {
            assertEquals("Cannot create a run of all wild cards.", e.getMessage());
        }

        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, run3, 3);
        assertEquals("PlayerCardPile has an incorrect size.", 3, testPCP.getSize());
        assertEquals("PlayerCardPile has the incorrect card set.", run3, testPCP.getCards());


        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, wildsBlue3, 2);
        assertEquals("PlayerCardPile has an incorrect size.", 4, testPCP.getSize());
        assertEquals("PlayerCardPile has the incorrect card set.", wildsBlue3, testPCP.getCards());
    }

    @Test
    public void testNumSets() {
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.NUM_SET, skipList,2);
        } catch (PTException e) {
            assertEquals("Given cards are not a number set.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.NUM_SET, improperPlay,3);
        } catch (PTException e) {
            assertEquals("Given cards are not a number set.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.NUM_SET, allWilds,3);
        } catch (PTException e) {
            assertEquals("Number set cannot contain all wilds or skips.", e.getMessage());
        }

        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.NUM_SET, wildsBlue3, 4);
        assertEquals("PlayerCardPile has an incorrect size.", 4, testPCP.getSize());
        assertEquals("PlayerCardPile has an incorrect card set.", wildsBlue3, testPCP.getCards());
        assertEquals("PlayerCardPile has the incorrect type data.", 3, testPCP.getTypeData());

        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.NUM_SET, allGreen12, 2);
        assertEquals("PlayerCardPile has an incorrect size.", 4, testPCP.getSize());
        assertEquals("PlayerCardPile has an incorrect card set.", allGreen12, testPCP.getCards());
        assertEquals("PlayerCardPile has the incorrect type data.", 12, testPCP.getTypeData());
    }

    @Test
    public void testColorSets() {
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.COLOR_SET, skipList,2);
        } catch (PTException e) {
            assertEquals("Given cards are not a color set.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.COLOR_SET, improperPlay,3);
        } catch (PTException e) {
            assertEquals("Given cards are not a color set.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.COLOR_SET, allWilds,3);
        } catch (PTException e) {
            assertEquals("Color set cannot contain all wilds or skips.", e.getMessage());
        }

        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.COLOR_SET, allGreen12, 2);
        assertEquals("PlayerCardPile has an incorrect size.", 4, testPCP.getSize());
        assertEquals("PlayerCardPile has an incorrect card set.", allGreen12, testPCP.getCards());
        assertEquals("PlayerCardPile has the incorrect type data.", 1, testPCP.getTypeData());

        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.COLOR_SET, wildsBlue3, 4);
        assertEquals("PlayerCardPile has an incorrect size.", 4, testPCP.getSize());
        assertEquals("PlayerCardPile has an incorrect card set.", wildsBlue3, testPCP.getCards());
        assertEquals("PlayerCardPile has the incorrect type data.", 0, testPCP.getTypeData());

        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.COLOR_SET, improperWildRun2, 3);
        assertEquals("PlayerCardPile has an incorrect size.", 3, testPCP.getSize());
        assertEquals("PlayerCardPile has an incorrect card set.", improperWildRun2, testPCP.getCards());
        assertEquals("PlayerCardPile has the incorrect type data.", 1, testPCP.getTypeData());
    }

    @Test
    public void testEvenOddSets() {
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.EVEN_ODD, skipList,2);
        } catch (PTException e) {
            assertEquals("Given cards are not an even/odd.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.EVEN_ODD, improperPlay,3);
        } catch (PTException e) {
            assertEquals("Given cards are not an even/odd.", e.getMessage());
        }
        try {
            testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.EVEN_ODD, allWilds,3);
        } catch (PTException e) {
            assertEquals("Even/odd set cannot contain all wilds or skips.", e.getMessage());
        }

        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.EVEN_ODD, allGreen12, 2);
        assertEquals("PlayerCardPile has an incorrect size.", 4, testPCP.getSize());
        assertEquals("PlayerCardPile has an incorrect card set.", allGreen12, testPCP.getCards());
        assertEquals("PlayerCardPile has the incorrect type data.", 0, testPCP.getTypeData());

        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.EVEN_ODD, wildsBlue3, 4);
        assertEquals("PlayerCardPile has an incorrect size.", 4, testPCP.getSize());
        assertEquals("PlayerCardPile has an incorrect card set.", wildsBlue3, testPCP.getCards());
        assertEquals("PlayerCardPile has the incorrect type data.", 1, testPCP.getTypeData());

        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.EVEN_ODD, improperWildRun3, 3);
        assertEquals("PlayerCardPile has an incorrect size.", 3, testPCP.getSize());
        assertEquals("PlayerCardPile has an incorrect card set.", improperWildRun3, testPCP.getCards());
        assertEquals("PlayerCardPile has the incorrect type data.", 1, testPCP.getTypeData());
    }
}
