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
  int total;                   // sum of all cards drawn for current hand
  char userInput;              // temp variable used to store user inputs
  boolean playAgain = true;    // set to true to if user wants to play a hand
  boolean anotherCard = false; // set to true if user wants another card

  Random rand = new Random();
  Scanner scnr = new Scanner(System.in);
    
  // always play at least one hand
  while (playAgain) {
    // generate first 2 cards and display hand to user

    card1 = rand.nextInt(10) + 1;
    card2 = rand.nextInt(10) + 1;
    total = card1 + card2;
    System.out.println("First cards: " + card1 + ", " + card2 );
    System.out.println("Total: " + total);

    // ask user about adding another card
    // only check affirmative case as all other entries imply 'n'
    System.out.print("Do you want another card (y/n)? ");
    userInput = scnr.next().charAt(0);
    anotherCard = (userInput == 'y');
    
    while (anotherCard) {
      // draw another card
      nextCard = rand.nextInt(10) + 1;
      total += nextCard;
      // output new total
      System.out.println("Card: " + nextCard);
      System.out.println("Total: " + total);
      if (total > 21) {
        System.out.println("Bust.");
        anotherCard = false;  // end while loop and dont ask for another card
      } else if (total == 21) {
        System.out.println("Congratulations!"); // end while loop and dont ask for another card
        anotherCard = false;
      } else {
        System.out.print("Do you want another card (y/n)? ");
        userInput = scnr.next().charAt(0);
        anotherCard = (userInput == 'y');
      }
    }
    System.out.print("Would you like to play again (y/n)? ");
    userInput = scnr.next().charAt(0);
    playAgain = (userInput == 'y');
  }
}
}