package main.game.blackjack;

import main.game.constants.BlackJackConstants;
import main.game.enums.Ranks;
import main.game.enums.Suits;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for BlackJackRule
 *
 * @author Saikat
 */
@RunWith(MockitoJUnitRunner.class)
public class BlackJackRuleTest {

    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
        deck.createUniqueDeck();
    }

    /**
     * Method under test implementBlackJackRules
     * Scenario: When both player Sam and dealer got black jack(21)
     * Outcome : Sam Always wins
     */
    @Test
    public void testBlackJackWinnerWhenBothPlayerGotBlackJack() {

        Person sam = addCardToPlayerHand(BlackJackConstants.PLAYER1_NAME, new Card(Suits.Clubs, Ranks.JACK), new Card(Suits.Hearts, Ranks.ACE));
        Person dealer = addCardToPlayerHand(BlackJackConstants.PLAYER2_NAME, new Card(Suits.Spades, Ranks.JACK), new Card(Suits.Diamonds, Ranks.ACE));

        List<Person> personList = addPlayerToGame(sam, dealer);

        Person winner = BlackJackRule.implementBlackJackRules(personList, deck);
        Assert.assertEquals(BlackJackConstants.PLAYER1_NAME, winner.getPlayerName());
    }

    /**
     * Method under test implementBlackJackRules
     * Scenario: When sam got card with value 15 and dealer got blackjack
     * Outcome : Dealer Always wins
     */
    @Test
    public void testBlackJackWinnerWhenOnePlayerGotBlackJack() {

        Person sam = addCardToPlayerHand(BlackJackConstants.PLAYER1_NAME, new Card(Suits.Clubs, Ranks.FIVE), new Card(Suits.Clubs, Ranks.ACE));
        Person dealer = addCardToPlayerHand(BlackJackConstants.PLAYER2_NAME, new Card(Suits.Clubs, Ranks.JACK), new Card(Suits.Clubs, Ranks.ACE));

        List<Person> personList = addPlayerToGame(sam, dealer);

        Person winner = BlackJackRule.implementBlackJackRules(personList, deck);
        Assert.assertEquals(BlackJackConstants.PLAYER2_NAME, winner.getPlayerName());
    }

    /**
     * Method under test implementBlackJackRules
     * Scenario: When both player Sam and dealer got highest card(22)
     * Outcome : Dealer Always wins
     */
    @Test
    public void testBlackJackWinnerWhenBothPlayerGotHighestCard() {

        Person sam = addCardToPlayerHand(BlackJackConstants.PLAYER1_NAME, new Card(Suits.Clubs, Ranks.ACE), new Card(Suits.Spades, Ranks.ACE));
        Person dealer = addCardToPlayerHand(BlackJackConstants.PLAYER2_NAME, new Card(Suits.Hearts, Ranks.ACE), new Card(Suits.Diamonds, Ranks.ACE));

        List<Person> personList = addPlayerToGame(sam, dealer);

        Person winner = BlackJackRule.implementBlackJackRules(personList, deck);
        Assert.assertEquals(BlackJackConstants.PLAYER2_NAME, winner.getPlayerName());
    }

    /**
     * Method under test implementBlackJackRules
     * Scenario: When Sam got highest card(22) and dealer got card with value 18
     * Outcome : Sam always win
     */
    @Test
    public void testBlackJackWinnerWhenOnePlayerGotHighestCard() {

        Person sam = addCardToPlayerHand(BlackJackConstants.PLAYER1_NAME, new Card(Suits.Clubs, Ranks.ACE), new Card(Suits.Spades, Ranks.ACE));
        Person dealer = addCardToPlayerHand(BlackJackConstants.PLAYER2_NAME, new Card(Suits.Clubs, Ranks.EIGHT), new Card(Suits.Hearts, Ranks.ACE));

        List<Person> personList = addPlayerToGame(sam, dealer);

        Person winner = BlackJackRule.implementBlackJackRules(personList, deck);
        Assert.assertEquals(BlackJackConstants.PLAYER1_NAME, winner.getPlayerName());
    }


    /**
     * Method under test implementBlackJackRules
     * Scenario: When player sam got card with value 18 and dealer got card with valie 19
     * Outcome : Highest card value(dealer) always win
     */
    @Test
    public void testBlackJackWinnerWhenOnePlayerGotHigherCard() {

        Person sam = addCardToPlayerHand(BlackJackConstants.PLAYER1_NAME, new Card(Suits.Clubs, Ranks.ACE), new Card(Suits.Clubs, Ranks.EIGHT));
        Person dealer = addCardToPlayerHand(BlackJackConstants.PLAYER2_NAME, new Card(Suits.Diamonds, Ranks.ACE), new Card(Suits.Hearts, Ranks.NINE));

        List<Person> personList = addPlayerToGame(sam, dealer);

        Person winner = BlackJackRule.implementBlackJackRules(personList, deck);
        Assert.assertEquals(BlackJackConstants.PLAYER2_NAME, winner.getPlayerName());
    }

    /**
     * Method under test implementBlackJackRules
     * Scenario: When sam got card with value 10 and dealer got card with value 15
     * Sam will draw next card 7 and total value will be 17, the dealer will draw
     * next card 7 and total value will be 22
     * Outcome : Sam will win
     */
    @Test
    public void testBlackJackWinnerWhenBothPlayerCanDrawCardAgain() {

        Person sam = addCardToPlayerHand(BlackJackConstants.PLAYER1_NAME, new Card(Suits.Clubs, Ranks.FIVE), new Card(Suits.Spades, Ranks.FIVE));
        Person dealer = addCardToPlayerHand(BlackJackConstants.PLAYER2_NAME, new Card(Suits.Diamonds, Ranks.ACE), new Card(Suits.Hearts, Ranks.FOUR));

        deck = Mockito.mock(Deck.class);
        Mockito.when(deck.drawNextCard()).thenReturn(new Card(Suits.Spades,Ranks.SEVEN));

        List<Person> personList = addPlayerToGame(sam, dealer);

        Person winner = BlackJackRule.implementBlackJackRules(personList, deck);
        Assert.assertEquals(BlackJackConstants.PLAYER1_NAME, winner.getPlayerName());
    }

    /**
     * Method under test implementBlackJackRules
     * Scenario: When sam got card with value 18 and dealer got card with value 10
     * Dealer will draw next card 9 and total value will be 19
     * Outcome : Dealer will win
     */
    @Test
    public void testBlackJackWinnerWhenOnePlayerCanDrawCardAgain() {

        Person sam = addCardToPlayerHand(BlackJackConstants.PLAYER1_NAME, new Card(Suits.Clubs, Ranks.ACE), new Card(Suits.Hearts, Ranks.SEVEN));
        Person dealer = addCardToPlayerHand(BlackJackConstants.PLAYER2_NAME, new Card(Suits.Clubs, Ranks.FIVE), new Card(Suits.Spades, Ranks.FIVE));

        deck = Mockito.mock(Deck.class);
        Mockito.when(deck.drawNextCard()).thenReturn(new Card(Suits.Diamonds,Ranks.NINE));

        List<Person> personList = addPlayerToGame(sam, dealer);

        Person winner = BlackJackRule.implementBlackJackRules(personList, deck);
        Assert.assertEquals(BlackJackConstants.PLAYER2_NAME, winner.getPlayerName());
    }


    /**
     * @param sam    - player 1
     * @param dealer - player 2
     * @return Person list
     */
    private List<Person> addPlayerToGame(Person sam, Person dealer) {
        List<Person> personList = new ArrayList<>();
        personList.add(sam);
        personList.add(dealer);
        return personList;
    }

    /**
     * @param playerName - name of the player
     * @param firstCard  - first card
     * @param secondCard - second card
     * @return
     */
    private Person addCardToPlayerHand(String playerName, Card firstCard, Card secondCard) {
        Person person = new Person(playerName, new Hand());
        person.receiveCard(firstCard);
        person.receiveCard(secondCard);
        return person;
    }

}