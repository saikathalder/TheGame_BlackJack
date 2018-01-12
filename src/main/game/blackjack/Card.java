package main.game.blackjack;

import main.game.enums.Ranks;
import main.game.enums.Suits;

/**
 *
 */
public class Card {

    private Suits suit;

    private Ranks rank;

    public Card(Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return suit.getCardSuit() + rank.getRank();
    }

    public Card getCard(String card) {
        this.suit = Suits.valueOf(String.valueOf(card.charAt(0)));
        this.rank = Ranks.valueOf(String.valueOf(card.charAt(1)));
        return new Card(suit, rank);
    }

    public Ranks getRank() {
        return rank;
    }
}
