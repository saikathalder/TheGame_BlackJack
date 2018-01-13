package main.game.enums;

import java.util.stream.Stream;

/*
    Enum for Card Suits
*/
public enum Suits {
    Clubs("C"), Diamonds("D"), Hearts("H"), Spades("S");

    private String cardSuit;

    Suits(final String cardSuit) {
        this.cardSuit = cardSuit;
    }

    /**
     * @param cardSuit - cardSuit value
     * @return Suits instance
     */
    public static Suits getSuit(String cardSuit) {
        return Stream.of(Suits.values()).filter(suit -> suit.getCardSuit().equalsIgnoreCase(cardSuit)).findFirst().get();
    }

    /**
     * @return cardSuit - getter method
     */
    public String getCardSuit() {
        return cardSuit;
    }
}
