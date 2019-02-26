// Matthew Clark
// CS210 Building Java Programs
// Assigment 4 & 5 - Hangman

import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class HangmanApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to hangman! I will pick a word and you will"
                + "try to guess it character by character."
                + "If you guess wrong 6 times, then you lose. "
                + "If you can guess it before then, you win. "
                + "Are you ready? I hope so because i am.");
        System.out.println();
        System.out.println("I have picked my word. Below is a picture, and below"
                + "that is your current guess, which starts off as nothing. \n Every time you "
                + "guess incorrectly. I add a body part to the picture. \n When there is a full"
                + " person, you lose");
        
        // Allows for multiple games
        boolean doYouWantToPlay = true;
        while (doYouWantToPlay) {
            // Setting up the game
            System.out.println();
            System.out.println("Alright! Let's play!");
            Hangman game = new Hangman();
            do {
                // Draw the things...
                System.out.println();
                System.out.println(game.drawPicture());
                System.out.println();
                System.out.println(game.getFormalCurrentGuess());
                System.out.println(game.mysteryWord);
                System.out.println();
                
                // Get the guess
                System.out.println("Enter a character that you think is in the word");
                char guess = (sc.next().toLowerCase().charAt(0));
                System.out.println();
                
                // check if the character is guessed already
                while (game.isGuessedAlready(guess)) {
                    System.out.println("Try again! You've already guessed that character.");
                    guess = (sc.next().toLowerCase()).charAt(guess);
                }
                
                if (game.playGuess(guess)) {
                    System.out.println("Great guess! That character is in the word!");
                } else {
                    System.out.println("Unfortunately that character isn't in the word");
                }
                
                // Play the game
            }
            while (!game.gameOver()); // Keep playing until the game is over.
            
            
            // Play again or no?
            System.out.println();
            System.out.println("Do you want to play another game? Enter Y if you do.");
            Character response = (sc.next().toUpperCase()).charAt(0);
            doYouWantToPlay = (response == 'Y');
        }
    }
}


class Hangman {
    
    String mysteryWord;
    StringBuilder currentGuess;
    ArrayList<Character> previousGuesses = new ArrayList<>();
    
    int maxTries = 6;
    int currentTry = 0;
    
    ArrayList<String> dictionary = new ArrayList<>();
    private static FileReader fileReader;
    private static BufferedReader bufferedFileReader;
    
    public Hangman() throws IOException {
        initializeStreams();
        mysteryWord = pickWord();
        currentGuess = initializeCurrentGuess();
    }
    
    public void initializeStreams() throws IOException{
        try {
            File inFile = new File("dictionary.txt");
            fileReader = new FileReader(inFile);
            bufferedFileReader = new BufferedReader(fileReader);
            String currentLine = bufferedFileReader.readLine();
            while (currentLine != null) {
                dictionary.add(currentLine);
                currentLine = bufferedFileReader.readLine();
            }
            bufferedFileReader.close();
            fileReader.close();
        } catch(IOException e) {
            System.out.println("Could not init streams");
    }
    }
    
    public String pickWord() {
        Random rand = new Random();
        int wordIndex = Math.abs(rand.nextInt()) % dictionary.size();
        return dictionary.get(wordIndex);
    }
    
    // _ A _ _ _ _
    
    public StringBuilder initializeCurrentGuess() {
        StringBuilder current = new StringBuilder();
        for(int i = 0; i < mysteryWord.length() * 2; i++) {
            if (i % 2 == 0) {
                current.append("_");
            } else {
                current.append(" ");
            }
        }
        return current;
    }
    
    public boolean gameOver() {
        if (didWeWin()) {
            System.out.println();
            System.out.println("Congrats! You won! You guessed the right word!");
            return true;
        } else if (didWeLose()) {
            System.out.println();
            System.out.println("Sorry, you lost. You spent all of your six tries"
                + "The word was " + mysteryWord + ".");
            return true;
        }
        return false;
    }
    
    public boolean didWeLose() {
        return currentTry >= maxTries;
    }
    
    public boolean didWeWin() {
        String guess = getCondensedCurrentGuess();
        return guess.equals(mysteryWord);
    }
    
    public String getCondensedCurrentGuess() {
        String guess = currentGuess.toString();
        return guess.replace(" ", "");
    }
    
    public boolean isGuessedAlready(char guess) {
        return previousGuesses.contains(guess);
    }
    
    public boolean playGuess(char guess) {
        boolean isItAGoodGuess = false;
        previousGuesses.add(guess);        
        for (int i = 0; i < mysteryWord.length(); i++) {
            if (mysteryWord.charAt(i) == guess) {
                currentGuess.setCharAt(i * 2, guess);
                isItAGoodGuess = true;
            }
        }
        
        if (!isItAGoodGuess) {
            currentTry++;
        }
        
        return isItAGoodGuess;
    }
    
    // _ A _ _ _ B _
    
    public String getFormalCurrentGuess() {
        return "Current Guess: " + currentGuess.toString();
    }
    
              // " - - - - -\n"+
              // "|        |\n"+
              // "|        O\n" +
              // "|      / | \\ \n"+
              // "|        |\n" +
              // "|       / \\ \n" +
              // "|\n" +
              // "|\n";     
    
    public String drawPicture() {
        switch(currentTry) {
            case 0: return noPersonDraw();
            case 1: return addHeadDraw();
            case 2: return addBodyDraw();
            case 3: return addOneArmDraw();
            case 4: return addSecondArmDraw();
            case 5: return addFirstleg();
            default: return fullPersonDraw();
        }
    }

    private String noPersonDraw() {
       return " - - - - -\n"+
              "|        |\n"+
              "|         \n" +
              "|         \n"+
              "|         \n" +
              "|         \n" +
              "|\n" +
              "|\n";
    }

    private String addHeadDraw() {
       return " - - - - -\n"+
              "|        |\n"+
              "|        0\n" +
              "|         \n"+
              "|         \n" +
              "|         \n" +
              "|\n" +
              "|\n";
    }

    private String addBodyDraw() {
       return " - - - - -\n"+
              "|        |\n"+
              "|        0\n" +
              "|        |\n"+
              "|        |\n" +
              "|         \n" +
              "|\n" +
              "|\n";
    }

    private String addOneArmDraw() {
       return " - - - - -\n"+
              "|        |\n"+
              "|        0\n" +
              "|      / |\n"+
              "|        |\n" +
              "|         \n" +
              "|\n" +
              "|\n";
    }

    private String addSecondArmDraw() {
       return " - - - - -\n"+
              "|        | \n"+
              "|        0 \n" +
              "|      / | \\ \n"+
              "|        | \n" +
              "|          \n" +
              "|\n" +
              "|\n";
    }
    
    private String addFirstleg() {
       return " - - - - -\n"+
              "|        |\n"+
              "|        O\n" +
              "|      / | \\ \n"+
              "|        |\n" +
              "|       / \n" +
              "|\n" +
              "|\n";   
    }
    
    private String fullPersonDraw() {
       return " - - - - -\n"+
              "|        |\n"+
              "|        O\n" +
              "|      / | \\ \n"+
              "|        |\n" +
              "|       / \\ \n" +
              "|\n" +
              "|\n";    
    }
    
    
}

