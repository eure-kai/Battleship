
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        while (true) {
            boolean[][][] myGrid = setUp();

            boolean[][] p1 = myGrid[0];
            boolean[][] p1_Guesses = myGrid[1];

            boolean[][] p2 = myGrid[2];
            boolean[][] p2_Guesses = myGrid[3];


            int[] ships = ships();

            playerSet(p1, ships, 1);

            playerSet(p2, ships, 2);


            System.out.print("\n\n\nFinally, the setup is over! Let the game begin!");

            Game(p1, p1_Guesses, p2, p2_Guesses);


            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            Scanner scn = new Scanner(System.in);

            System.out.print("\n\n\nWould you like to play again? y/n ");

            String word = scn.nextLine();

            if (!word.equalsIgnoreCase("Y")) {
                System.out.print("\nThank you for playing!");
                break;


            } else {

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.print("\n\n\n");
            }
        }

    }


    public static boolean[][][] setUp() {

        while (true) {
            Scanner scn = new Scanner(System.in);

            System.out.print("\n\nHow tall should the board be? ");
            int row = scn.nextInt();

            System.out.print("\nHow wide should the board be? ");
            int col = scn.nextInt();


            if (row >= 2 && col >= 2) {
                boolean[][] array = new boolean[row][col];
                boolean[][] arrayG = new boolean[row][col];

                boolean[][] array2 = new boolean[row][col];
                boolean[][] array2G = new boolean[row][col];

                return new boolean[][][]{array, arrayG, array2, array2G};

            } else {
                System.out.println("\nPlease choose a grid that is at least 2 by 2.");

            }

        } //end of while loop


    }


    public static void printGrid(boolean[][] array) {

        System.out.print("  ");

        for (int i = 0; i < array[0].length; i++) {
            System.out.print(i + " ");
        }

        System.out.println();


        for (int j = 0; j < array.length; j++) {
            System.out.print(j + " ");

            for (int k = 0; k < array[0].length; k++) {
                if (array[j][k]) System.out.print("X ");
                else System.out.print("- ");
            }

            System.out.println();
        }
    }



    public static int[] ships() {
        Scanner scn = new Scanner(System.in);

        while (true) {

            System.out.print("\n\n\nHow many two-unit ships should each player have? ");
            int two = scn.nextInt();

            System.out.print("\nHow many three-unit ships should each player have? ");
            int three = scn.nextInt();

            System.out.print("\nHow many four-unit ships should each player have? ");
            int four = scn.nextInt();


            if (two != 0 || three != 0 || four != 0) {
                return new int[]{two, three, four};

            } else {
                System.out.print("\nPlease pick at least one ship.");
            }


        }
    }


    public static boolean[][] playerSet(boolean[][] array, int[] ships, int num) {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\n\n\nPlayer " + num + ", set up your ships!\n\n");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printGrid(array);


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        twoShips(array, ships);

        System.out.print("\n\n\nYou've completed placing your 2-ships.");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Throwable e) {
            e.printStackTrace();
        }


        System.out.print("\n\n");

        threeShips(array, ships);

        System.out.print("\n\n\nYou've completed placing your 3-ships.");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.print("\n\n");
        fourShips(array, ships);

        System.out.print("\n\n\nYou've completed placing your 4-ships.");
        // TODO change this code below
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\n\n");

        return array;

    }



    public static boolean[][] twoShips(boolean[][] array, int[] ships) {
        if (ships[0] == 0) return array;

        Scanner scn = new Scanner(System.in);

        System.out.print("\n\nEnter coordinates for your " + ships[0] + " 2-ships:");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = 0;

        while (i < ships[0]) {
            System.out.println("\n\n\n");

            System.out.println("Pair " + (i + 1) + ":\n ");

            System.out.print("\nPick a row (HORIZONTAL): ");
            int row = scn.nextInt();

            System.out.print("\nPick a column (VERTICAL): ");
            int col = scn.nextInt();

            System.out.print("\n\nPick the final row: ");
            int row2 = scn.nextInt();

            System.out.print("\nPick the final column: ");
            int col2 = scn.nextInt();


            int length = array.length;
            int colLength = array[0].length;


            if ((row > length - 1 || row < 0) || (col > colLength - 1 || col < 0) || (row2 > length - 1 || row2 < 0) || (col2 > colLength - 1 || col2 < 0)) {

                System.out.print("\nThose coordinates are out of bounds!");

            } else if (array[row][col] || array[row2][col2]) {
                System.out.print("\nYou've already placed a ship here!");


                //Below, in the else statement, we will be checking to make sure the rows or columns are consecutive. If the rows are equal, the columns must be consecutive (difference of 1). If the columns are equal, then the rows must be consecutive (difference of 1). Otherwise, the coordinates are not consecutive, which is illegal in battleship.

            } else {
                int diffR = Math.abs(row - row2); //absolute value difference in rows
                int diffC = Math.abs(col - col2); //absolute value difference in cols


                //rows are equal, cols are consecutive. OR cols are equal, rows are consecutive.
                if ( (row == row2 && diffC == 1) || (col == col2 && diffR == 1) ) {
                    array[row][col] = true;
                    array[row2][col2] = true;

                    i++;


                } else {
                    System.out.print("\nThose coordinates are not consecutive!");
                }

            } //end of outer else statement

            System.out.print("\n\n");
            printGrid(array);

        } //end of while loop

        return array;
    }




    public static boolean[][] threeShips(boolean[][] array, int[] ships) {

        if (ships[1] == 0) return array;

        Scanner scn = new Scanner(System.in);

        System.out.print("\n\nEnter coordinates for your " + ships[1] + " 3-ships:");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = 0;

        while (i < ships[1]) {

            System.out.println("\n\n\n");

            System.out.println("Group " + (i + 1) + ":\n ");

            System.out.print("\nPick a row: ");
            int row = scn.nextInt();

            System.out.print("\nPick a column: ");
            int col = scn.nextInt();

            System.out.print("\n\nPick another row: ");
            int row2 = scn.nextInt();

            System.out.print("\nPick another column: ");
            int col2 = scn.nextInt();

            System.out.print("\n\nPick the final row: ");
            int row3 = scn.nextInt();

            System.out.print("\nPick the final column: ");
            int col3 = scn.nextInt();


            int length = array.length;
            int colLength = array[0].length;


            if ((row > length - 1 || row < 0) || (col > colLength - 1 || col < 0) || (row2 > length - 1 || row2 < 0) || (col2 > colLength - 1 || col2 < 0) ||
                    (row3 > length - 1 || row3 < 0) || (col3 > colLength - 1 || col3 < 0)){

                System.out.print("\nThose coordinates are out of bounds!");


            } else if (array[row][col] || array[row2][col2] || array[row3][col3]) {
                System.out.print("\nYou've already placed a ship there!");


                //Below, in the else statement, we will be checking to make sure the rows or columns are consecutive. If the rows are equal, the columns must be consecutive (difference of 1). If the columns are equal, then the rows must be consecutive (difference of 1). Otherwise, the coordinates are not consecutive, which is illegal in battleship.


            } else {
                int[] rows = {row, row2, row3};
                Arrays.sort(rows); //make an array of the rows and sort it

                int[] cols = {col, col2, col3};
                Arrays.sort(cols); //make an array of the cols and sort it


                if ( (row == row2 && row2 == row3) && ((cols[0] == cols[1] - 1) && (cols[1] == cols[2] - 1)) ) /*rows are equal, cols are consecutive*/ {

                    array[row][col] = true;
                    array[row2][col2] = true;
                    array[row3][col3] = true;

                    i++;


                } else if ( (col == col2 && col2 == col3) && ((rows[0] == rows[1] - 1) && (rows[1] == rows[2] - 1)) ) /*cols are equal, rows are consecutive*/ {

                    array[row][col] = true;
                    array[row2][col2] = true;
                    array[row3][col3] = true;

                    i++;


                } else {
                    System.out.print("\nThose coordinates are not consecutive!");

                }

            } //end of outer else statement


            System.out.print("\n\n");
            printGrid(array);

        } //end of while loop

        return array;
    }



    public static boolean[][] fourShips(boolean[][] array, int[] ships) {

        if (ships[2] == 0) return array;

        Scanner scn = new Scanner(System.in);

        System.out.print("\n\nEnter coordinates for your " + ships[2] + " 4-ships:");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = 0;

        while (i < ships[2]) {

            System.out.println("\n\n\n");

            System.out.println("Group " + (i + 1) + ":\n ");

            System.out.print("\nPick a row: ");
            int row = scn.nextInt();

            System.out.print("\nPick a column: ");
            int col = scn.nextInt();

            System.out.print("\n\nPick another row: ");
            int row2 = scn.nextInt();

            System.out.print("\nPick another column: ");
            int col2 = scn.nextInt();


            System.out.print("\n\nChoose another row: ");
            int row3 = scn.nextInt();

            System.out.print("\nChoose another column: ");
            int col3 = scn.nextInt();


            System.out.print("\n\nPick the final row: ");
            int row4 = scn.nextInt();

            System.out.print("\nPick the final column: ");
            int col4 = scn.nextInt();



            int length = array.length;
            int colLength = array[0].length;


            if ((row > length - 1 || row < 0) || (col > colLength - 1 || col < 0) || (row2 > length - 1 || row2 < 0) || (col2 > colLength - 1 || col2 < 0) ||
                    (row3 > length - 1 || row3 < 0) || (col3 > colLength - 1 || col3 < 0) ||
                    (row4 > length - 1 || row4 < 0) || (col4 > colLength - 1 || col4 < 0)) {

                System.out.print("\nThose coordinates are out of bounds!");


            } else if (array[row][col] || array[row2][col2] || array[row3][col3] || array[row4][col4]) {

                System.out.print("\nYou've already placed a ship there!");



                //Below, in the else statement, we will be checking to make sure the rows or columns are consecutive. If the rows are equal, the columns must be consecutive (difference of 1). If the columns are equal, then the rows must be consecutive (difference of 1). Otherwise, the coordinates are not consecutive, which is illegal in battleship.

            } else {

                int[] rows = {row, row2, row3, row4};
                Arrays.sort(rows); //make an array of the rows and sort it

                int[] cols = {col, col2, col3, col4};
                Arrays.sort(cols); //make an array of the cols and sort it


                //rows are equal, and cols are consecutive:
                if ( (row == row2 && row2 == row3 && row3 == row4) && ((cols[0] == cols[1] - 1) && (cols[1] == cols[2] - 1) && (cols[2] == cols[3] - 1)) ) {

                    array[row][col] = true;
                    array[row2][col2] = true;
                    array[row3][col3] = true;
                    array[row4][col4] = true;

                    i++;


                    //cols are equal, and rows are consecutive:
                } else if ( (col == col2 && col2 == col3 && col3 == col4) && ((rows[0] == rows[1] - 1) && (rows[1] == rows[2] - 1) && (rows[2] == rows[3] - 1)) ) {

                    array[row][col] = true;
                    array[row2][col2] = true;
                    array[row3][col3] = true;
                    array[row4][col4] = true;

                    i++;


                } else {
                    System.out.print("\nThose coordinates are not consecutive!");

                }

            } //end of outer else statement


            System.out.print("\n\n");
            printGrid(array);

        } //end of while loop

        return array;
    }




    public static boolean[][] turn(boolean[][] player, boolean[][] opp, int num) {

        Scanner scn = new Scanner(System.in);


        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("\n\n\n\nPlayer " + num + ", here are your hits:\n");

            printGrid(player);

            System.out.print("\nPlayer " + num + ", pick a row: ");
            int row = scn.nextInt();

            System.out.print("\nPick a column: ");
            int col = scn.nextInt();


            int length = player.length;
            int colLength = player[0].length;


            if ((row > length - 1 || row < 0) || (col > colLength - 1 || col < 0)) {
                System.out.print("\nThose coordinates are out of bounds!");

            } else {
                if (player[row][col]) System.out.print("\nYou've already hit there!");

                else {
                    if (opp[row][col]) {
                        System.out.print("\nIt's a hit!");
                        player[row][col] = true;

                    } else {
                        System.out.print("\nIt's a miss!");
                    }

                    break;
                }
            }


        } //end of while loop

        return player;

    }



    public static boolean checkWin(boolean[][] player, boolean[][] opp) {

        for (int i = 0; i < player.length; i++) {
            for (int j = 0; j < player[0].length; j++) {

                if (player[i][j] != opp[i][j]) return false;


            } //end of nested for loop
        } //end of outer for loop


        return true;
    }



    public static void Game(boolean[][] player1, boolean[][] Guesses1, boolean[][] player2, boolean[][] Guesses2) {

        int turns = 0;

        while (true) {

            if (checkWin(Guesses1, player2)) {
                System.out.print("\n\n\nAll ships have been knocked down! Player 1 wins!");
                break;

            } else if (checkWin(Guesses2, player1)) {
                System.out.print("\n\n\nAll ships have been knocked down! Player 2 wins!");
                break;
            }


            if (turns % 2 == 0) {
                turn(Guesses1, player2, 1);

            } else {
                turn(Guesses2, player1, 2);
            }

            turns += 1;

        } //end of while loop

    }

}
