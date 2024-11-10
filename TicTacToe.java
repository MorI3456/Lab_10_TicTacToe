import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain;

        do {
            // Clear the board for a new game
            clearBoard();
            int moveCount = 0;
            boolean gameWon = false;

            // Loop until the game is won or all moves are made
            while (moveCount < ROWS * COLS && !gameWon) {
                // Display the current state of the board
                display();
                int row, col;
                do {
                    // Get valid move from the current player
                    row = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                    col = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3) - 1;
                } while (!isValidMove(row, col));

                // Record the move on the board
                board[row][col] = currentPlayer;
                moveCount++;

                // Check if the current player has won or if the game is a tie
                if (moveCount >= 5) { // Minimum moves required for a win is 5
                    gameWon = isWin(currentPlayer);
                    if (gameWon) {
                        // Display the board and announce the winner
                        display();
                        System.out.println("Player " + currentPlayer + " wins!");
                    } else if (isTie()) {
                        // Display the board and announce a tie
                        display();
                        System.out.println("The game is a tie!");
                        break;
                    }
                }

                // Toggle the player for the next turn
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            }

            // Ask if the players want to play again
            playAgain = SafeInput.getYNConfirm(console, "Would you like to play again? (Y/N): ");
        } while (playAgain);
    }

    // Clear the board by setting all cells to a space
    private static void clearBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    // Display the current state of the board
    private static void display() {
        System.out.println("\n  1   2   3");
        for (int i = 0; i < ROWS; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j]);
                if (j < COLS - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < ROWS - 1) System.out.println(" ---+---+---");
        }
        System.out.println();
    }

    // Check if the move at the given row and column is valid (i.e., the cell is empty)
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    // Check if the current player has won the game
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    // Check if the current player has a winning row
    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Check if the current player has a winning column
    private static boolean isColWin(String player) {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Check if the current player has a winning diagonal
    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
               (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    // Check if the game is a tie (i.e., all cells are filled and there is no winner)
    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}


