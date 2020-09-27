import com.PTException;
import org.junit.Test;
import com.Card;

public class CardTest {

    /** Tests creating WILD card with Card color ctor */
    @Test(expected = PTException.class)
    public void CardColorCtorWild() {
        Card aCard = new Card(Card.typeEnum.WILD, 0);
    }

    /** Tests creating SKIP card with Card color ctor */
    @Test(expected = PTException.class)
    public void CardColorCtorSkip() {
        Card aCard = new Card(Card.typeEnum.SKIP, 0);
    }

    /** Tests creating color card under min with Card color ctor */
    @Test(expected = PTException.class)
    public void CardColorCtorMin() {
        Card aCard = new Card(Card.typeEnum.RED, 0);
    }

    /** Tests creating color card over max with Card color ctor */
    @Test(expected = PTException.class)
    public void CardColorCtorMax() {
        Card aCard = new Card(Card.typeEnum.RED, 13);
    }

}
