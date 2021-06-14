import java.lang.Math;
import java.util.ArrayList;

/**
 * Project 3.6.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 * After wathcing the memory strings appear in the buttons one at a time, the
 * player recreates the sequence from memory.
 */
public class MemoryGame
{
  private static ArrayList<String> allCharacters = new ArrayList<String>();

  // This will go through all ASCII values from 33 -> 126, which you can see the values in
  // characters here -> https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
  static {
    ArrayList<String> allChars = new ArrayList<String>();
    char start = '!'; // 33 (In Decimal)
    for (int i = 0; i < 93; i++) {
      char currChar = (char) (start + i); // Convert the decimal into a character
      allChars.add(String.valueOf(currChar)); // Convert the character to String
    }

    MemoryGame.allCharacters = allChars; // Sets the static variable to all the characters
  }

  public static void main(String[] args) {

    // Create the "memory strings" - an array of single character strings to
    // show in the buttons, one element at a time. This is the sequence
    // the player will have to remember.

    // Create the game and gameboard. Configure a randomized board with 3 buttons.
    // (Later, you can change options to configure more or less buttons
    // and turn randomization on or off.)

    // Play the game until user wants to quit.

    // Create a new array that will contain the randomly ordered memory strings.

    // Create a list of randomly ordered integers with no repeats, the length
    // of memory strings. Use it to create a random sequence of the memory strings.
    // - OR -
    // Overload the next method in RandomPermutation to create a random sequence
    // of the memory strings, passed as a parameter.

    // Play one sequence, delaying half a second for the strings to show
    // in the buttons. Save the player's guess.
    // (Later, you can speed up or slow down the game.)

    // Determine if player's guess matches all elements of the random sequence.

    // Cleanup the guess - remove commas and spaces. Refer to a new String method
    // replace to make this easy.

    // iterate to determine if guess matches sequence

    // If match, increase score, and signal a match, otherwise, try again.

    // Ask if user wants to play another round of the game
    // and track the number of games played.

    // When done playing, show score and end the game.

    MemoryGameGUI memoryBoard = new MemoryGameGUI(); // Create new board
    int difficulty = memoryBoard.difficulty(); // Custom function (In MemoryGameGUI.java)

    double delay = 0; // Initial delay

    // Switch case and changing delay depending on difficulty
    switch (difficulty) {
      case 0:
        delay = 1.0;
        break;
      case 1:
        delay = 0.8;
        break;
      case 2:
        delay = 0.6;
        break;
    }

    // Generate a sequence
    String[] currSequence = generateSequence(difficulty);

    // This just creates a string out of the string array so we can use later to match
    String combinedChars = new String();
    for (String character : currSequence) {
      combinedChars = combinedChars.concat(character);
    }

    // Initialize rounds and score
    int rounds = 0;
    int score = 0;

    // Create board based on sequence length
    memoryBoard.createBoard(currSequence.length, true);

    while (true) {
      // Add rounds every time we get to the top of the while loop
      ++rounds;
      // Get guess sequence from user
      String guessSequence = memoryBoard.playSequence(currSequence, delay);
      // If user guess right, then display matched window and add to score.
      if (guessSequence.equals(combinedChars)) {
        memoryBoard.matched();
        ++score;
        // If play again, then generate a new sequence (Same difficulty) or show score and quit
        boolean again = memoryBoard.playAgain();
        if (again) {
          currSequence = generateSequence(difficulty);
          continue;
        } else {
          memoryBoard.showScore(score, rounds);
          memoryBoard.quit();
          break;
        }
      } else {
        // Will display the try again window if you got the sequence wrong!
        memoryBoard.tryAgain();
        // If play again, then generate a new sequence (Same difficulty) or show score and quit
        boolean again = memoryBoard.playAgain();
        if (again) {
          continue;
        } else {
          memoryBoard.showScore(score, rounds);
          memoryBoard.quit();
          break;
        }
      }
    }
  }

  public static String[] generateSequence(int diff) {
    ArrayList<String> currSequence = new ArrayList<String>();
    int currSequenceLen = 0;

    switch (diff) {
      case 0: // Easy
        // Will make an ArrayList from random characters 4 times
        currSequenceLen = 4;
        for (int i = 0; i < currSequenceLen; ++i) {
          currSequence.add(allCharacters.get((int) (0 + Math.random() * allCharacters.size())));
        }
        break;
      case 1: // Medium
        // Will make an ArrayList from random characters 6 times
        currSequenceLen = 6;
        for (int i = 0; i < currSequenceLen; ++i) {
          currSequence.add(allCharacters.get((int) (0 + Math.random() * allCharacters.size())));
        }
        break;
      case 2: // Hard
        // Will make an ArrayList from random characters 8 times
        currSequenceLen = 8;
        for (int i = 0; i < currSequenceLen; ++i) {
          currSequence.add(allCharacters.get((int) (0 + Math.random() * allCharacters.size())));
        }
        break;
    }

    // Convert our ArrayList<String> to a String[]
    String[] arraySequence = new String[currSequenceLen];
    arraySequence = currSequence.toArray(arraySequence);

    return arraySequence;
  }
}