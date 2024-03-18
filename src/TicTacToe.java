import java.util.Scanner;

public class TicTacToe {
    //Global data items here so methods can see them
    private static final int ROW = 3; // tells me how many rows there are
    private static final int COL = 3; // tells me how many columns there are
    private static String board[][] = new String[ROW][COL]; // This is method

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String currentPlayer = "X"; //Set currentPlayer to "X" since X goes first in game
        int rowMove = 0;// rowMove and colMove  variable reflects the regular way of counting.
        int colMove = 0;
        int adjRowMove = 0;// adj"" variables are to reflect 0 based index numbering,
        int adjColMove = 0;
        int moveCnt =0; // counts the moves in the game to see when we should start checking for wins or ties
        boolean wantToPlay = false; // this boolean checks if you want to play again. Set to false since we assuming you don't
        boolean done = false; // this exits game loop. Set to false so it will loop. If set to true, it will break out of loop

        do { // This
            clearBoard(); // must be stated before showBoard method.Gives every index in 2d array a value of a space aka empty
            moveCnt = 0; // resets move count
            currentPlayer = "X"; // Resets player back to X
            do {     // this loops the actual tic-tac-toe game.
                showBoard(); // Shows the board and the spaces from clearBoard method.
                System.out.print("Enter your move player " + currentPlayer);
                rowMove = SafeInput.getRangedInt(in, "Enter the coordinates for the row", 1, 3);
                colMove = SafeInput.getRangedInt(in, "Enter the coordinates for the columns", 1, 3);
                moveCnt++; // Increments moveCnt

                adjRowMove = rowMove -1;
                adjColMove = colMove -1;


                if (isValidMove(adjRowMove,adjColMove))
                {
                    board[adjRowMove][adjColMove] = currentPlayer;

                    if (isWin(currentPlayer)){ // how do we check for wins after moveCnt > 5
                        showBoard();
                        System.out.println("Player " + currentPlayer + " wins!");
                        done = true;
                    }
                    if (isTie()){
                        showBoard();
                        System.out.println("Its a tie");
                        done = true;
                    }
                    if (currentPlayer.equals("X")){
                        currentPlayer = "O";
                    } else {
                        currentPlayer = "X";
                    }
                }
                else
                {
                    System.out.println("Invalid move. Try again.");
                }
            } while (!done);
            wantToPlay = SafeInput.getYNConfirm(in, "Are you done playing?");
        }while (!wantToPlay);


    }// <------This is the end of the main method
    // Support methods here
    private static void clearBoard(){ // this method sets all the board elements to a space to clear the board
        for (int row = 0; row < ROW; row++)
            for (int col = 0; col < COL; col++)
                board[row][col] = " "; // makes rows and columns have spaces in them
    }
    private static void showBoard(){
        for (int row = 0; row < ROW; row++ ) {
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col]);
                if (col < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col){
        return board[row][col].equals(" ");

    }
    private static boolean isWin(String player){
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)){
            return true;
        }
        return false;
    }

    private static boolean isColWin(String player){
        for (int col = 0; col < COL; col++){
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)){
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player){
        for (int row = 0; row < ROW; row++){
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)){
                return true;
            }
        }
        return false; //no row win
    }
    private static boolean isDiagonalWin(String player){
        for (int row = 0; row < ROW; row++){
            if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) || board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isTie() {
        // Check if all spaces on the board are filled
        boolean allSpacesFilled = true;
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" ")) {
                    allSpacesFilled = false;
                    break;
                }
            }
        }

        // Check if there is an X and an O in every win vector
        boolean allWinVectorsBlocked = true;
        boolean xWin = isWin("X");
        boolean oWin = isWin("O");

        if (!(xWin && oWin)) {
            allWinVectorsBlocked = false;
        }

        return allSpacesFilled || allWinVectorsBlocked;
    }



} // <------This is the end of the class