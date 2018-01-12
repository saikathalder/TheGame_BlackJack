package main.game.blackjack;

import main.game.constants.BlackJackConstants;
import main.game.enums.Ranks;
import main.game.enums.Suits;

import java.util.ArrayList;
import java.util.List;

public class BlackJackRuleExecutorTest {

    @org.junit.Test
    public void implementBlackJackRules() {
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
        Person winner = BlackJackRuleExecutor.implementBlackJackRules(personList, deck);
        System.out.println(winner.getPlayerName());
    }
}