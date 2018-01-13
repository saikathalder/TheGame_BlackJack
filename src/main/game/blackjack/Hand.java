package main.game.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Determines hand of a player
 * @author Saikat
 */
public class Hand {
    private List<Card> cards = new ArrayList<>();

    /**
     * @param card - card object
     * Method to add card in a hand
     */
    public void addCards(Card card) {
        cards.add(card);
    }

    /**
     * @return list of cards in a hand
     * getter method
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * @return total card value of a hand
     * Calculates total card value
     */
    public int calculateTotal() {
        int totalValue = 0;
        for (Card card : cards) {
            totalValue += card.getRank().getValue();
        }
        return totalValue;
    }
}
