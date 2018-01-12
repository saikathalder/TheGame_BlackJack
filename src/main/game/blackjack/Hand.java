package main.game.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Determines hand of a player
 */
public class Hand {
    private List<Card> cards = new ArrayList<>();

    public void addCards(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateTotal() {
        int totalValue = 0;
        for (Card card : cards) {
            totalValue += card.getRank().getValue();
        }
        return totalValue;
    }
}
