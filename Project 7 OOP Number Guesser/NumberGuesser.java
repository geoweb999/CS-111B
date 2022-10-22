public class NumberGuesser {
    private int high;           // current upper bound for guessing
    private int low;            // current lower bound for guessing
    private int guess;          // current guess
    private int initHigh;       // initial upper bound, used to reset object to initial conditions
    private int initLow;        // initial lower bound, used to reset object to initial conditions

    public NumberGuesser(int lowerBound, int upperBound) {
        // constructor method, initialize high and low and set initial conditions
        high = upperBound;
        initHigh = upperBound;
        low = lowerBound;
        initLow = lowerBound;
        guess = getCurrentGuess();
    }
    public void higher() {
        // user has indicated their number is higher, so reset lower bound to current guess + 1
        // and update current guess
        low = guess + 1;
        guess = getCurrentGuess();
    }
    public void lower() {
        // user has indicated their number is lower, so reset upper bound to guess - 1
        // and update current guess
        high = guess - 1;
        guess = getCurrentGuess();
    }
    public int getCurrentGuess() {
        // returns the midpoint of low and high (using integer math)
        return (low + high) / 2;
    }
    public void reset() {
        // reset values to initialization 
        high = initHigh;
        low = initLow;
        guess = getCurrentGuess();
    }
}