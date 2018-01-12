package main.game.blackjack;


/**
 * Interface for players
 */
public class Person {

    private String playerName = "";

    private Hand hand;

    public Person(final String name, Hand hand) {
        this.playerName = name;
        this.hand = hand;
    }

    public void receiveCard(Card card) {
        hand.addCards(card);
    }

    public String getPlayerName() {
        return playerName;
    }

    public Hand getHand() {
        return hand;
    }
}
