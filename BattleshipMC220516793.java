/** This project is the first assignment for course ITWM5113, enrolled by MC220516793 in May 2022.
 * The assignment requires a recreation of the game known as 'Battleship'.
 * The game entails a human player playing against a computer player to sink all of each other's ship by way of defeat.
 * The game begins with a simple welcoming note to notify the player the name of the game.
 * The player will be prompted to enter 5 sets of coordinates, denoting the location of their ships on a grid of 10 by 10.
 * A wrong input constitute invalid coordinate will be returned with an error message.
 * The computer will subsequently set it's ships coordinates on the same grid as well.
 * The battle ensues when the player input a valid coordinate as guessed target of computer's ship by way of attack to sink the ship.
 * The computer does the same after to reciprocate, before the player gets to launch another attack.
 * Notification will be provided with every missed, successful or when either player has accidentally sunk its own ship
 * The series of attacks will end when either the player or the computer has gotten all of their ships sunk.
 * The game ends with a congratulatory note to the player who still has at least one ship left.
 */

//This statement is the common scanner class available in the java.util package, that is imported into this class to allow the user (i.e. the human player) to enter input
import java.util.*;

//Part 0: This part of the code makes up project class. It begins with a declaration of all variables used throughout the project and the
public class BattleshipMC220516793 {
    public static int numRows = 10;
    public static int numCols = 10;
    public static int PlayerShips;
    public static int ComputerShips;
    public static int i;
    public static int nextInt;
    public static String[][] grid = new String[numRows][numCols];
    public static int[][] missedGuesses = new int[numRows][numCols];
// Part 1: This part of the code is the main method that calls for other methods required to run the entire game
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship Game!");
        System.out.println("The battle area is now empty");

        //Part 2: This part of the code calls for the method to create the 10 by 10 battle area. The method is written in Part 7 of this code.
        createBattleMap();

        //Part 3: This part of the code calls for the method to prompt the user (human player) to set the coordinates of user's ships.This method is written in Part XX of this code.
        deployPlayerShips();

        //Part 4: This part of the code calls for the method that generates random numbers to set as coordinates of the computer's ships.This method is written in Part XX of this code
        deployComputerShips();

        //Part 5: This part of the code calls for the method that undertakes the battle sequence. The method is written in Part 10 of this code.
        do {
            BattleshipMC220516793();
        } while (BattleshipMC220516793.PlayerShips != 0 && BattleshipMC220516793.ComputerShips != 0);

