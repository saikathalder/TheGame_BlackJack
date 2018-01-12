package main.game.blackjack;

import main.game.constants.BlackJackConstants;
import main.game.enums.Ranks;
import main.game.enums.Suits;

import java.util.ArrayList;
import java.util.List;

public class BlackJackRuleExecutor {

    /**
     * @param players
     * @return
     */
    public static Person implementBlackJackRules(List<Person> players, Deck deck) {
        int highestPersonCardValue = 0;
        Person winner = players.get(0);
        boolean isBlackJack = false;
        for (Person person : players) {
            int personCardValue = person.getHand().calculateTotal();
            if (!isBlackJack && personCardValue == BlackJackConstants.BLACKJACK) {
                winner = person;
                isBlackJack = true;
            }
            if (personCardValue == BlackJackConstants.HIGHEST_CARD_VALUE) {
                winner = person;
            }
            if (personCardValue < BlackJackConstants.STOP_DRAW_CARD_VALUE) {
                while (personCardValue < BlackJackConstants.STOP_DRAW_CARD_VALUE) {
                    person.receiveCard(deck.drawNextCard());
                    personCardValue = person.getHand().calculateTotal();
                }
                if (personCardValue <= BlackJackConstants.BLACKJACK && personCardValue > highestPersonCardValue) {
                    winner = person;
                    highestPersonCardValue = personCardValue;
                }
            } else {
                if (personCardValue > highestPersonCardValue) {
                    highestPersonCardValue = personCardValue;
                    winner = person;
                }
            }
        }
        return winner;
    }

    public static void main(String[] args) {
        Person person = new Person(BlackJackConstants.PLAYER1_NAME, new Hand());
        person.receiveCard(new Card(Suits.Clubs, Ranks.FIVE));
        person.receiveCard(new Card(Suits.Clubs, Ranks.TWO));

        Person person2 = new Person(BlackJackConstants.PLAYER2_NAME, new Hand());
        person2.receiveCard(new Card(Suits.Clubs, Ranks.SEVEN));
        person2.receiveCard(new Card(Suits.Clubs, Ranks.EIGHT));

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person2);
        Deck deck = new Deck();
        deck.createUniqueDeck();
        Person winner = implementBlackJackRules(personList, deck);
        System.out.println(winner.getPlayerName());
    }
}
