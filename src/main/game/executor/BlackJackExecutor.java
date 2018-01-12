package main.game.executor;

import main.game.blackjack.BlackJack;
import main.game.constants.BlackJackConstants;
import main.game.enums.Suits;

import java.util.stream.Collectors;

/**
 * Main class for executing BlackJack game
 * This is the starting point of the application
 */
public class BlackJackExecutor {

    /**
     * @param args Main method to start BlackJack game
     */
    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("!! Welcome to Black Jack !!");
        System.out.println("==============================================================");

        System.out.println(Suits.getSuit("C"));
        playGame(args);

    }

    private static void playGame(String[] args) {
        BlackJack blackJack = BlackJack.initializeBlackJack();
        System.out.println("Checking whether file reference passed as an argument..");
        if (args.length > 0) {
            System.out.println("File name " + args[0] + " given for playing..");
            blackJack.initializeDeck(args[0]);
        } else {
            System.out.println("No files given. New shuffled deck of unique cards will be given for playing..");
            BlackJackExecutor blackJackExecutor = new BlackJackExecutor();
          /*  Deck deck = new Deck();
            deck.createUniqueDeck();
            System.out.println(deck.getCards());
            System.out.println(deck.getCards().size());*/
            blackJack.initializeDeck();
        }
        blackJack.addPlayers(BlackJackConstants.PLAYER1_NAME, BlackJackConstants.PLAYER2_NAME);
        blackJack.distributeCards(BlackJackConstants.TOTAL_NO_OF_CARDS_TO_DISTRIBUTE);
        System.out.println(BlackJackConstants.PLAYER1_NAME + ":" + blackJack.showHand(BlackJackConstants.PLAYER1_NAME).stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
        System.out.println(BlackJackConstants.PLAYER2_NAME + ":" + blackJack.showHand(BlackJackConstants.PLAYER2_NAME).stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
        System.out.println("Winner is :" + blackJack.getWinner());
    }
}
