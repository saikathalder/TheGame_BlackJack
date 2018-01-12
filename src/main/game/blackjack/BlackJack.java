package main.game.blackjack;

import java.util.ArrayList;
import java.util.List;

public final class BlackJack {

    Deck deck = new Deck();
    private List<Person> players = new ArrayList<>();

    public static BlackJack initializeBlackJack() {
        return new BlackJack();
    }

    public void initializeDeck() {
        deck.createUniqueDeck();
    }

    public void initializeDeck(String fileName) {
        deck.createDeckBasedOnFile(fileName);
    }

    public void distributeCards(int noOfCards) {
        for (int i = 0; i < noOfCards; i++) {
            for (Person person : players) {
                person.receiveCard(deck.drawNextCard());
            }
        }
    }

    /**
     * @param playerNames Method to add players
     *                    List is created based on players inserted
     */
    public void addPlayers(final String... playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            players.add(i, new Player(playerNames[i], new Hand()));
        }
    }

    public List<Card> showHand(String playerName) {
        Person person = players.stream().filter(player -> player.getPlayerName().equalsIgnoreCase(playerName)).findFirst().get();
        return person.getHand().getCards();
    }

    /**
     * @return Winner Name
     * Determines winner based on total card points of a user and set of rules
     */
    public String getWinner() {
        return BlackJackRuleExecutor.implementBlackJackRules(players, deck).getPlayerName();
    }
}
