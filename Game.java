import java.util.*;
class Game {
    //creating scanner and player
    Scanner sc = new Scanner(System.in);
    Player player = new Player();
    //booleans to tell when the game is won
    boolean wonMaze = false;
    boolean wonQuiz = false;
    boolean wonMemory = false;
    //making the maze
    int[][] maze = new int[10][10];
    //booleans to tell if there is a wall in certain directions in comparison to the player
    boolean northSpace;
    boolean eastSpace;
    boolean southSpace;
    boolean westSpace;
    //clears screen when you start a new game
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println("                                                                             ");
        }
    }
    //code that sets up the walls of the maze
    public void mazeSetup() {
        //making the perimeter of the maze and the center 0
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (y == 0 || y == 9 || x == 0 || x == 9) {
                    maze[x][y] = 1;
                } else {
                    maze[x][y] = 0;
                }
            }
        }
        //making the maze complex
        maze[1][8] = 1;
        maze[3][8] = 1;
        maze[1][7] = 1;
        maze[3][7] = 1;
        maze[1][6] = 1;
        maze[1][5] = 1;
        maze[2][5] = 1;
        maze[3][5] = 1;
        maze[5][7] = 1;
        maze[6][7] = 1;
        maze[7][7] = 1;
        maze[7][6] = 1;
        maze[7][5] = 1;
        maze[6][5] = 1;
        maze[5][7] = 1;
        maze[5][5] = 1;
        maze[4][3] = 1;
        maze[5][3] = 1;
        maze[3][3] = 1;
        maze[2][3] = 1;
        maze[2][2] = 1;
        maze[2][1] = 1;
        maze[3][2] = 1;
        maze[7][2] = 1;
        maze[7][3] = 1;
        maze[7][4] = 1;
        maze[6][1] = 1;
        maze[5][1] = 1;
    }
    //making the maze playable because it is so hard
    public void printMap() {
        //stacked for loops that print out the values of each cord
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                //show where the player is
                if (y == player.cordY && x == player.cordX) {
                    System.out.print("2 ");
                } else {
                    //show where the goal is
                    if (x == 1 && y == 1) {
                        System.out.print("3 ");
                    } else {
                        System.out.print(maze[x][y] + " ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("The path is 0, the walls are 1, you are the 2, the goal is 3");
    }
    //sets the booleans from before, this simplifies the later process of finding out where the player can go
    public void checkMazeSpace() {
        if (maze[player.cordX + 1][player.cordY] == 0) {
            eastSpace = true;
        } else {
            eastSpace = false;
        }
        if (maze[player.cordX - 1][player.cordY] == 0) {
            westSpace = true;
        } else {
            westSpace = false;
        }
        if (maze[player.cordX][player.cordY + 1] == 0) {
            southSpace = true;
        } else {
            southSpace = false;
        }
        if (maze[player.cordX][player.cordY - 1] == 0) {
            northSpace = true;
        } else {
            northSpace = false;
        }
    }
    //tells the player what directions are possible
    public void printMazePath() {
        checkMazeSpace();
        System.out.println("There is a path to your:");
        if (northSpace) {
            System.out.print(" North");
        }
        if (southSpace) {
            System.out.print(" South");
        }
        if (eastSpace) {
            System.out.print(" East");
        }
        if (westSpace) {
            System.out.print(" West");
        }
    }
    //the body of the maze
    public void startMaze() {
        //booleans to start and stop the maze
        boolean win = false;
        boolean mazeActive = true;
        //main loop of maze
        while (mazeActive) {
            //does the previous 2 methods to set up for a movement
            checkMazeSpace();
            printMazePath();
            System.out.print("\n");
            System.out.println("Where do you want to go? (Type: N, S, E, or W) (This one many be hard so use the command \"Cheat\" to give you an advantage if you need it! Type \"exit\" if you give up!");
            //this scanner doesn't work correctly the first time sometimes and i cant figure out how to fix it
            //asking the player for directions and changing the players cords respectively
            String direction = sc.next().toLowerCase();
            //allows cheating
            if (direction.equals("cheat")) {
                printMap();
                //allows quiting
            } else if (direction.equals("quit") || direction.equals("exit")) {
                mazeActive = false;
            } else if (direction.equals("n")) {
                if (northSpace) {
                    player.cordY -= 1;
                } else {
                    System.out.println("There is a wall there.");
                }
            } else if (direction.equals("s")) {
                if (southSpace) {
                    player.cordY += 1;
                } else {
                    System.out.println("There is a wall there.");
                }
            } else if (direction.equals("e")) {
                if (eastSpace) {
                    player.cordX += 1;
                } else {
                    System.out.println("There is a wall there.");
                }
            } else if (direction.equals("w")) {
                if (westSpace) {
                    player.cordX -= 1;
                } else {
                    System.out.println("There is a wall there.");
                }
            } else {
                System.out.println("Type the first letter of the direction you want to go");
            }
            //win conditions
            if (player.cordX == 1 && player.cordY == 1) {
                mazeActive = false;
                win = true;
            }
        }
        //win message
        if (win) {
            System.out.println("congrats! You won!");
            wonMaze = true;
        }
    }
    //quiz
    public void startQuiz() {
        //booleans of which question is active so the player has multiple chances and can quit
        boolean question1 = false;
        boolean question2 = false;
        boolean question3 = false;        
        boolean bonus = false;
        boolean bonusWin = false;
        boolean quit = false;
        System.out.println("I hope you know your Monty Python. Type \"exit\" to quit. Your first question is:");
        //the first question, this scanner is bugged too and i can't figure it out
        while (!question1) {
            //monty python references
            System.out.println("What is your name?");
            String Ans1 = sc.nextLine().toLowerCase();
            if (Ans1.equals("it is arthur, king of the britons.") || Ans1.equals("arthur")) {
                question1 = true;
                //quit option (on all questions)
            } else if (Ans1.equals("quit") || Ans1.equals("quit")) {
                question1 = true;
                quit = true;
            } else {
                question1 = false;
                System.out.println("No.");
            }
        }
        if (!quit) {
            while (!question2) {
                System.out.println("What is your quest?");
                String Ans2 = sc.nextLine().toLowerCase();
                if (Ans2.equals("to seek the holy grail.") || Ans2.equals("holy grail")) {
                    question2 = true;
                } else if (Ans2.equals("quit") || Ans2.equals("quit")) {
                    question2 = true;
                    quit = true;
                } else {
                    question2 = false;
                    System.out.println("No.");
                }
            }
        }
        if (!quit) {
            while (!question3) {
                System.out.println("What is your favorite color?");
                String Ans3 = sc.nextLine().toLowerCase();
                if (Ans3.equals("blue") || Ans3.equals("green")) {
                    question3 = true;
                    System.out.println("Very Good");
                    wonQuiz = true;
                } else if (Ans3.equals("quit") || Ans3.equals("quit")) {
                    question3 = true;
                    quit = true;
                } else {
                    question3 = false;
                    System.out.println("No.");
                }
            }
        }
        if (!quit) {
            System.out.println("Would you like to try a challenge problem?");
            //this gives option of an extra question, but typing no takes 2 tries
            if (sc.next().toLowerCase().equals("yes") || sc.next().toLowerCase().equals("y"))  {
                bonus = true;
            } else {
                bonus = false;
                System.out.println("Fair is fair. You win, go back to the menu.");
            }
            if (bonus) {
                while (bonus) {
                    //this one is bugged too
                    System.out.println("What is the air-speed velocity of an unladen swallow?");
                    String AnsB = sc.nextLine().toLowerCase();
                    if (AnsB.contains("11") || AnsB.equals("an african or european swallow?"))  {
                        bonusWin = true;
                        bonus = false;
                        System.out.println("Congrats! You won!");
                    } else if (AnsB.equals("quit") || AnsB.equals("quit")) {
                        bonus = false;
                    } else {
                        bonusWin = false;
                        System.out.println("Try again.");
                    }
                }
            }
        }
    }
    //memory game
    public void startMemory() {
        //string of possible words
        String[] MemoryList = new String[] {"abashed", "ubiquitous", "grieving", "toe", "nostalgic", "love", "house", "long", "pancake", "holiday", "kick", "mean", "five", "swing", "shaky", "deafening", "truthful", "hammer", "ship", "wing", "division", "plain", "fry", "coil", "lip", "dreary", "numberless", "needle", "terrify", "grade", "wild", "box", "babies", "jealous", "befitting", "wooden", "wool", "ink", "toad", "mouth", "nasty", "cheat", "physical", "thoughtful", "skillful", "economic", "snatch", "boat", "itch", "steam"};
        //arraylist of the words the player will have to guess
        ArrayList<String> ChosenWords = new ArrayList<String>();
        Boolean cont = false;
        //picks 10 random words and puts them in the Chosen words arraylist, this can pick the same word twice but i like that
        for (int n = 0; n < 10; n++) {
            int WordNum = (int) (Math.random() * 49 + 1);
            ChosenWords.add(MemoryList[WordNum]);
            System.out.println(ChosenWords.get(n));
        }
        //memorize
        System.out.println("Memorize these words. When you are ready, type: \"y\"");
        //triger when done memorizing
        while (!cont) {
            if (sc.next().equals("y")) {
                cont = true;
            }
        }
        //deletes previous text
        for (int i = 0; i < 50; i++) {
            System.out.println("////////////////////////////////////////////////////////////////////////");
        }
        //intructions
        System.out.println("What were the words? Dont you dare copy and paste! (type \"exit\" to quit)");
        //counter of how many words they got right so far
        int correctNum = 0;
        boolean quit = false;
        //array list for words they alread got so they cant game the system by typing the same words multiple times
        ArrayList<String> GuessedWords = new ArrayList<String>();
        while (correctNum != 9 && quit == false) {
            String guess = sc.next();
            //checks if the words are in the certain array lists
            if (guess.equals("quit") || guess.equals("exit")) {
                quit = true;
                if (correctNum > 5) {
                    System.out.println("You got " + correctNum + " correct! Good Job!");
                } else {
                    System.out.println("You got " + correctNum + " correct! Nice try... I guess...");
                }
            } else if (ChosenWords.contains(guess)) {
                correctNum += 1;
                System.out.println("That is correct, you guessed " + correctNum + "/10 so far");
                ChosenWords.remove(guess);
                GuessedWords.add(guess);
            } else if (GuessedWords.contains(guess)) {
                System.out.println("You already guessed that one, you still have " + (10-correctNum) + " left.");
            } else if (!ChosenWords.contains(guess) && !GuessedWords.contains(guess)) {
                System.out.println("wrong, try again");
            } else {
                System.out.println("error");
            }
        }
        //win text
        if (quit == false) {
            System.out.println("You Won! Fantastic Job! Back to the menu.");
            wonMemory = true;
        } else {
            System.out.println("You did your best, back to the menu.");
        }
    }

    public static void main(String[] args) {

    }
}