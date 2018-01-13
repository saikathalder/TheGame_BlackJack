package main.game.enums;

import java.util.stream.Stream;

/**
 * Enum for Ranks of Cards
 * @author Saikat
 */
public enum Ranks {

    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 11);

    private String rank;

    private int value;

    Ranks(final String rank, final int value) {
        this.rank = rank;
        this.value = value;
    }

    /**
     * @param rank - rank of card
     * @return Ranks object based on rank
     */
    public static Ranks getRank(String rank) {
        return Stream.of(Ranks.values()).filter(rankObj -> rankObj.getRank().equalsIgnoreCase(rank)).findFirst().get();
    }

    /**
     * @return rank - rank of card
     */
    public String getRank() {
        return rank;
    }

    /**
     * @return value - value of card
     */
    public int getValue() {
        return value;
    }
}
