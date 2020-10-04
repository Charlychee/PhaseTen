import com.PTException;
import com.PlayerCardPile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.Card;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class PlayerCardPileTest {
    protected PlayerCardPile testPCP;
    protected Card red1 = new Card(Card.typeEnum.RED, 1);
    protected Card green2 = new Card(Card.typeEnum.GREEN, 2);
    protected Card blue3 = new Card(Card.typeEnum.BLUE, 3);
    protected LinkedList<Card> run3 = new LinkedList<>();
    protected LinkedList<Card> improperRun1 = new LinkedList<>();

    @BeforeClass
    public void setUp() {
        run3.push(blue3);
        run3.push(green2);
        run3.push(red1);
    }
    @Test(expected = PTException.class)
    public void testEmptyPCP() {
        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, new LinkedList<>(), 4);
    }

    @Test(expected = PTException.class)
    public void testSmallPCP() {
        testPCP = new PlayerCardPile(PlayerCardPile.typeEnum.RUN, run3, 4);
    }
}
