import java.util.Scanner;
import java.util.Random;

class Main {
  public static void main(String[] args) {
// Write a command line game that plays a simple version of blackjack.
// The program should generate a random number between 1 and 10 each time the player gets a card.
// It should keep a running total of the player's cards,
// and ask the player whether or not it should deal another card.
// If the player hits 21 exactly, the program should print "Congratulations!"
// and then ask if the player wants to play again.
// If the player exceeds 21, the program should print "Bust" and then ask if the player wants to play again.

  int card1;                   // stores first card drawn
  int card2;                   // stores second card drawn
  int nextCard;                // stores card drawn after 1st 2 cards
  int dealerCard1;             // stores dealer drawn first card
  int dealerCard2;             // stores dealer drawn second card (shown)
  int dealerNext;              // stores cards drawn by dealer
  int playerTotal;             // sum of all cards drawn for current player hand
  int dealerTotal;             // sum of all cards drawn for dealer hand
  char userInput;              // temp variable used to store user inputs
  boolean playAgain = true;    // set to true to if user wants to play a hand
  boolean anotherCard = false; // set to true if user wants another card
  int DEALER_STANDS = 17;      // dealer stands at this value and above

  Random rand = new Random();
  Scanner scnr = new Scanner(System.in);
    
  // always play at least one hand
  while (playAgain) {
    // generate first 2 hand and display cards to user

    card1 = rand.nextInt(10) + 1;
    dealerCard1 = rand.nextInt(10) + 1;
    card2 = rand.nextInt(10) + 1;
    dealerCard2 = rand.nextInt(10) + 1;
    playerTotal = card1 + card2;
    dealerTotal = dealerCard1 + dealerCard2;
    
    System.out.println("First cards: " + card1 + ", " + card2 );
    System.out.println("Total: " + playerTotal);
    System.out.println("Dealer shows: " + dealerCard2);
    
    // ask user about adding another card
    // only check affirmative case as all other entries imply 'n'
    System.out.print("Do you want another card (y/n)? ");
    userInput = scnr.next().charAt(0);
    anotherCard = (userInput == 'y');
    
    while (anotherCard) {
      // draw another card
      nextCard = rand.nextInt(10) + 1;
      playerTotal += nextCard;
      // output new total
      System.out.println("Card: " + nextCard);
      System.out.println("Total: " + playerTotal);
      if (playerTotal > 21) {
        System.out.println("Bust.");
        anotherCard = false;  // end while loop and dont ask for another card
      } else if (playerTotal == 21) {
        System.out.println("Backjack!"); // end while loop and dont ask for another card
        anotherCard = false;
      } else {
        System.out.print("Do you want another card (y/n)? ");
        userInput = scnr.next().charAt(0);
        anotherCard = (userInput == 'y');
      }
    }
    System.out.println("Dealer shows: " + dealerCard1 + " " + dealerCard2);
    System.out.println("Total: " + dealerTotal);
    anotherCard = true;
    dealerTotal = dealerCard1 + dealerCard2;
    System.out.println(dealerTotal);
    if (dealerTotal >= 17); {
      anotherCard = false;
      System.out.println("Dealer stands on: " + dealerTotal);
    }
    while (anotherCard) {
      dealerNext = rand.nextInt(10) + 1;
      dealerTotal += dealerNext;
      System.out.println("Dealer draws: " + dealerNext );
      System.out.println("Total: " + dealerTotal);
      if (dealerTotal > 21) {
        System.out.println("Dealer busts!");
        anotherCard = false;
      } else if (dealerTotal == 21) {
        System.out.println("Dealer Blackjack!!");
        anotherCard = false;
      } else if (dealerTotal >= DEALER_STANDS) {
        System.out.println("Dealer stands.");
        anotherCard = false;
      } else {
        anotherCard = true;
      }
    }
    if ((dealerTotal > 21) && (playerTotal > 21)) {
      System.out.println("Push, both busted.");
    } else if (playerTotal == dealerTotal) {
      System.out.println("Push!");
    } else if ((dealerTotal > 21) && (playerTotal <=21)) {
      System.out.println("You win, dealer busted.");
    } else if ((dealerTotal <= 21) && (playerTotal > 21)) {
      System.out.println("You busted, dealer wins.");
    } else if (dealerTotal > playerTotal) {
      System.out.println("Dealer wins: " + dealerTotal + " vs. " + playerTotal);
    } else {
      System.out.println("You win: " + playerTotal + " vs. " + dealerTotal);
    }
    System.out.println();
    System.out.print("Would you like to play again (y/n)? ");
    userInput = scnr.next().charAt(0);
    playAgain = (userInput == 'y');
  }
}
}