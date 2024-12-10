import java.io.*;
import java.util.*;
//https://www.w3schools.com/java/java_arraylist.asp -> array list
//https://www.w3schools.com/java/java_switch.asp -> switch & cases
/**
 * This class represents the logic behind Hangman
 *
 * @author Danny Jiang
 */
public class HangmanLogic {
    //
    private int coins;
    private int hints;
    private int powerups;
    private String guessesShown;
    private String word;
    private String userDifficulty;
    private int guessAmount;
    private List<String> incorrectList;
    //Statistics variables
    private int lettersGuessed;
    private int wordsGuessed;
    private int roundsWon;
    private int roundsLost;
    private int roundsPlayed;
    private int hintsUsed;
    private int powerUpsUsed;
    private int coinsSpent;
    private int leastLettersGuessed;

    /**
     * Easy Words Array
     */
    private String[] easyArray = {
            "cat", "wait", "pen", "hat", "code", "math", "swim", "pear", "fun", "play",
            "six", "key", "pig", "dog", "card", "easy", "hard", "soft", "pole", "flag",
            "kelp", "word", "apple", "blue", "ball", "good", "rain", "sand", "moon", "star",
            "bird", "frog", "fish", "lamp", "bike", "cake", "hand", "love", "wind", "snow",
            "star", "tree", "coin", "game", "chess", "golf", "jump", "game", "bead", "fish",
            "sock", "duck", "hill", "sock", "roof", "rock", "coat", "spoon", "beet", "pearl",
            "team", "book", "glove", "yarn", "nest", "leaf", "flame", "snow", "cloud", "wall"
    };
    /**
     * Medium Words Array
     */
    private String[] mediumArray = {
            "apple", "phone", "water", "computer", "mouse", "beach", "happy", "teacher",
            "science", "heard", "people", "brooklyn", "borrow", "money", "genre", "medium",
            "craft", "bottle", "paint", "school", "music", "squirrel", "robot", "planet",
            "guitar", "active", "simple", "tiger", "vivid", "major", "cherry", "winter",
            "dollar", "brick", "mango", "magic", "clean", "human", "search", "dance"
    };
    /**
     * Hard Words Array
     */
    private String[] hardArray = {
            "dialect", "language", "classroom", "microscopic", "greetings", "computer science",
            "elated", "difficulty", "hangman", "christmas", "thanksgiving", "abyssal",
            "harmonious", "innocuous", "adjective", "playground", "pseudo", "phenomenon",
            "paradox", "idiom", "established", "astronaut", "expression", "mysterious",
            "parliament", "universe", "psychology", "constitution", "celebrate",
            "extraordinary", "encyclopedia", "thunderstorm", "intelligence", "vegetable",
            "barometer", "galaxy", "mathematics", "philosophy", "chocolate", "treasure",
            "biological"
    };
    /**
     * Default Array
     */
    private String[] strArray = {};
    /**
     * Instantiates Hangman
     *
     * @param scan The User's choice for the difficulty of Hangman
     */
    public HangmanLogic(Scanner scan) {
        coins = 0; //Gives the stats vars a value so there's no error
        hints = 0;
        powerups = 0;
        lettersGuessed = 0;
        wordsGuessed = 0;
        roundsWon = 0;
        roundsLost = 0;
        roundsPlayed = 0;
        hintsUsed = 0;
        powerUpsUsed = 0;
        coinsSpent = 0;
        leastLettersGuessed = 999;
        getStats();
    }
    public HangmanLogic() {
        getStats();
    }
    public void start() {
        Scanner scan = new Scanner(System.in);
        HangmanDrawPicture drawer = new HangmanDrawPicture();
        playGame(scan, drawer);
    }

