// Matthew Clark
// CS210 Building Java Programs
// Assigment 4 & 5 - Hangman

    import java.awt.*;
    import java.io.*;
    import java.util.*;
    import java.util.List;
    import javax.swing.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;


    public class Hangman1 {
        static String[] wordList;
        static String secretWord;
        static Set<Character> alphabet;
        static Set<Character> lettersGuessed;    // letters the user has guessed
        static boolean[] lettersRevealed;       // determines if the letter should be revealed or not
        static int guessesRemaining;



        public static void main(String[] args){
            Hangman1 hangman = new Hangman1();
            hangman.createAlphabetSet();
            hangman.readFile("words.txt");

            HangmanGUI.buildGUI();
            setUpGame();
        }



        // checkIfWon - sees if the user has won the game
        static boolean checkIfWon(){
            for(boolean isLetterShown : lettersRevealed){
                if(!isLetterShown)
                    return false;
            }
            return true;
        }

        // checkUserGuess - get input from the user
        static boolean checkUserGuess(String l){
            if(l.length() == 1 && !lettersGuessed.contains(l.charAt(0)) && alphabet.contains(l.charAt(0))) {
                HangmanGUI.setText(null);
                lettersGuessed.add(l.charAt(0));
                return true;
            }else{
                Toolkit.getDefaultToolkit().beep();
            }
            return false;
        }

        // chooseSecretWord - selects a word
        private static String chooseSecretWord(String[] wordList){
            return wordList[(int)(Math.random() * wordList.length)];
        }

        // createAlphabetSet - Creates the alphabet set that's used to ensure that the user's guess not a number nor a special character
        private void createAlphabetSet(){
            alphabet = new HashSet<Character>(26);
            for(Character c = 'a'; c<='z'; c++){
                alphabet.add(c);
            }
        }

        // loseSequence - when the the user runs out of guesses
        static void loseSequence(){
            for(int i = 0; i < lettersRevealed.length; i++){
                lettersRevealed[i] = true;
            }
            HangmanGUI.drawSecretWord();
            playAgain("Tough luck. The secret word was " + secretWord + ".\nWould you like to play another game of hangman?");
        }

        // playAgain - Allows the user to play another game of hangman
        private static void playAgain(String message){
            int ans = HangmanGUI.playAgainDialog(message);
            if(ans == JOptionPane.YES_OPTION){
                setUpGame();
            }else{
                System.exit(0);
            }
        }

        // readFile - read in wordList
        private String[] readFile(String loc){
            BufferedReader input = null;
            try{
                input = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(loc)));
                wordList = input.readLine().split(" ");
            }catch(IOException ioException) {
                ioException.printStackTrace();
            }finally{
                try {
                    if (input != null) input.close();
                }catch(IOException ioEx){
                    ioEx.printStackTrace();
                }
            }
            return wordList;
        }

        // setUpGame - sets up the variables appropriately
        private static void setUpGame(){
            guessesRemaining = 5;
            secretWord = chooseSecretWord(wordList);
            lettersRevealed = new boolean[secretWord.length()];
            Arrays.fill(lettersRevealed, false);
            lettersGuessed = new HashSet<Character>(26);     // 26 letters in alphabet

            HangmanGUI.drawSecretWord();
            HangmanGUI.drawLettersGuessed();
            HangmanGUI.drawGuessesRemaining();
        }

        // updateSecretWord - updates which letters of the secret word have been revealed
        static void updateSecretWord(String l){
            List<Integer> changeBool = new ArrayList<Integer>();

            if(secretWord.contains(l)){
                // Searches through secretWord & notes down all letters that equal the user's guess
                for(int i=0; i<secretWord.length(); i++){
                    if(secretWord.charAt(i) == l.charAt(0))
                        changeBool.add(i);
                }

                // Changes the boolean value for those letters @ their corresponding indexes
                for(Integer idx : changeBool)
                    lettersRevealed[idx] = true;
            }else{
                guessesRemaining--;
                HangmanGUI.drawGuessesRemaining();
            }
        }

        // winSequence - when the user has correctly guessed the secret word
        static void winSequence(){
            playAgain("Well done! You guessed " + secretWord + " with " + guessesRemaining + " guesses left!\n" +
                    "Would you like to play another game of hangman?");
        }

    }


    class HangmanGUI {

        // GUI
        static JFrame frame;
        static JTextField textField;
        static JLabel guessesRemainingLabel;
        static JLabel lettersGuessedLabel;
        static JLabel secretWordLabel;

        // buildGUI - builds the GUI
        static void buildGUI(){
            SwingUtilities.invokeLater(
                    new Runnable(){
                        @Override
                        public void run(){
                            frame = new JFrame("Hangman");

                            // JLabels
                            guessesRemainingLabel = new JLabel("Guesses remaining: " + String.valueOf(Hangman1.guessesRemaining));
                            lettersGuessedLabel = new JLabel("Already guessed: ");
                            secretWordLabel = new JLabel();

                            // TextField & checkButton
                            textField = new JTextField();
                            JButton checkButton = new JButton("Guess");
                            GuessListener guessListener = new GuessListener();
                            checkButton.addActionListener(guessListener);
                            textField.addActionListener(guessListener);

                            // Panel for all the labels
                            JPanel labelPanel = new JPanel();
                            labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
                            labelPanel.add(guessesRemainingLabel);
                            labelPanel.add(lettersGuessedLabel);
                            labelPanel.add(secretWordLabel);

                            // User panel
                            JPanel userPanel = new JPanel(new BorderLayout());
                            userPanel.add(BorderLayout.CENTER, textField);
                            userPanel.add(BorderLayout.EAST, checkButton);
                            labelPanel.add(userPanel);

                            // Add everything to frame
                            frame.add(BorderLayout.CENTER, labelPanel);

                            frame.setSize(250, 100);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            frame.setVisible(true);
                        }
                    }
            );
        }

        // drawGuessesRemaining - Outputs the guesses remaining
        static void drawGuessesRemaining(){
            final String guessesMessage = "Guesses remaining: " + String.valueOf(Hangman1.guessesRemaining);
            SwingUtilities.invokeLater(
                    new Runnable(){
                        @Override
                        public void run(){
                            guessesRemainingLabel.setText(guessesMessage);
                            guessesRemainingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                        }
                    }
            );
        }

        // drawLettersGuessed - Outputs the letters guessed
        static void drawLettersGuessed(){
            StringBuilder lettersBuilder = new StringBuilder();
            for (Character el : Hangman1.lettersGuessed) {
                String s = el + " ";
                lettersBuilder.append(s);
            }

            final String letters = lettersBuilder.toString();
            SwingUtilities.invokeLater(
                    new Runnable() {
                        @Override
                        public void run() {
                            lettersGuessedLabel.setText("Already guessed: " + letters);
                        }
                    }
            );
        }

        // drawSecretWord - draws the secret word with dashes & etc for user to use to guess the word with
        static void drawSecretWord(){
            StringBuilder word = new StringBuilder();
            for(int i=0; i<Hangman1.lettersRevealed.length; i++){

                if (Hangman1.lettersRevealed[i]) {
                    String s = Hangman1.secretWord.charAt(i) + " ";
                    word.append(s);
                } else {
                    word.append("_ ");
                }
            }

            final String w = word.toString();
            SwingUtilities.invokeLater(
                    new Runnable(){
                        @Override
                        public void run() {
                            secretWordLabel.setText(w);
                        }
                    }
            );
        }

        //playAgainDialog - shows the confirm w
        static int playAgainDialog(String m){
            return JOptionPane.showConfirmDialog(frame, m, "Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        }


        // GETTERS
        private static String getText(){
            return textField.getText();
        }

        // SETTERS
        static void setText(final String t){
            SwingUtilities.invokeLater(
                    new Runnable() {
                        @Override
                        public void run() {
                            textField.setText(t);
                        }
                    }
            );
        }


        // ActionListener
        private static class GuessListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent ev){
                String guess = getText();

                if(Hangman1.checkUserGuess(guess)) {
                    Hangman1.updateSecretWord(guess);
                    drawSecretWord();

                    if(Hangman1.lettersGuessed.size() != 0)      // No letters have been guessed by the user at the beginning
                        drawLettersGuessed();

                    // Checks if the user has won or lost
                    if (Hangman1.checkIfWon())
                        Hangman1.winSequence();
                    else if (Hangman1.guessesRemaining == 0)
                        Hangman1.loseSequence();
                }
            }
        }
    }
