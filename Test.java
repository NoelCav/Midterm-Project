import java.util.*;
class Test {

    public static void main(String[] args) {
        //creats the game and the scanner
        Game fun = new Game();
        Scanner sc = new Scanner(System.in);
        //boolean for main loop
        boolean gamePlaying = true;
        //clear screen for start of game
        fun.clearScreen();
        System.out.println("What activity do you want to try?");
        //main loop
        while (gamePlaying) {
            //sets when game is over
            if (fun.wonMaze && fun.wonQuiz && fun.wonMemory) {
                gamePlaying = false;
            } else {
                //shows different text if you have completed an activity
                if (fun.wonMaze) {
                    System.out.println("1. Maze - Completed");
                } else {
                    System.out.println("1. Maze");
                }
                if (fun.wonQuiz) {
                    System.out.println("2. Quiz - Completed");
                } else {
                    System.out.println("2. Quiz");
                }
                if (fun.wonMemory) {
                    System.out.println("3. Memory Game - Completed");
                } else {
                    System.out.println("3. Memory Game");
                }
                String gameChoice = sc.nextLine().toLowerCase();
                if (gameChoice.equals("1") || gameChoice.equals("maze")) {
                    fun.mazeSetup();
                    fun.startMaze();
                } else if (gameChoice.equals("2") || gameChoice.equals("quiz")) {
                    fun.startQuiz();
                } else if (gameChoice.equals("3") || gameChoice.equals("memory game")) {
                    fun.startMemory();
                } else {
                    System.out.println("Type the name or number of the Game you want to play.");
                }
            }
        }
        //end game message
        System.out.println("You did it! Thats the whole game! Congrats!");
    }
}