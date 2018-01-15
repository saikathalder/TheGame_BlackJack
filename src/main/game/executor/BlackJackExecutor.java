package main.game.executor;

import main.game.blackjack.BlackJack;
import main.game.constants.BlackJackConstants;
import main.game.enums.Suits;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Main class for executing BlackJack game
 * This is the starting point of the application
 * @author Saikat
 */
public class BlackJackExecutor {

    /**
     * @param args
     * Main method to start BlackJack game
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("==============================================================");
        System.out.println("!! Welcome to Black Jack !!");
        System.out.println("==============================================================");

        playGame(args);

    }

    /**
     * @param args - this may contain fileName
     * method to play blackjack game
     * @throws  Exception
     */
    private static void playGame(String[] args) throws Exception {
        BlackJack blackJack = BlackJack.initializeBlackJack();
        System.out.println("Checking whether file reference passed as an argument..");
        if (args.length > 0) {
            System.out.println("File name " + args[0] + " given for playing..");
            blackJack.initializeDeck(args[0]);
        } else {
            System.out.println("No files given. New shuffled deck of unique cards will be given for playing..");
            BlackJackExecutor blackJackExecutor = new BlackJackExecutor();
            blackJack.initializeDeck();
        }
        blackJack.addPlayers(BlackJackConstants.PLAYER1_NAME, BlackJackConstants.PLAYER2_NAME);
        blackJack.distributeCards(BlackJackConstants.INITIAL_NO_OF_CARDS_TO_DISTRIBUTE);
        System.out.println("Showing initial hands..");
        System.out.println(BlackJackConstants.PLAYER1_NAME + ":" + blackJack.showHand(BlackJackConstants.PLAYER1_NAME).stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
        System.out.println(BlackJackConstants.PLAYER2_NAME + ":" + blackJack.showHand(BlackJackConstants.PLAYER2_NAME).stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
        System.out.println("Winner is :" + blackJack.getWinner());
    }
}