    /**
     * This is the method that runs the Game
     *
     * Main class
     *
     * @param scan Scanner object
     * @param drawer Draws the Hangman
     */
    public void playGame(Scanner scan, HangmanDrawPicture drawer) {
        //This is until user types 0 or (quit)
        while (true) {
            System.out.println();
            System.out.println("------------------Menu------------------");
            System.out.println("1.) Play Hangman");
            System.out.println("2.) Shop");
            System.out.println("3.) View Inventory");
            System.out.println("4.) View Statistics");
            System.out.println("0.) Quit");
            System.out.print("Enter your answer: ");
            int userInput = scan.nextInt();
            scan.nextLine();

            if (userInput == 1) {
                int rounds = 1;
                int currentGuessedLetters = 1;
                roundsPlayed++;
                System.out.print("Easy, Medium, or Hard?: ");
                userDifficulty = scan.nextLine();
                userDifficulty = userDifficulty.toUpperCase();

                while (!(userDifficulty.equals("EASY") || userDifficulty.equals("MEDIUM") || userDifficulty.equals("HARD"))) {
                    System.out.print("Invalid choice. Please enter Easy, Medium, or Hard: ");
                    userDifficulty = scan.nextLine().toUpperCase();
                }

                strArray = switch (userDifficulty.toUpperCase()) { //Chooses Array
                    case "EASY" -> easyArray;
                    case "MEDIUM" -> mediumArray;
                    case "HARD" -> hardArray;
                    default -> easyArray;
                };
                initialize();
                if (powerups > 0) { //Checks if the user has a power-up
                    System.out.print("Detected that you have 1 or more power-up. Would you like to use one? (Y/N): ");
                    String userPowerUpChoice = scan.nextLine();
                    if (userPowerUpChoice.equalsIgnoreCase("y")) {
                        powerUpsUsed++;
                        powerups--;
                        usePowerUp();
                    }
                }
                System.out.println("--------------------Round " + rounds + "--------------------");

                while (true) { //Ends only when the user GUESSES the word or when the user FAILS to guess the word
                    System.out.println(guessesShown);  // Shows the current guessed state
                    System.out.println("Guesses left: " + guessAmount);
                    System.out.print("Enter your guess: ");
                    String userGuess = scan.nextLine();
                    //Checks if the user types (hint) and sees if they have a hint
                    if (userGuess.equals("(hint)") && hints > 0) {
                        hints--;
                        hintsUsed++;
                        System.out.println("There is a '" + useHint() + "' in the word.");
                        System.out.print("Enter your guess: ");
                        userGuess = scan.nextLine();
                    }
                    //If the user types (hint) and they have no hints then the game tells them the following:
                    if (userGuess.equals("(hint)") && hints <= 0) {
                        System.out.println("\u001B[31mYou have no hints. Go to the shop to buy more.\u001B[0m");
                        System.out.print("Enter your guess: ");
                        userGuess = scan.nextLine();
                    }
                    //Checks if the user's guess is more than one letter. If it is, it tells them to try again.
                    if (userGuess.length() > 1) {
                        while (true) {
                            System.out.print("You can only guess 1 letter at a time. Try again: ");
                            userGuess = scan.nextLine();
                            if (userGuess.length() == 1) {
                                break;
                            }
                        }
                    }

                    //Checks if the guess is already made (inside of guess shown or incorrect list)
                    if (guessesShown.contains(userGuess) || incorrectList.contains(userGuess)) {
                        System.out.println("You already guessed that. Try again.");
                        continue;
                    }

                    clearConsole();
                    rounds++;
                    System.out.println("--------------------Round " + rounds + "--------------------");
                    boolean guessState = false;

                    //Checks if the user guess is in the word. If it is then the guessState is true and it doesn't penalizes
                    for (int i = 0; i < word.length(); i++) {
                        if (userGuess.equalsIgnoreCase(word.substring(i,i+1))) {
                            guessesShown = guessesShown.substring(0, i) + userGuess + guessesShown.substring(i + 1);
                            guessState = true;
                        }
                    }
                    //If the guessState is false then it subtracts a guess
                    if (!guessState) {
                        guessAmount--;
                        incorrectList.add(userGuess);
                    }

                    //Redraw hangman
                    drawer.draw(userDifficulty, guessAmount);  // Draw the hangman based on remaining guesses
                    System.out.println("Incorrect List: " + incorrectList);
                    //Checks if the user's guess is equal or less than 0 and if the guess Shown is not equal to the word (if this is true then they lose)
                    if (guessAmount <= 0 && !guessesShown.equalsIgnoreCase(word)) {
                        roundsLost++;
                        System.out.println("Word: " + word);
                        System.out.println("\u001B[31mYou lose. You ran out of guesses.\u001B[0m");
                        break;
                    }
                    //Checks if the user guessed the word with more than 1 guess
                    if (guessesShown.equalsIgnoreCase(word) && guessAmount > 0) {
                        if (userDifficulty.equalsIgnoreCase("HARD")) {
                            coins += 5;
                        } else if (userDifficulty.equalsIgnoreCase("MEDIUM")) {
                            coins += 3;
                        } else if (userDifficulty.equalsIgnoreCase("EASY")) {
                            coins += 1;
                        }
                        //Checks to see if the letters guessed this match is less than the least one
                        if (currentGuessedLetters < leastLettersGuessed) {
                            leastLettersGuessed = currentGuessedLetters;
                        }
                        roundsWon++;
                        wordsGuessed++;
                        System.out.println("Word: " + word);
                        System.out.println("\u001B[32mCongratulations! You win. \u001B[0m" + "You now have " + coins + " coins.");
                        saveStats();
                        break; //Exit the game loop
                    }
                    currentGuessedLetters++;
                    lettersGuessed++;
                }
            }
            else if (userInput == 2) {
                System.out.println();
                while (true) {
                    System.out.println();
                    System.out.println("------------------Shop------------------");
                    System.out.println("Your coins: " + coins);
                    System.out.println("1.) Buy Hint -> 1 Coins");
                    System.out.println("2.) Buy Powerup -> 3 Coins");
                    System.out.println("9.) View Description");
                    System.out.println("0.) Exit Shop");
                    System.out.print("Enter choice: ");
                    int userShopChoice = scan.nextInt();
                    if (userShopChoice == 1) {
                        System.out.println();
                        System.out.print("How much would you like to buy?: ");
                        int userHintBuy = scan.nextInt();
                        if (coins >= userHintBuy) {
                            coinsSpent += userHintBuy;
                            hints += userHintBuy;
                            coins -= userHintBuy;
                            System.out.println("\u001B[32mSuccessfully bought " + userHintBuy + " hints.\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mNot enough coins.\u001B[0m");
                        }
                    }
                    if (userShopChoice == 2) {
                        System.out.println();
                        System.out.print("How much would you like to buy?: ");
                        int userPowerUpBuy = scan.nextInt();
                        if (coins >= userPowerUpBuy * 3) {
                            coinsSpent += (userPowerUpBuy * 3);
                            powerups += userPowerUpBuy;
                            coins -= (3 * userPowerUpBuy);
                            System.out.println("\u001B[32mSuccessfully bought " + userPowerUpBuy + " hints.\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mNot enough coins.\u001B[0m");
                        }
                    }
                    if (userShopChoice == 9) {
                        System.out.println();
                        System.out.print("Which item would you like to view the description of?: ");
                        int userDescriptionChoice = scan.nextInt();
                        if (userDescriptionChoice == 1) {
                            System.out.println("You can use a hint anything during a match as long you still have one. You can use them as many times as you want and you can use them by typing (hint) into the input.");
                        } else if (userDescriptionChoice == 2) {
                            System.out.println("You can use this at the start before a round. This power-up fills in anywhere from 1 to half of the words depending on the length of it.");
                        }
                    }
                    if (userShopChoice == 0) {
                        saveStats();
                        clearConsole();
                        break;
                    }

                }
            }
            else if (userInput == 3) {
                System.out.println();
                System.out.println("********Inventory********");
                if (hints > 0) {
                    System.out.println("x" + hints + " Hints");
                } if (powerups > 0) {
                    System.out.println("x" + powerups + " Power-ups");
                }
                if (hints == 0 && powerups == 0) {
                    System.out.println("You own nothing.");
                }
                System.out.println("*************************");
            }
            else if (userInput == 4) {
                System.out.println();
                System.out.println("********Statistics********");
                stats();
                System.out.println("**************************");
            }
            else if (userInput == 0) {
                saveStats();
                break;
            } else {
                System.out.println("Invalid Option. Try again");
            }
        }
    }

