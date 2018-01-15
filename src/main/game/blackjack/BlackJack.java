package main.game.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a gateway for interacting the components of blackjack
 * @author Saikat
 */
public class BlackJack {

    private Deck deck = new Deck();

    private List<Person> players = new ArrayList<>();

    private  BlackJack(){
        // Private Constructor so that class cannot be instantiated
    }

    /**
     * @return Deck
     * getter method
     */
    public Deck getDeck(){
        return deck;
    }

    /**
     * @return player list
     * getter method
     */
    public List<Person> getPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * @return instance of blackjack
     * Method to return BlackJack instance
     */
    public static BlackJack initializeBlackJack() {
        // Provision to make it singleton, so that same instance should be available
        // Should implement based on requirement
       return new BlackJack();
    }

    /**
     * Method to call deck class to fill deck based on unique random cards
     */
    public void initializeDeck() {
        deck.createUniqueDeck();
    }

    /**
     * @param fileName - name of the file which consists of card information
     * Method call deck class to initialize deck based on cards
     * @throws Exception - Throws exception if problem with file reading
     */
    public void initializeDeck(String fileName) throws Exception{
        deck.createDeckBasedOnFile(fileName);
    }

    /**
     * @param noOfCards - initial no of cards to distribute
     * Method to draw cards from deck and add into players hand
     */
    public void distributeCards(int noOfCards) {
        for (int i = 0; i < noOfCards; i++) {
            for (Person person : players) {
                person.receiveCard(deck.drawNextCard());
            }
        }
    }

    /**
     * @param playerNames - name of players
     * Method to add players in the list
     * Player will be added in a same order addPlayers method gets called
     */
    public void addPlayers(final String... playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            players.add(i, new Person(playerNames[i], new Hand()));
        }
    }

    /**
     * @param playerName - name of player
     * @return list of Card
     * Method to show initial hand of players
     */
    public List<Card> showHand(String playerName) {
        Person person = players.stream().filter(player -> player.getPlayerName().equalsIgnoreCase(playerName)).findFirst().get();
        return person.getHand().getCards();
    }

    /**
     * @return Winner Name
     * Determines winner based on total card points of a user and set of rules
     */
    public String getWinner() {
        return BlackJackRule.implementBlackJackRules(players, deck).getPlayerName();
    }
}
