public abstract class Player {
    private Hand hand;

    public Player() {
        hand = new Hand();
    }
    /** Draws a card from PILE. */
    public void draw(CardPile pile) {
        Card drawnCard = pile.remove();
        hand.add(drawnCard);
    }

    /** Prints out a string of information for this player. */
    public String toString() {
        return hand.toString();
    }
}
