package com;

import java.util.LinkedList;

/** Represents a collection of cards. Class that's extended by com.DrawDeck,
 *  com.DiscardPile, com.Hand, and com.PlayerCardPile.
 */
public class CardPile {

    /** Holds all the cards in the pile. */
    protected LinkedList<Card> cards;

    /** The size of the card pile. */
    protected int size;

    /** Creates an empty com.CardPile. */
    public CardPile() {
        cards = new LinkedList<>();
        size = 0;
    }

    /** Creates a com.CardPile with _CARDS. */
    public CardPile(LinkedList<Card> _cards) {
        cards = _cards;
        size = _cards.size();
    }

    /** Adds a com.Card to the top of the CardPIle and updates size. */
    public void add(Card card) {
        cards.push(card);
        ++size;
    }

    /** Removes a com.Card from the top of the com.CardPile and updates size. */
    public Card remove() {
        --size;
        return cards.pop();
    }

    /** Removes specified com.Card from the com.CardPile and updates size. */
    public void remove(Card card) {
        for(Card c : cards) {
            if(c.equals(card)) {
                cards.remove(c);
                --size;
                return;
            }
        }
        throw new PTException("Card does not exist in CardPile");
    }

    /** Returns a copy of the cards in the com.CardPile. */
    public LinkedList<Card> getCards() {
        return new LinkedList<>(cards);
    }

    /** Returns the size of the com.CardPile. */
    public int getSize() {
        return size;
    }

    /** Prints out the cards in this card pile. */
    public String toString() {
        String info = "";
        for (Card c : cards) {
            info += c.toString() + "\n";
        }
        return info;
    }


}
