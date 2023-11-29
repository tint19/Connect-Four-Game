import java.util.*;

public class ConnectFour implements AbstractStrategyGame {
    private char[][] board;
    private boolean isPlayerATurn;

    // Constructs a new Connect Four game.
    public ConnectFour() {
        board = new char[][]{{'-', '-', '-','-', '-', '-', '-'},
                             {'-', '-', '-','-', '-', '-', '-'},
                             {'-', '-', '-','-', '-', '-', '-'},
                             {'-', '-', '-','-', '-', '-', '-'},
                             {'-', '-', '-','-', '-', '-', '-'},
                             {'-', '-', '-','-', '-', '-', '-'}};
        isPlayerATurn = true;
    }

    // Returns whether or not the game is over,
    // checks if a player has won or if the board is full.
    public boolean isGameOver() {
        return getWinner() >= 0 || isBoardFull();
    }

    // Returns the index of the winner of the game.
    // Player 1 (A) is 1, Player 1 (B) is 2, returns 0 if the game ends in a tie.
    // and -1 if the game is not over.

    public int getWinner() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                char player = board[row][col];
                if (player != '-') {
                    
                    //check horizontally
                    if (col + 3 < 7 && 
                            player == board[row][col + 1] &&
                            player == board[row][col + 2] &&
                            player == board[row][col + 3]) {
                        return player == 'A' ? 1 : 2;
                    }
                    //check vertically
                    if (row + 3 < 6 &&
                            player == board[row + 1][col] &&
                            player == board[row + 2][col] &&
                            player == board[row + 3][col]) {
                        return player == 'A' ? 1 : 2;
                    }
                    //check diagonally
                    if (row >= 3 && col + 3 < 7 &&
                            player == board[row - 1][col + 1] &&
                            player == board[row - 2][col + 2] &&
                            player == board[row - 3][col + 3]) {
                        return player == 'A' ? 1 : 2;
                    }
                
                    if (row + 3 < 6 && col + 3 < 7 &&
                            player == board[row + 1][col + 1] &&
                            player == board[row + 2][col + 2] &&
                            player == board[row + 3][col + 3]) {
                        return player == 'A' ? 1 : 2;
                    }
                }
            }
        }
        return -1;
    }

    // Checks for tie.
    private boolean isBoardFull() {
        for (int i = 0; i < 7; i++) {
            if (board[0][i] == '-') {
                return false;
            }
        }
        return true;
    }

    // Returns the index of which player's turn it is.
    // Player 1 (A) is 1, Player 2 (B) is 2, the game ends if there is a -1
    public int getNextPlayer() {
        return isPlayerATurn ? 1 : 2;
    }

    // Checks what player is making the move and gets input.
    // Depending on the input, an A or B will be placed in the respective column.
    // e.g. If player 1 inputs 0, it will place an A in column [0].
    // Valid inputs are between (0-6).
    public void makeMove(Scanner input) {
        char currentPlayer = isPlayerATurn ? 'A' : 'B';

        System.out.print("Column? ");
        int col = input.nextInt();

        makeMove(col, currentPlayer);
        isPlayerATurn = !isPlayerATurn;
    }

    // Checks if input is within the board bounds (0-6).
    // If the number is not in the board bounds it will throw an IllegalArgumentException.
    // Gathers input and places token in lowest possible spot in selected column.
    private void makeMove(int col, char player) {
        if (col < 0 || col >= 7) {
                throw new IllegalArgumentException("Invalid board position: " + col);
        }

        for (int row = 5; row >= 0; row--) {
            if (board[row][col] == '-') {
                board[row][col] = player;
                return;
            }
        }
            throw new IllegalArgumentException("Column is full: " + col);
    }
    
    // Returns instructions on how to play connect four.
    public String instructions() {
        String result = "";
        result += "Player 1 (A) will go first, and chooses where to play by choosing a column (0-6). \n";
        result += "A token will fall to the lowest possible position in the selected column. \n";
        result += "Player 2 (B) will go next, players will alternate turns until a winner is decided. \n";
        result += "To win, a player must connect four tokens either vertically, horizontally, or diagonally. \n";
        result += "In the case where there is no winner and the board is full the game ends in a tie.\n";
        result += "Tokens are represented by A's and B's. \n";
        return result;
    }

    // Returns the current state of the board.
    public String toString() {
        String result = "";
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
}