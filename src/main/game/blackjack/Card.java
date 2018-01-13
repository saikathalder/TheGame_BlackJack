package main.game.blackjack;

import main.game.enums.Ranks;
import main.game.enums.Suits;

/**
 * Class defines a card with a particular suit and rank
 * @author Saikat
 */
public class Card {

    private Suits suit;

    private Ranks rank;

    protected Card(Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * @return String in a particular format
     * mixed of suit and rank
     */
    @Override
    public String toString() {
        return suit.getCardSuit() + rank.getRank();
    }

    /**
     * @return Ranks - instance of Ranks
     * getter method
     */
    public Ranks getRank() {
        return rank;
    }
}
