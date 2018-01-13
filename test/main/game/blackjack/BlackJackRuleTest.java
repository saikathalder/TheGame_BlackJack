package main.game.blackjack;

import main.game.constants.BlackJackConstants;
import main.game.enums.Ranks;
import main.game.enums.Suits;

import java.util.ArrayList;
import java.util.List;

public class BlackJackRuleTest {

    @org.junit.Test
    public void implementBlackJackRules() {
        Person person = new Person(BlackJackConstants.PLAYER1_NAME, new Hand());
        person.receiveCard(new Card(Suits.Clubs, Ranks.JACK));
        person.receiveCard(new Card(Suits.Clubs, Ranks.ACE));

        Person person2 = new Person(BlackJackConstants.PLAYER2_NAME, new Hand());
        person2.receiveCard(new Card(Suits.Clubs, Ranks.JACK));
        person2.receiveCard(new Card(Suits.Clubs, Ranks.ACE));

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person2);
        Deck deck = new Deck();
        deck.createUniqueDeck();
        Person winner = BlackJackRule.implementBlackJackRules(personList, deck);
        System.out.println(winner.getPlayerName());
    }
}