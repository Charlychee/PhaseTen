package com;

import java.util.Collections;

public class DrawDeck extends CardPile{
    /** Creates the standard starting deck. */
    public DrawDeck() {
        Card.typeEnum[] colors = new Card.typeEnum[]
                {
                        Card.typeEnum.BLUE,
                        Card.typeEnum.GREEN,
                        Card.typeEnum.RED,
                        Card.typeEnum.YELLOW
                };
        for (Card.typeEnum color : colors) {
            for (int k = 1; k <= 12; k += 1) {
                cards.add(new Card(color, k));
                cards.add(new Card(color, k));
            }
            cards.add(new Card(Card.typeEnum.SKIP));
            cards.add(new Card(Card.typeEnum.WILD));
            cards.add(new Card(Card.typeEnum.WILD));
        }
    }

    /** Shuffles the cards in the deck. */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /** Deals NUMCARDS cards to PLAYERS. */
    public void deal(int numCards, Player[] players) {
        for (Player p : players) {
            for (int i = 0; i < 10; i += 1) {
                p.draw(this);
            }
        }
    }

    /**
     * Cannot add cards to a com.DrawDeck
     */
    @Override
    public void add(Card card) {
        throw new PTException("You cannot add to a deck");
    }

    /**
     * Cannot draw a specific card from deck
     */
    @Override
    public void remove(Card card) {
        throw new PTException("Cannot remove/draw a specific card");
    }
}
