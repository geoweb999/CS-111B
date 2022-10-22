import java.util.Scanner;

public class Main {
  public static char getUserResponseToGuess(int guess) {
    // with a passed value for guess, prompt the user for (h)igher, (l)ower, or (c)orrect
    // and return guess via result (passed by reference)
    char userInput;
    boolean validInput = false;
    Scanner scnr = new Scanner(System.in);
    while (!validInput) {
      System.out.println("...is it? " + guess + " (h/l/c): ");
      userInput = scnr.next().charAt(0);
      if ( (userInput == 'h') || (userInput == 'l') || (userInput == 'c') ) {
        validInput = true;
      } else {
        System.out.println("Invalid input " + userInput + " please enter (h/l/c)");
      }
    }
    return userInput;
  }
    
  public static int getMidpoint(int low, int high) {
    // return mid-point between low and high. If there are two valid midpoints
    // e.g. low = 4 and high= 7, then chose the lower, eg. 5 not 6
    return (low + high) / 2;
  }

  public static boolean shouldPlayAgain() {
    return false;
  }

  public static void playOneGame() {
    // this function controls the main game flow
    // game ends when computer guesses correct number
    //
    boolean gameOver = false;
    char userInput;
    int newGuess;
    int low = 1;
    int high = 100; 
  
    System.out.println("Think of a number between 1 and 100.");
  
    while (!gameOver) {
      // loop until correct answer
      // make a guess in between the current high and low values
      // if guess is 'h', adjust lower boundry to next highest integer
      // if guess is 'l', adjust higher boundry to next lowest integer
      // ASSUMPTION: human player is honest
      newGuess = getMidpoint(low, high);
      userInput = getUserResponseToGuess(newGuess);
      if (userInput == 'h') {
        low = newGuess + 1;
      } else if (userInput == 'l') {
        high = newGuess - 1;
      } else if (userInput == 'c') {
        gameOver = true;
       }  else {
          // this branch should never execute
        System.out.println("Something unexpected happened.");
        gameOver = true;
       }
     }
     return;  
  }
  public static void main(String[] args) {
    boolean playAgain = true;
    while (playAgain) {
      playOneGame();
      playAgain = shouldPlayAgain();
    }
  }
}
