import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;
    private static int countPlayerTurn = 0;
    public static String[][] gameBoard = new String[ROWS][COLUMNS];
    private static  int countX = 0;
    private static int countO = 0;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        createBoard();
        printBoard();

        do {
            try {
                int[] coordinates = Arrays.stream(scanner.nextLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                if ((coordinates[0] < 1 || coordinates[0] > 3) || (coordinates[1] < 1 || coordinates[1] > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!gameBoard[coordinates[0] - 1][coordinates[1] - 1].equals(" ")) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else if (countPlayerTurn % 2 == 0) {
                    gameBoard[coordinates[0] - 1][coordinates[1] - 1] = "X";
                    countPlayerTurn++;
                    countX++;
                    printBoard();
                } else {
                    gameBoard[coordinates[0] - 1][coordinates[1] - 1] = "O";
                    countPlayerTurn++;
                    countO++;
                    printBoard();
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        } while (!verifyGameStatus());
    }

    public static void printBoard() {

        System.out.println("---------");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < gameBoard.length; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void createBoard() {

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = " ";
            }
        }
    }

    public static boolean verifyGameStatus() {

        boolean winX = verifyTheWinner("X");
        boolean winO = verifyTheWinner("O");

        if (Math.abs(countX - countO) > 1 || winO && winX) {
            System.out.println("Impossible");
            return true;
        } else if (winO) {
            System.out.println("O wins");
            return true;
        } else if (winX) {
            System.out.println("X wins");
            return true;
        } else if (countO + countX == 9) {
            System.out.println("Draw");
            return true;
        } else {
            return false;
        }
    }

    public static boolean verifyTheWinner(String player) {

        for (int i = 0; i < gameBoard.length; i++) {
            // Check row
            boolean check = true;
            for (int j = 0; j < gameBoard.length; j++) {
                if (!gameBoard[i][j].equals(player)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                return true;
            }

            // Check column
            check = true;
            for (int j = 0; j < gameBoard.length; j++) {
                if (!gameBoard[j][i].equals(player)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                return true;
            }
        }

        // Check diagonal
        boolean check = true;
        for (int i = 0; i < gameBoard.length; i++) {
            if (!gameBoard[i][i].equals(player)) {
                check = false;
                break;
            }
        }
        if (check) {
            return true;
        }

        // Check other diagonal
        check = true;
        for (int i = 0; i < gameBoard.length; i++) {
            if (!gameBoard[i][gameBoard.length - i - 1].equals(player)) {
                check = false;
                break;
            }
        }

        return check;
    }

}

