import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TestGUI {
    /** A test GUI for PhaseTen games consisting of one human player and some number of CPU players. */
    private JButton drawFromDeckButton;
    public JPanel panel1;
    private JButton card1;
    private JButton card2;
    private JButton card3;
    private JButton card4;
    private JButton card5;
    private JButton card6;
    private JButton card7;
    private JButton card8;
    private JButton card9;
    private JButton card10;
    private JButton button1;

    /** ArrayList of buttons for cards in the hand. */
    private List<JButton> handButtons;

    /** Test player. */
    private Player player;

    /** Player's hand. */
    private Hand hand;

    /** The game being run on this TestGUI. */
    private Game game;

    /** Creates a test GUI for this GAME. */
    TestGUI() {
        game = new Game(1, 0);
        player = game.getPlayers()[0];
        handButtons = new ArrayList<>();
        handButtons.add(card1);
        handButtons.add(card2);
        handButtons.add(card3);
        handButtons.add(card4);
        handButtons.add(card5);
        handButtons.add(card6);
        handButtons.add(card7);
        handButtons.add(card8);
        handButtons.add(card9);
        handButtons.add(card10);
        game.startGame();
        setUpPlayerHand();
    }

    public void setUpPlayerHand() {
        hand = player.getHand();
        List<Card> handCards = hand.getCards();
        for (int i = 0; i < hand.getSize(); i += 1) {
            handButtons.get(i).setText(handCards.get(i).toString());
        }
    }
}
