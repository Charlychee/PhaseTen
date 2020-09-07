import javax.swing.*;

public class TestGUI {
    /** A test GUI for PhaseTen games consisting of one human player and some number of CPU players. */

    /** The JFrame of the GUI. */
    private JFrame board;

    /** And array holding text fields for all CPU player information.*/
    private JTextField[] playerInfo;

    /** The game being run on this TestGUI. */
    private Game game;

    /** Creates a test GUI for this GAME. */
    TestGUI() {
        game = new Game(1, 0);
        board = new JFrame();
        board.add(new JTextField());
        board.setVisible(true);
    }
}