        //Part 6: This part of the code calls for the method that provides the congratulatory note to the winner of the battle.
        gameOver();

    }


    //Part 7: This part of the code is the method that creates the 10 by 10 battle area. This method is called in Part 2 of this code.
    public static void createBattleMap() {
        //First section of the battle area. It produces numbers 0-9 that makes the top margin of the battle area.
        System.out.println(" ");
        System.out.print("  ");
        for (int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();

        //Second section of the battle area. It produces numbers 0-9 and a straight downward line that makes the left and right margins of the battle area in the console.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + grid[i][j]);
                else if (j == grid[i].length - 1)
                    System.out.print(grid[i][j] + "|" + i);
                else
                    System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        //Third and last section of the battle area. It produces numbers 0-9 that makes the bottom margin of the battle area.
        System.out.print("  ");
        for (int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
    }

        //Part 8: This part of the code is the method that prompts the user (the human player) to input coordinates of the users' ships, and displays them as '@'.

        public static void deployPlayerShips(){
            Scanner input = new Scanner(System.in);
            System.out.println("\nDeploy your ships:");
            BattleshipMC220516793.PlayerShips = 5;
            for (int i = 1; i <= BattleshipMC220516793.PlayerShips;){
                System.out.print("Enter the X-coordinate for your "+ i +" ship: ");
                int x = input.nextInt();
                System.out.print("Enter Y ccordinate for your "+ i +" ship: ");
                int y = input.nextInt();


                if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && grid[x][y] == " ")
                {
                    grid[x][y] =  "@";
                    i++;
                }
                //This section checks if the coordinates that has been provided is valid; i.e. if the if there any ships which are provided the same coordinate.

                else if((x >= 0 && x <numRows) && (y >= 0 && y < numCols) && grid[x][y] == "@")
                    System.out.println("You can't place two or more ships on the same location");

                else if((x < 0 || x >= numRows) || (y < 0 || y >= numCols))
                    System.out.println("You can't place your ships outside the " + numRows + " by " + numCols +" battle area grid");
            }
            printBattleMap();
        }

        //Part 9: This part of the code is the methods that prompts the computer to generate random coordinates of the computers' ships and displays them as 'x'.

        public static void deployComputerShips(){
            System.out.println("\nComputer is deploying ships");
            BattleshipMC220516793.ComputerShips = 5;
            for (int  i = 1; i <=BattleshipMC220516793.ComputerShips;){
                int x = (int)(Math.random() * 10);
                int y = (int)(Math.random() * 10);

                if((x >= 0 && x <numRows) && (y >= 0 && y <numCols) && (grid[x][y] == " "))
                {
                    grid[x][y] = "x";
                    System.out.println (i + ". ships DEPLOYED");
                    i++;
                }
            }
            printBattleMap();
        }

        //Part 10: This part runs the public class for iteration of turns between the user (human player) and the computer and prints the outcome of each attack.

        public static void BattleshipMC220516793(){
            //This section calls for the method for user to provides a set of coordinate which the computers ship may be at. This method is defined at Part xx of this code.
            playerTurn();
            //This section calls for the method for computer to generate a set of coordinate which may be where the users ship is at.
            computerTurn();
            //This section calls for the method which prints the outcome of each coordinate input by the player, and the computer.
            printBattleMap();
            //This section displays the tally between the users ships and the computers ship.
            System.out.println();
            System.out.println("Your ships: " + BattleshipMC220516793.PlayerShips + " | Computer ships: " + BattleshipMC220516793.ComputerShips);
            System.out.println();
        }

        //Part 11: This part of the code is to capture and respond to users attack.
        public static void playerTurn(){
            System.out.println("\nYOUR TURN");
            int x = -1, y =-1;
            do {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter X coordinate");
                x = input.nextInt();
                System.out.print("Enter Y coordinate");
                y = input.nextInt();

                //This section checks if the input is valid.
                if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) {
                    //This section checks if there is already a player ship. If there is, the player loses a ship.
                    if (grid[x][y] == "x") {
                        System.out.println("Oh no, You have sunk your own ship");
                        grid[x][y] = "!";
                        --BattleshipMC220516793.ComputerShips;
                    }
                    //  This section checks if there is already a computer ship. If there is, the computer looses a turn.
                    else if (grid[x][y] == "@") {
                        System.out.println("Boom! You have sunk the ship!");
                        grid[x][y] = "x";
                        --BattleshipMC220516793.PlayerShips;
                        ++BattleshipMC220516793.ComputerShips;
                    }
                    //This section checks if these is any computer ship in.
                    else if (grid[x][y] == " ") {
                        System.out.println("Sorry, you missed");
                        grid[x][y] = "-";
                    }
                }
                //This section checks for Invalid Guesses
                else if ((x < 0 || x >= numRows) || (y < 0 || y>= numCols ))
                    System.out.println("You can't place ship outside the " + numRows + "by " + numCols + "grid ");
                //This section keeps re-prompting the user until valid guesses are made.
            }while((x < 0 || x >=numRows) || (y <0 || y >= numCols));
        }

        //Part 12: This part of the code is to capture and respond to computer's attack.
        public static void computerTurn(){
            //This section generates random coordinate to attack user ships
            System.out.println("\nIt's computers turn");
            int x = -1, y = -1;
            do{
                x = (int)(Math.random() * 10);
                y = (int)(Math.random() * 10);

                //This section checks if the inputs are valid.
                if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols))
                {
                    //This section checks if there is already a user's ship on the selected coordinates. If these is, the player looses a ship.
                    if (grid[x][y] == "@")
                    {
                        System.out.println("The computer has sunk one of your ships!");
                        grid[x][y] = "x";
                        --BattleshipMC220516793.PlayerShips;
                        ++BattleshipMC220516793.ComputerShips;
                    }
                    //This section checks if there is already a computer's ship on the selected coordinates
                    else if (grid[x][y] == "x"){
                        System.out.println("The computer has sunk its own ships");
                        grid[x][y] = "!";
                    }
                    //This section saves missed guesses for computer
                    else if (grid[x][y] == " ")
                    {
                        System.out.println("Computer Missed");
                        if (missedGuesses[x][y] != 1)
                            missedGuesses[x][y] = 1;
                    }
                }
                else
                    System.out.println("Sorry, that coordinate is out of the BattleMap");
                //Part 12: This part keeps re-prompting for valid guesses
                }while ((x < 0 || x >= numRows) || (y < 0 || y >= numCols));
            }

            //Part 13: This part provides the end of the battle
        public static void gameOver() {
            System.out.println("Your ships: " + BattleshipMC220516793.PlayerShips + " | Computer ships: " + BattleshipMC220516793.ComputerShips);
            if (BattleshipMC220516793.PlayerShips > 0 && BattleshipMC220516793.ComputerShips <= 0)
            System.out.println("Hooray! You have won the battle");
            else
            System.out.println("Sorry, you have lost the battle");
            System.out.println();
        }


        //Part 14: This part prints the battle area.
        public static void printBattleMap(){
            //This section is the first section of the BattleMap
        System.out.println(" ");
        for(int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();

        //This section is the second section of the BattleMap
        for (int x = 0; x < grid.length; x++){
            System.out.print(x + "|");

            for(int y = 0; y < grid[x].length; y++){
                System.out.println(grid[x][y]);
            }

            System.out.println("|" + x);
        }

        //This section is the third and last section of the  BattleMap

        System.out.print(" ");
        for (int i = 0; i < numCols; i++)
            System.out.print(i);
        System.out.println();
        }
    }



