package main.game.blackjack;


/**
 * Super class for players
 * @author Saikat
 */
public class Person {

    private String playerName;

    private Hand hand;

    Person(final String name, Hand hand) {
        this.playerName = name;
        this.hand = hand;
    }

    /**
     * @param card - card instance
     * method to add card in players hand
     */
    public void receiveCard(Card card) {
        hand.addCards(card);
    }

    /**
     * @return playerName
     * getter method
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return instance of player hand
     * getter method
     */
    public Hand getHand() {
        return hand;
    }
}
