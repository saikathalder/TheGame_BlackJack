package main.game.blackjack;

import main.game.constants.BlackJackConstants;

import java.util.List;

/**
 * Class to implement BlackJack rules and decide winner
 * @author Saikat
 */
public class BlackJackRule {

    /**
     * @param players - list of players
     * @return person - winner
     * Method to decide winner based on score and set of game rules
     */
    public static Person implementBlackJackRules(List<Person> players, Deck deck) {
        int highestPersonCardValue = 0;
        Person winner = players.get(0);
        boolean isBlackJack = false;
        for (Person person : players) {
            int personCardValue = person.getHand().calculateTotal();
            // If both player got blackjack, Sam is winner
            //If either one of the player got blackjack, that player will be winner
            if (!isBlackJack && personCardValue == BlackJackConstants.BLACKJACK) {
                winner = person;
                isBlackJack = true;
                continue;
            }
            // If both player got highest hand(22(A+A)), dealer is the winner
            if (personCardValue == BlackJackConstants.HIGHEST_CARD_VALUE) {
                winner = person;
                highestPersonCardValue = personCardValue;
            }

            // If player got hand less than 17, sam starts draw next card until reaches 17 or higher
            // After sam, dealer draw next card until dealer reaches equal or less than sam
            if (personCardValue < BlackJackConstants.STOP_DRAW_CARD_VALUE) {
                while (personCardValue < BlackJackConstants.STOP_DRAW_CARD_VALUE) {
                    Card card = deck.drawNextCard();
                    person.receiveCard(card);
                    personCardValue = person.getHand().calculateTotal();
                }
                if (personCardValue <= BlackJackConstants.BLACKJACK && personCardValue > highestPersonCardValue) {
                    winner = person;
                    highestPersonCardValue = personCardValue;
                }
            } else if(personCardValue < BlackJackConstants.BLACKJACK){ // Else, player who got more value card, is the winner
                if (personCardValue > highestPersonCardValue) {
                    highestPersonCardValue = personCardValue;
                    winner = person;
                }
            }
        }
        return winner;
    }

}
