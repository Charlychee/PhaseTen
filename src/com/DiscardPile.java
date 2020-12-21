package com;

public class DiscardPile extends CardPile{

    @Override
    public void remove(Card card) {
        throw new PTException("Cannot remove a specific card.");
    }

    @Override
    public Card remove() {
        if(cards.get(cards.size()-1).getType() != Card.typeEnum.SKIP) {
            return super.remove();
        }
        else {
            throw new PTException("Cannot remove/draw 'SKIP' cards from com.DiscardPile.");
        }
    }

    /** Returns the card at the top of the DiscardPile without removing it from the CardPile. */
    public Card peek() {
        if (size == 0) {
            return null;
        }
        return cards.peek();
    }

}
