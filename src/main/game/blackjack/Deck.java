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
 */
public class Deck {

    private LinkedList<Card> deckOfAllCards = new LinkedList<>();

    /**
     * @return a deck containing all cards
     */
    public List<Card> getCards() {
        return deckOfAllCards;
    }

    public void createUniqueDeck() {
        for (Suits suit : Suits.values()) {
            for (Ranks rank : Ranks.values()) {
                /*String suitName = String.valueOf(Suits.values()[i]);
                deckOfAllCards.add(Suits.valueOf(suitName).getCardSuit() + Ranks.valueOf(rank.name()).getRank());*/
                deckOfAllCards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(deckOfAllCards);
        System.out.println(deckOfAllCards);
    }

    public void createDeckBasedOnFile(String fileName) {
        try {
            Stream<String> lines = Files.lines(Paths.get(fileName));
            final List<String[]> splitLines = lines.map(line -> line.split(",")).collect(Collectors.toList());
            List<String> cards = new ArrayList<>();
            for (String[] splitLine : splitLines) {
                cards.addAll(Arrays.asList(splitLine));
            }
            lines.close();
            System.out.println(cards);
            if (cards.size() > 0) {
                for (String card : cards) {
                    if (card.length() >= 2) {
                        Suits suit = Suits.getSuit(String.valueOf(card.charAt(0)));
                        Ranks rank = Ranks.getRank(card.substring(1, card.length()));
                        deckOfAllCards.add(new Card(suit, rank));
                    }
                }
            }
            System.out.println(deckOfAllCards);
        } catch (IOException e) {
            System.out.println("Not able to read provided file.Error occured=" + e);
        }
    }

    public Card drawNextCard() {
        try {
            return deckOfAllCards.remove();
        } catch (NoSuchElementException e) {
            System.out.println("No cards available to distribute");
            return null;
        }
    }
}
