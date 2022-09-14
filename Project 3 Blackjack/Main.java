import java.util.Scanner;
import java.util.Random;

class Main {
  public static void main(String[] args) {
/* Version 1.0

 Write a command line game that plays a simple version of blackjack.
 The program should generate a random number between 1 and 10 each time the player gets a card.
 It should keep a running total of the player's cards,
 and ask the player whether or not it should deal another card.
 If the player hits 21 exactly, the program should print "Congratulations!"
 and then ask if the player wants to play again.
 If the player exceeds 21, the program should print "Bust" and then ask if the player wants to play again.


*/
/* Version 1.1:  
    
 Added dealer game play vs. user and statistics (num wins) for end of game
 Change DEALER_STANDS to a different value to have dealer stand on another hand
*/

    int DEALER_STANDS = 17;      // dealer stands at this value and above

    int card1;                   // stores first card drawn
    int card2;                   // stores second card drawn
    int nextCard;                // stores card drawn after 1st 2 cards
    int dealerCard1;             // stores dealer drawn first card
    int dealerCard2;             // stores dealer drawn second card (shown)
    int dealerNext;              // stores cards drawn by dealer
    int playerTotal;             // sum of all cards drawn for current player hand
    int dealerTotal;             // sum of all cards drawn for dealer hand
    int playerWinCount = 0;      // track number of player wins
    int dealerWinCount = 0;      // track number of dealer wins
    char userInput;              // temp variable used to store user inputs
    boolean playAgain = true;    // set to true to if user wants to play a hand
    boolean anotherCard = false; // set to true if user wants another card

    Random rand = new Random();
    Scanner scnr = new Scanner(System.in);
    
    // always play at least one hand
    while (playAgain) {
      // generate first 2 hands and display cards to user, cards dealt 1 by 1
      card1 = rand.nextInt(10) + 1;
      dealerCard1 = rand.nextInt(10) + 1;
      card2 = rand.nextInt(10) + 1;
      dealerCard2 = rand.nextInt(10) + 1;
    
      // keep track of totals in each hand
      playerTotal = card1 + card2;
      dealerTotal = dealerCard1 + dealerCard2;
      System.out.println("First cards: " + card1 + " & " + card2 );
      System.out.println("Total: " + playerTotal);
      System.out.println("Dealer shows: " + dealerCard2);
    
      // ask user about adding another card
      // only check affirmative case as all other entries imply 'n'
      System.out.print("Do you want another card (y/n)? ");
      userInput = scnr.next().charAt(0);
      anotherCard = (userInput == 'y');
    
      while (anotherCard) {
        // draw another card and update total
        nextCard = rand.nextInt(10) + 1;
        playerTotal += nextCard;
        // output new total
        System.out.println("Card: " + nextCard);
        System.out.println("Total: " + playerTotal);
        if (playerTotal > 21) {
          System.out.println("Bust.");
          anotherCard = false;  // end while loop and dont ask for another card
        } else if (playerTotal == 21) {
          System.out.println("Backjack!"); 
          anotherCard = false;  // end while loop and dont ask for another card
        } else {
          System.out.print("Do you want another card (y/n)? ");
          userInput = scnr.next().charAt(0);
          anotherCard = (userInput == 'y');
        }
      }
      // play dealer hand
      System.out.println("Dealer shows: " + dealerCard1 + " & " + dealerCard2);
      System.out.println("Total: " + dealerTotal);
      anotherCard = true;
      
      // first check to see if dealer must stand
      if (dealerTotal >= DEALER_STANDS) {
        anotherCard = false;
        System.out.println("Dealer stands on: " + dealerTotal);
      }
        // draw cards until either busts, or must stand above DEALER_STANDS but <= 21
      while (anotherCard) {
        // draw one card and update total
        dealerNext = rand.nextInt(10) + 1;
        dealerTotal += dealerNext;
        System.out.println("Dealer draws: " + dealerNext );
        System.out.println("Total: " + dealerTotal);
        if (dealerTotal > 21) {
          System.out.println("Dealer busts!");
          anotherCard = false; // stop drawing cards
        } else if (dealerTotal == 21) {
          System.out.println("Dealer Blackjack!!");
          anotherCard = false; // stop drawing cards
        } else if (dealerTotal >= DEALER_STANDS) {
          System.out.println("Dealer stands.");
          anotherCard = false; // stop drawing cards
        } else {
          anotherCard = true;  // get another card and update
        }
      }
    /*
    Check win conditions as follows:
      A. Both players bust (> 21)
      B. Both players have same score but don't bust (<=21)
      C. Dealer busted, player did not bust
      D. Player busted, dealer did not
      E. Dealer hand > player hand
      F. Player hand > dealer hand
      */
      if ((dealerTotal > 21) && (playerTotal > 21)) {            // A
        System.out.println("*** Push, both busted. ***");
      } else if (playerTotal == dealerTotal) {                   // B
        System.out.println("*** Push! ***");
      } else if ((dealerTotal > 21) && (playerTotal <=21)) {     // C
        System.out.println("*** You win, dealer busted. ***");
        playerWinCount += 1;
      } else if ((dealerTotal <= 21) && (playerTotal > 21)) {    // D
        System.out.println("*** You busted, dealer wins. ***");
        dealerWinCount += 1;      
      } else if (dealerTotal > playerTotal) {                    // E
        System.out.println("*** Dealer wins: " + dealerTotal + " vs. " + playerTotal + ". ***");
        dealerWinCount += 1;
      } else {                                                   // F
        System.out.println("*** You win: " + playerTotal + " vs. " + dealerTotal + ". ***");
        playerWinCount += 1;
      }
      System.out.println();
      System.out.print("Would you like to play again (y/n)? ");
      userInput = scnr.next().charAt(0);
      playAgain = (userInput == 'y');
    }
    // summarize games played
    if (dealerWinCount > playerWinCount) {
      System.out.println("Sorry, dealer beat you " + dealerWinCount + " games to " + playerWinCount + ".");
    } else if (playerWinCount == dealerWinCount) {
      System.out.println("You won as many games (" + playerWinCount + ") as the dealer.");
    } else {
      System.out.println("Congratulations, you beat the dealer " + playerWinCount + " games to " + dealerWinCount + ".");
    }
  }
}