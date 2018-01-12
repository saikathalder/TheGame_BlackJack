package main.game.enums;

import java.util.stream.Stream;

/*
    Enum for Card Suits
*/
public enum Suits {
    Clubs("C"), Diamonds("D"), Hearts("H"), Spades("S");

    private String cardSuit;

    private Suits(final String cardSuit) {
        this.cardSuit = cardSuit;
    }

    public static Suits getSuit(String cardSuit) {
        return Stream.of(Suits.values()).filter(suit -> suit.getCardSuit().equalsIgnoreCase(cardSuit)).findFirst().get();
    }

    public String getCardSuit() {
        return cardSuit;
    }
}