    public void initialize() {
        // Chooses word list based on difficulty
        List<String> wordList = Arrays.asList(strArray);
        int random = (int) (Math.random() * wordList.size());
        word = wordList.get(random);

        // Sets the guess amount based on difficulty
        guessAmount = switch (userDifficulty.toUpperCase()) {
            case "EASY" -> 8;
            case "MEDIUM" -> 5;
            case "HARD" -> 3;
            default -> 8;
        };

        // Initialize the incorrect list and guesses shown
        incorrectList = new ArrayList<>();
        guessesShown = "＿".repeat(word.length());

        // It will space the word if it's a word with a space
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                guessesShown = guessesShown.substring(0, i) + " " + guessesShown.substring(i + 1);
            }
        }
    }

    private String useHint() {
        while (true) { //keeps on choosing a random word from the list until it's equal to _ (meaning not guessed yet)
            int rng = (int) (Math.random() * word.length());
            if (guessesShown.substring(rng, rng+1).equals("＿")) {
                return word.substring(rng, rng+1);
            }
        }
    }
    private void stats() {
        System.out.println("Rounds Played: " + roundsPlayed);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Rounds Lost: " + roundsLost);
        System.out.println("Win-rate: " + (int) Math.round((double) roundsWon / roundsPlayed * 100) + "%\n");
        System.out.println("Total coins spent: " + coinsSpent);
        System.out.println("Total hints used: " + hintsUsed);
        System.out.println("Total power-ups used: " + powerUpsUsed);
        System.out.println("\nWords guessed: " + wordsGuessed);
        System.out.println("Letters guessed: " + lettersGuessed);
        if (leastLettersGuessed == 999) {
            System.out.println("Least Letters used to guess a word: N/A");
        } else {
            System.out.println("Least letters used to guess a word: " + leastLettersGuessed);
        }
    }

    private void usePowerUp() {
        double cycles = Math.floor(word.length() / 2.5); //Tells us how many words to reveal
        for (int i = 0; i < cycles; i++) {
            while (true) {
                int rng = (int) (Math.random() * word.length());
                if (guessesShown.charAt(rng) != '＿') { //Keeps on going until the rng is equal to '_' in guessShown
                } else { //If it is equal then
                    for (int j = 0; j < word.length(); j++) { //Goes through the list to see if the random word that was just revealed also appears in other parts in the word
                        if (word.substring(rng, rng + 1).equals(word.substring(j, j + 1))) {
                            guessesShown = guessesShown.substring(0, j) + word.charAt(j) + guessesShown.substring(j + 1);
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Used to clear the console
     *
     */
    private void clearConsole() {
        for (int i = 0; i <= 30; i++) {
            System.out.println();
        }
    }
    //Mr.Miller helped here
    private void getStats() {
        try {
            File dataFile = new File("src\\data.txt");
            Scanner fileScanner = new Scanner(dataFile);
            if (fileScanner.hasNextInt()) coins = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) hints = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) powerups = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) lettersGuessed = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) wordsGuessed = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) roundsWon = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) roundsLost = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) roundsPlayed = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) hintsUsed = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) powerUpsUsed = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) coinsSpent = fileScanner.nextInt();
            if (fileScanner.hasNextInt()) leastLettersGuessed = fileScanner.nextInt();
            fileScanner.close();
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void saveStats() {
        try {
            PrintWriter dataFile = new PrintWriter("src/data.txt");
            dataFile.println(coins);
            dataFile.println(hints);
            dataFile.println(powerups);
            dataFile.println(lettersGuessed);
            dataFile.println(wordsGuessed);
            dataFile.println(roundsWon);
            dataFile.println(roundsLost);
            dataFile.println(roundsPlayed);
            dataFile.println(hintsUsed);
            dataFile.println(powerUpsUsed);
            dataFile.println(coinsSpent);
            dataFile.println(leastLettersGuessed);
            dataFile.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}














