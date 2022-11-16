
/**
 * Fork this repl to use as the starting point
 * for your project
 *
 * You will be modifying the actionPerformed method
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

class Main extends JFrame implements ActionListener {

  private JLabel guessLabel;
  private JTextField guessField;
  private JButton higherButton;
  private JButton lowerButton;
  private JButton correctButton;
  private JButton resetButton;

  private NumberGuesser guesser;

  public Main() {
    // Used to specify GUI component layout
    GridBagConstraints positionConst = null;

    // Set frame's title
    setTitle("Number Guesser");

    // Create the display for the guess
    guessLabel = new JLabel("Is it?");

    guessField = new JTextField(15);
    guessField.setEditable(false);
    guessField.setText("0");

    // Create the buttons Lower, Higher, Corect, Restet
    lowerButton = new JButton("Lower");
    higherButton = new JButton("Higher");
    correctButton = new JButton("Correct");
    resetButton = new JButton("Reset");

    // Use "this" class to handle button presses
    lowerButton.addActionListener(this);
    higherButton.addActionListener(this);
    correctButton.addActionListener(this);
    resetButton.addActionListener(this);

    // Use a GridBagLayout
    setLayout(new GridBagLayout());
    positionConst = new GridBagConstraints();

    // 10 pixels vert, 5 horizontal around components
    positionConst.insets = new Insets(10, 5, 10, 5);

    // Add component using the specified constraints
    positionConst.gridx = 0;
    positionConst.gridy = 0;
    add(guessLabel, positionConst);

    positionConst.gridx = 1;
    positionConst.gridy = 0;
    add(guessField, positionConst);

    positionConst.gridx = 2;
    positionConst.gridy = 0;
    add(resetButton, positionConst);

    positionConst.gridx = 0;
    positionConst.gridy = 2;
    add(lowerButton, positionConst);

    positionConst.gridx = 1;
    positionConst.gridy = 2;
    add(higherButton, positionConst);

    positionConst.gridx = 2;
    positionConst.gridy = 2;
    add(correctButton, positionConst);

    // Get Ready to play the game
    this.guesser = new RandomNumberGuesser(1, 100);
    this.guessField.setText("" + guesser.getCurrentGuess());
  }

  /*
   * Method is automatically called when a button is pressed
   * 
   * It needs some work. The logic here doesn't play a guessing game. It just
   * provides some examples that you can use.
   */
  @Override
  public void actionPerformed(ActionEvent event) {

    // buttonPressed will be initialized with a
    // reference to the button object that was pressed.
    // One of the four buttons: Higher, Lower, Correct, Reset
    Object buttonPressed = event.getSource();

    // You can compare the buttonPressed reference to member fields
    // that refer to your four buttons. Here we check for the
    // lower button
    if (buttonPressed == this.lowerButton) {
      guessField.setText("lower!");
    }

    // Notice that right now the buttons write strings into the
    // guess Field. That isn't very useful. Instead you should
    // use a number guesser to put the next guess in.
    if (buttonPressed == this.higherButton) {
      guessField.setText("higher!");
    }

    // for correct and reset, no need for a try/catch
    if (buttonPressed == this.correctButton) {
      guessField.setText("correct!");
    }

    // Here is the reset button. Right now it shows a
    // JOptionPane. That isn't right. It should just reset
    // the guesser and put a first guess into the textField.
    //
    // But.. You can use similar JOptionPane when you catch the user
    // cheating
    if (buttonPressed == this.resetButton) {
      JOptionPane.showInternalMessageDialog(null, "Reset!", "Notice", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  public static void main(String[] args) {
    Main myFrame = new Main();

    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myFrame.pack();
    myFrame.setVisible(true);
  }
}