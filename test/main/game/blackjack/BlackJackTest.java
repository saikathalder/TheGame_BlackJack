package main.game.blackjack;

import main.game.constants.BlackJackConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Test class for BlackJack
 */
public class BlackJackTest {

    private BlackJack blackJack;

    @Before
    public void setUp() {
        blackJack = BlackJack.initializeBlackJack();
    }

    /**
     * Method under test initializeBlackJack
     */
    @Test
    public void testInitializeBlackJack() {
        Assert.assertTrue(blackJack!= null);
    }

    /**
     * Method under test initializeDeck
     */
    @Test
    public void testInitializeDeck() {
        blackJack.initializeDeck();
        assertDeck(blackJack);
    }

    /**
     * Method under test initializeDeck(String fileName)
     * Scenario success case
     * @throws Exception - exception can be thrown
     */
    @Test
    public void testInitializeDeckWithProperFile() throws Exception{
        blackJack.initializeDeck("./src/main/resources/Blackjackfile");
        assertDeck(blackJack);
    }

    /**
     * Method under test initializeDeck(String fileName)
     * Scenario failure case
     */
    @Test
    public void testInitializeDeckWithBlankFile(){
        try{
            blackJack.initializeDeck("./src/main/resources/Blackjackfile_Empty");
        }catch(Exception e){
            System.err.println("Error occured when processing file "+e);
        }
        Assert.assertTrue(blackJack.getDeck().getDeckOfAllCards().size() == 0);
    }


    /**
     * Method under test distributeCards
     */
    @Test
    public void testDistributeCards() {
        blackJack.initializeDeck();
        blackJack.addPlayers(BlackJackConstants.PLAYER1_NAME,BlackJackConstants.PLAYER2_NAME);
        blackJack.distributeCards(2);
        List<Card> handOfPlayer1= blackJack.showHand(BlackJackConstants.PLAYER1_NAME);
        Assert.assertTrue(handOfPlayer1!= null);
        Assert.assertEquals(handOfPlayer1.size(),2);
    }

    /**
     * Method under test addPlayers()
     */
    @Test
    public void testAddPlayers() {
        blackJack.addPlayers(BlackJackConstants.PLAYER1_NAME,BlackJackConstants.PLAYER2_NAME);
        Assert.assertTrue(blackJack.getPlayers() != null);
        Assert.assertEquals(blackJack.getPlayers().size(),2);
    }

    /**
     * Method under test showHand
     */
    @Test
    public void testShowHand() {
        testDistributeCards();
    }

    /**
     * Method under test getWinner
     * @throws Exception - exception can be thrown
     */
    @Test
    public void getWinner() throws Exception {
        blackJack.initializeDeck("./src/main/resources/Blackjackfile");
        blackJack.addPlayers(BlackJackConstants.PLAYER1_NAME,BlackJackConstants.PLAYER2_NAME);
        blackJack.distributeCards(2);
        String winnerName = blackJack.getWinner();
        Assert.assertEquals(BlackJackConstants.PLAYER2_NAME,winnerName);
    }

    /**
     * common assertion method to test deck with cards
     */
    private void assertDeck(BlackJack blackJack) {
        Assert.assertTrue(blackJack.getDeck() != null);
        Assert.assertTrue(blackJack.getDeck().getDeckOfAllCards()!= null);
        Assert.assertEquals(blackJack.getDeck().getDeckOfAllCards().size() , BlackJackConstants.TOTAL_CARD_IN_DECK);
    }
}