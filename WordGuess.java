import java.util.Random;
import java.util.Scanner;
/**
 * Allows users to guess randomly picked words
 * @author mfrankel8
 * @version 1.0
 */
public class WordGuess {
    static final String[] CANDIDATES = {"cat", "dad", "dog", "mom", "rat"};
    /**
    * @param args Main arguments passed to the function
    */
    public static void main(String[] args) {
        String secretWord = args.length > 0
            ? CANDIDATES[Integer.parseInt(args[0])]
            : CANDIDATES[new Random().nextInt(CANDIDATES.length)];
        // Your code goes here:
        int wrongGuesses = 0;
        StringBuilder guessedLetters = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int wordLength = secretWord.length();
        boolean gotWord = false;
        for (int i = 0; i < wordLength; i++) {
            sb.append('_');
        }
        while (wrongGuesses < 6) {
            int guessesRemaining = 5 - wrongGuesses;
            if (guessesRemaining != 0 && !gotWord) {
                System.out.println("Missed letters ("
                        + guessesRemaining + " left): " + guessedLetters);
                System.out.println("Current guess: " + sb);
            } else {
                System.out.println("Missed letters: " + guessedLetters);
                System.out.println("Final guess: " + sb);
                if (gotWord) {
                    System.out.print("Congratulations! You got it!");
                } else {
                    String txt = "Sorry, too many misses. The secret word was ";
                    System.out.print(txt + secretWord);
                }
                break;
            }
            System.out.print("Guess a letter: ");
            char userInput = scanner.nextLine().charAt(0);
            System.out.println("");
            int guessIndex = secretWord.indexOf(userInput);
            if (guessIndex != -1) {
                while (guessIndex != -1) {
                    sb.setCharAt(guessIndex, userInput);
                    guessIndex = secretWord.indexOf(userInput, guessIndex + 1);
                }
                if (sb.toString().equals(secretWord)) {
                    gotWord = true;
                }
            } else {
                guessedLetters.append(userInput);
                wrongGuesses++;
            }
        }
    }
}