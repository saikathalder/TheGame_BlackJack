package main.game.blackjack;

import main.game.constants.BlackJackConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Test class for Deck
 */
public class DeckTest {

    private Deck deck;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        deck = new Deck();
    }

    /**
     * Method under test createUniqueDeck
     */
    @Test
    public void testCreateUniqueDeck() {
        deck.createUniqueDeck();
        LinkedList<Card> cards = deck.getDeckOfAllCards();
        Assert.assertNotNull(cards);
        Assert.assertTrue(cards.size() == BlackJackConstants.TOTAL_CARD_IN_DECK);
    }

    /**
     * Method under test createDeckBasedOnFile
     * Scenarion : success case
     */
    @Test
    public void testCreateDeckBasedOnCorrectFile() throws Exception {
        deck.createDeckBasedOnFile("./src/main/resources/Blackjackfile");
        LinkedList<Card> cards = deck.getDeckOfAllCards();
        Assert.assertNotNull(cards);
        Assert.assertTrue(cards.size() == BlackJackConstants.TOTAL_CARD_IN_DECK);
    }

    /**
     * Method under test createDeckBasedOnFile
     * Scenario : when file content is not valid
     * Outcome : NoSuchElementException should be thrown
     */
    @Test
    public void testCreateDeckBasedOnFileWithInvalidContents() throws Exception {
        expectedException.expect(NoSuchElementException.class);
        deck.createDeckBasedOnFile("./src/main/resources/Blackjackfile_InvalidContent");
    }

    /**
     * Method under test createDeckBasedOnFile
     * Scenario : when file path is not valid
     * Outcome : IOException should be thrown
     */
    @Test
    public void testCreateDeckBasedOnFileWithInvalidPath() throws Exception {
        expectedException.expect(IOException.class);
        deck.createDeckBasedOnFile("./src/main/resources/Blackjackfile_FilePathNInvalid");
    }

    /**
     * Method under test createDeckBasedOnFile
     * Scenario : when file is empty
     * Outcome : IOException should be thrown
     */
    @Test
    public void testCreateDeckBasedOnFileWithEmptyFile() throws Exception {
        expectedException.expect(IOException.class);
        deck.createDeckBasedOnFile("./src/main/resources/Blackjackfile_Empty");
    }

    /**
     * Method under test drawNextCard
     * Scenario : Success case
     */
    @Test
    public void testDrawNextCardWithSuccessCase() {
        deck.createUniqueDeck();
        Card card = deck.drawNextCard();
        Assert.assertTrue(card != null);
        Assert.assertFalse(deck.getDeckOfAllCards().contains(card));
    }

    /**
     * Method under test drawNextCard
     * Scenario : when deck do not have element present
     * Outcome : no card will be returned
     */
    @Test
    public void testDrawNextCardWithNoElementPresent() {
        Card card = deck.drawNextCard();
        Assert.assertTrue(card == null);
    }
}