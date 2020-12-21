import com.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testCtor() {
        Player player = new HumanPlayer();
        assertEquals(0, player.getHand().getSize());
        assertFalse(player.getCompletedPhase());
        assertNull(null, player.getCardPiles());
    }

    @Test
    public void testDraw() {
        // Drawing from DrawDeck
        DrawDeck deck = new DrawDeck();
        Player player = new HumanPlayer();
        player.setCurrentTurn(true);
        player.draw(deck);
        assertTrue(player.getHand().getCards().pop().equals(new Card(Card.typeEnum.BLUE,1)));

        // Drawing from DiscardPile
        DiscardPile discard = new DiscardPile();
        discard.add(new Card(Card.typeEnum.RED,1));
        player = new HumanPlayer();
        player.setCurrentTurn(true);
        player.draw(discard);
        assertTrue(player.getHand().getCards().pop().equals(new Card(Card.typeEnum.RED,1)));
    }

    @Test(expected = PTException.class)
    public void testDrawException() {
        CardPile pile = new CardPile();
        pile.add(new Card(Card.typeEnum.BLUE,1));
        Player player = new HumanPlayer();
        player.draw(pile);
    }

    @Test
    public void testAddToPile() {
        Card testCard = new Card(Card.typeEnum.BLUE, 1);
        CardPile pile = new CardPile();
        Player player = new HumanPlayer();
        player.getHand().add(testCard);

        assertEquals(0,pile.getSize());
        player.addToPile(testCard, pile);
        assertTrue(pile.remove().equals(testCard));


    }
}
