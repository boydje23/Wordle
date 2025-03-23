import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Wordle
{
    public static void matchDlewor(String targetWord, ArrayList<String> targetWords)
    {
        Scanner scnr = new Scanner(System.in);
        int [] numberColors = new int [5];
        int attempt = 1;
        String[] arrGuess = new String[5];
        String[] arrTarget = new String[5];
        for (attempt = 1; attempt <= 6; attempt++)
        {
            System.out.print("Enter Word (" + attempt + "):  ");
            String guess = scnr.next();
            while(!(targetWords.contains(guess)))
            {
                System.out.println("Invalid Input");
                System.out.print("Enter Word (" + attempt + "):  ");
                guess = scnr.next();
            }
            if (targetWords.contains(guess))
            {
                arrGuess = guess.split("");
                arrTarget = targetWord.split("");
                for (int i = 0; i < numberColors.length; i++) {
                    if (arrGuess[i].equals(arrTarget[i])) {
                        numberColors[i] = 1;
                    } else if (arrGuess[i].equals(arrTarget[0]) || arrGuess[i].equals(arrTarget[1]) || arrGuess[i].equals(arrTarget[2]) || arrGuess[i].equals(arrTarget[3]) || arrGuess[i].equals(arrTarget[4])) {
                        numberColors[i] = 2;
                    } else if (!(arrGuess[i].contains(arrTarget[i]))) {
                        numberColors[i] = 3;
                    }
                }
                for(int i = 0; i <  numberColors.length; i++) {
                    if (numberColors[i] == 1) {
                        System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK + arrGuess[i]);
                        System.out.print(ANSI_RESET);
                    }
                    else if (numberColors[i] == 2) {
                        System.out.print(ANSI_YELLOW_BACKGROUND  + ANSI_BLACK + arrGuess[i]);
                        System.out.print(ANSI_RESET);
                    }
                    else if (numberColors[i] == 3) {
                        System.out.print(ANSI_WHITE_BACKGROUND  + ANSI_BLACK + arrGuess[i]);
                        System.out.print(ANSI_RESET);
                    }
                }
                System.out.println(" ");
            }
            if(numberColors[0] == 1 && numberColors[1] == 1 && numberColors[2] == 1 && numberColors[3] == 1 && numberColors[4] == 1 ) {
                System.out.println("You guessed the word in " + attempt + " attempts. nice Job");
                attempt = 100;
            }
        }
    }


    // constants to allow colored text and backgrounds
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) throws IOException {
        // print welcome message
        System.out.println("Welcome to Dlewor(TM)");

        ArrayList<String> targetWords = new ArrayList<String>();
        // open file for reading
        FileReader f = new FileReader(args[0]);
        // create Scanner object for reading
        Scanner in = new Scanner(f);

        // read in text from file, one word at a time
        while (in.hasNext())
        {
            String line = in.next();
            // write message to the file
            if(line.length() == 5)
            {
                targetWords.add(line);
            }
        }
        // close input file
        f.close();

        Random rand = new Random();

        int rand_int = rand.nextInt(targetWords.size());

        String targetWord = targetWords.get(rand_int);

        System.out.println(" ");

        matchDlewor(targetWord, targetWords);

        System.out.println(targetWord);


    }
}
