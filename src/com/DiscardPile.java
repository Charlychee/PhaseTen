package com;

public class DiscardPile extends CardPile{

    @Override
    public void remove(Card card) {
        throw new PTException("Cannot remove a specific card.");
    }

    // TODO: Modified the method slightly, test/double check method works for general case and when pile is empty
    @Override
    public Card remove() {
        if(size > 0 && cards.peekLast().getType() != Card.typeEnum.SKIP) {
            return super.remove();
        }
        throw new PTException("Cannot remove/draw 'SKIP' cards from com.DiscardPile.");
    }

    /** Returns the card at the top of the DiscardPile without removing it from the CardPile. */
    public Card peek() {
        if (size == 0) {
            return null;
        }
        return cards.peek();
    }

}
