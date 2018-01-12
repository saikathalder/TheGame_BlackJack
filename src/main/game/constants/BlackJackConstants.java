package main.game.constants;


public final class BlackJackConstants {

    private BlackJackConstants() {
        // Making default constructor private so that class cannot be instantiated
    }

    public static final String PLAYER1_NAME = "Sam";

    public static final String PLAYER2_NAME = "Dealer";

    public static final int TOTAL_NO_OF_CARDS_TO_DISTRIBUTE = 2;

    public static final int BLACKJACK = 21;

    public static final int HIGHEST_CARD_VALUE = 22;

    public static final int STOP_DRAW_CARD_VALUE = 17;
}
