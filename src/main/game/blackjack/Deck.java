package main.game.blackjack;

import main.game.enums.Ranks;
import main.game.enums.Suits;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for generating deck of Ranks
 * Deck contains 52 cards of 4 suits
 * @author Saikat
 */
public class Deck {

    private LinkedList<Card> deckOfAllCards = new LinkedList<>();

    /**
     * @return list of cards in a deck
     * getter method
     */
    public LinkedList<Card> getDeckOfAllCards() {
        return new LinkedList<>(deckOfAllCards);
    }

    /**
     * Method to create unique deck based on random cards
     */
    protected void createUniqueDeck() {
        for (Suits suit : Suits.values()) {
            for (Ranks rank : Ranks.values()) {
                deckOfAllCards.add(new Card(suit, rank));
            }
        }
        // shuffle the list so that every time random deck can be prepared
        Collections.shuffle(deckOfAllCards);
        System.out.println("Deck filled with cards : "+deckOfAllCards);
    }

    /**
     * @param fileName - path of the file which contains card details
     * Method to create deck based on card details of the file
     * @throws  Exception - Throw exception if problem reading file
     */
    protected void createDeckBasedOnFile(String fileName) throws Exception{
        try {
            // Read files line by line using Java8 stream object
            Stream<String> lines = Files.lines(Paths.get(fileName));
            // split each card details based on comma separator
            final List<String[]> splitLines = lines.map(line -> line.split(",")).collect(Collectors.toList());
            List<String> cards = new ArrayList<>();
            for (String[] splitLine : splitLines) {
                cards.addAll(Arrays.asList(splitLine));
            }
            // Closing line stream object
            lines.close();
            // Fill deck with cards based on card details on file
            if (cards.size() > 0) {
                for (String card : cards) {
                    if (card.length() >= 2) {
                        Suits suit = Suits.getSuit(String.valueOf(card.charAt(0)));
                        Ranks rank = Ranks.getRank(card.substring(1, card.length()));
                        deckOfAllCards.add(new Card(suit, rank));
                    }
                }
            }else {
                throw new IOException("There is no cards present in the file to fill deck");
            }

            System.out.println("Deck filled with cards : "+deckOfAllCards);
        } catch (IOException e) {
            System.err.println("Error reading file=" + fileName + " with error "+e);
            throw e;
        } catch (Exception e) {
            System.err.println("Error reading file contents="+e);
            throw e;
        }
    }

    /**
     * @return Card
     * Method to return next card from the deck
     * returned card also removed from the deck
     */
    protected Card drawNextCard() {
        try {
            return deckOfAllCards.remove();
        } catch (NoSuchElementException e) {
            System.err.println("No cards available to distribute");
            return null;
        }
    }
}
