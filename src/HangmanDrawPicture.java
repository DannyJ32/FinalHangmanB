public class HangmanDrawPicture {
    private int guesses;
    public void draw(String userDifficulty, int guessAmount) {
        guesses = guessAmount;
        switch (userDifficulty.toUpperCase()) { //Chooses the difficulty and then calls the methods to draw the picture
            case "HARD":
                drawHard(guesses);
                break;
            case "MEDIUM":
                drawMedium(guesses);
                break;
            case "EASY":
                drawEasy(guesses);
                break;
        }
    }

    private void drawHard(int guessAmount) { //Draws Hard picture and the ones below draw medium and easy
        switch (guessAmount) {
            case 3:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("    _|_                   ");
                break;
            case 2:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("    _|_                   ");
                break;
            case 1:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       __|__        ");
                System.out.println("     |         |          ");
                System.out.println("    _|_                   ");
                break;
            case 0:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       __|__        ");
                System.out.println("     |        _|_         ");
                System.out.println("    _|_       | |         ");
                break;
        }
    }

    private void drawMedium(int guessAmount) {
        switch (guessAmount) {
            case 5:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("    _|_                   ");
                break;
            case 4:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("    _|_                   ");
                break;
            case 3:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |         |          ");
                System.out.println("     |         |          ");
                System.out.println("    _|_                   ");
                break;
            case 2:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |         |__        ");
                System.out.println("     |         |          ");
                System.out.println("    _|_                   ");
                break;
            case 1:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       __|__        ");
                System.out.println("     |         |          ");
                System.out.println("    _|_                   ");
                break;
            case 0:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       __|__        ");
                System.out.println("     |        _|_         ");
                System.out.println("    _|_       | |         ");
                break;
        }
    }

    private void drawEasy(int guessAmount) {
        switch (guessAmount) {
            case 8:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("    _|_                   ");
                break;
            case 7:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("    _|_                   ");
                break;
            case 6:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |                    ");
                System.out.println("     |                    ");
                System.out.println("    _|_                   ");
                break;
            case 5:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |         |          ");
                System.out.println("     |         |          ");
                System.out.println("    _|_                   ");
                break;
            case 4:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |         |__        ");
                System.out.println("     |         |          ");
                System.out.println("    _|_                   ");
                break;
            case 3:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       __|__        ");
                System.out.println("     |         |          ");
                System.out.println("    _|_                   ");
                break;
            case 2:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       __|__        ");
                System.out.println("     |         |_         ");
                System.out.println("    _|_         |         ");
                break;
            case 1:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       __|__        ");
                System.out.println("     |        _|_         ");
                System.out.println("    _|_       | |         ");
                break;
            case 0:
                System.out.println("     -----------          ");
                System.out.println("     |         |          ");
                System.out.println("     |      _______       ");
                System.out.println("     |     _|_____|_      ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       |---|        ");
                System.out.println("     |       __|__        ");
                System.out.println("     |        _|_         ");
                System.out.println("    _|_       | |         ");
                break;

        }
    }
}
