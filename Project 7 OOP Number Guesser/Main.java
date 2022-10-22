import java.util.Scanner;

public class Main {
  // The program will figure out a number chosen by a human user. The human user
  // will think of a number from 1 to 100. The program will make guesses and 
  //the user will tell the program to guess higher or lower.
  
  public static final int UPPER = 100;  // sets the upper bound of the range for guesses
  public static final int LOWER = 1;    // sets the lower bound for range
  
    public static char getUserResponseToGuess(NumberGuesser guess) {
        // with a passed value for guess, prompt the user for (h)igher, (l)ower, or (c)orrect
        // and return the user's response
        String userInputRaw;
        char inChar = ' ';
        boolean validInput = false;
        Scanner scnr = new Scanner(System.in);
        while (!validInput) {
            System.out.print("...is it? " + guess.getCurrentGuess() + " (h/l/c): ");
            userInputRaw = scnr.nextLine();
            inChar = userInputRaw.charAt(0);
            if ( (inChar == 'h') || (inChar == 'l') || (inChar == 'c') ) {
                validInput = true;
            } else {
                System.out.println("Invalid input " + inChar + " please enter (h/l/c)");
            }
        }
        return inChar;
    }
    
    public static boolean shouldPlayAgain() {
        // prompt the user to play another game, check for only valid responses
        // return true if user enters 'y' and false if 'n'
        String userInputRaw;
        char inChar = ' ';
        boolean validInput = false;
        Scanner scnr = new Scanner(System.in);
        while (!validInput) {
            System.out.print("Would you like to play again? (y/n): ");
            userInputRaw = scnr.nextLine();
            inChar = userInputRaw.charAt(0);
            if ( (inChar == 'y') || (inChar == 'n') ) {
                validInput = true;
            } else {
                System.out.println("Invalid input " + inChar + " please enter (y/n)");
            }
        }
        return (inChar == 'y');
    }

    public static void playOneGame(NumberGuesser guesser) {
        // this method controls the main game flow
        //
        boolean gameOver = false;
        char userInput;
 
        System.out.println("Think of a number between " + LOWER + " and " + UPPER + ".");
  
        while (!gameOver) {
            // loop until correct answer
            // make a guess in between the current high and low values
            // if guess is 'h', adjust lower boundry to next highest integer
            // if guess is 'l', adjust higher boundry to next lowest integer
            // ASSUMPTION: human player is honest    
            userInput = getUserResponseToGuess(guesser);
            if (userInput == 'h') {
                guesser.higher();
            } else if (userInput == 'l') {
                guesser.lower();
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
        // controls flow of game
        // create a new guesser object and play game
        NumberGuesser guesser = new NumberGuesser(LOWER, UPPER);
        boolean playAgain = true;
        while (playAgain) {
            playOneGame(guesser);             // plays one game of guess, guesser object tracks detyails
            playAgain = shouldPlayAgain();    // ask user if they want another game
            guesser.reset();                  // reset guesser to initial state
        }
    }

}
