import java.util.Scanner;

class Main {
  public static void main(String[] args) {

  final int HAND_SIZE = 5;  // maximum nuber of cards drawn
  final int DECK_SIZE = 10; // set the capacitt of the array used to count cards
  int[] hand = new int[HAND_SIZE];      // array to store cards drawn
  int[] deck = new int[DECK_SIZE];      // deck to track quantity of each card drawn
  int i;                    // used for for loops
  boolean pair = false;        // set to true if a pair is found (deck[i]==2)
  int lowPair = 0;          // tracks the low pair in a 2 pair hand
  int highPair = 0;         // track the high pair in a 2 pair hand
  int pairCount = 0;        // count the number of pairs found
  boolean twoPairs = false;    // set to true if two pairs are found
  boolean three = false;       // set to true if 3 of a kind is found (deck[i]==3)
  int threeCard = 0;        // set to the value of the card in a three of a kind
  boolean straight = false;    // set to true if a straight is found
  boolean fullHouse = false;   // set to true if a full house is found (1 pair + 3 of a kind)
  boolean four = false;        // set to true if four of a kind is found (deck[i]==4)
  int highCard = 0;         // tracks the highest card
  int straightCount = 0;    // true if 5 cards in sequence are found
  int card = 0;

  Scanner scnr = new Scanner(System.in);
  
  // initialize deck
  for (i = 0; i < DECK_SIZE; i++) {
    deck[i] = 0;
  }
  // draw 5 cards
  System.out.println("Enter five numeric cards, no face cards. Use 2 - 9.");
  for (i = 0; i < HAND_SIZE; i++) {
    System.out.print("Card " + (i + 1) + ": ");
    hand[i] = scnr.nextInt();
    deck[hand[i]]++;
  }
  for (i = 2; i < DECK_SIZE; i++) {
    switch (deck[i]) {
      case 0:
        straightCount = 0;  //this card value was not found so restart count of cards in sequence
        break;
      case 1:
        highCard = i;
        straightCount++;
        straight = (straightCount == 5); 
        break;
      case 2:
        pair = true;
        pairCount++;
        lowPair = (pairCount == 1) ? i : lowPair;
        highPair = (pairCount == 2) ? i : 0;
        break;
      case 3:
        three = true;
        threeCard = i;
        break;
      case 4:
        four = true;
        card = i;
        break;
      case 5:
        four = true;
        card = i;
        break;
    }
    twoPairs = (pairCount == 2);
    fullHouse = (pair && three);
  }
  if (four) {
    System.out.println("Four of a kind (" + card + ").");
  } else if (fullHouse) {
    System.out.println("Full house (" + threeCard + " over " + lowPair + ").");
  } else if (straight) {
    System.out.println("Straight.");
  } else if (three) {
    System.out.println("Three of a kind (" + card + ").");
  } else if (twoPairs) {
    System.out.println("Two pairs (" + lowPair + " & " + highPair + ").");
  } else if (pair) {
    System.out.println("Pair (" + lowPair + ").");
  } else {
    System.out.println("High card (" + highCard + ").");
  }
}
}