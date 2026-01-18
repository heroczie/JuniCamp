public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        int[][] board = {
                {0, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 0, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 0},
                {4, 0, 0, 8, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 2, 0, 0, 0, 0},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 0, 0, 0, 7, 0}
        };

        int[][] board2 = {
                {0, 2, 0, 0, 3, 0, 0, 4, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 4, 0, 0, 0, 5, 0, 0},
                {0, 0, 0, 8, 0, 6, 0, 0, 0},
                {8, 0, 0, 0, 1, 0, 0, 0, 6},
                {0, 0, 0, 7, 0, 5, 0, 0, 0},
                {0, 0, 7, 0, 0, 0, 6, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 0, 8},
                {0, 3, 0, 0, 4, 0, 0, 2, 0}
        };

        int[][] unsolvableBoard = {
                {7, 2, 0, 0, 3, 0, 0, 4, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 4, 0, 0, 0, 5, 0, 0},
                {0, 0, 0, 8, 0, 6, 0, 0, 0},
                {8, 0, 0, 0, 1, 0, 0, 0, 6},
                {0, 0, 0, 7, 0, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 6, 0, 0},
                {4, 0, 0, 0, 0, 0, 0, 0, 8},
                {0, 5, 0, 0, 4, 0, 0, 2, 0}
        };

        if (solve(unsolvableBoard)) {
            System.out.println("Solved! :)");
            printBoard(unsolvableBoard);
        }
        else {
            System.out.println("Unsolvable :(");
            printBoard(unsolvableBoard);
        }

    }

    private static boolean solve(int[][] board) {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (board[r][c] == 0) {
                    for (int n = 1; n <= GRID_SIZE; n++) {
                        if(isPlaceable(board, n, r, c)) {
                            board[r][c] = n;

                            if (solve(board)) {
                                return true;
                            }
                            else {
                                board[r][c] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int col) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInSection(int[][] board, int number, int row, int col) {
        //megállapítjuk, hogy melyik sectionben van az adott szám - a section bal felső sarkát számoljuk ki
        int sectionFirstRow = row - row % 3;
        int sectionFirstColumn = col - col % 3;

        //végigmegyünk a sectionben minden mezőn
        for (int r = sectionFirstRow; r < sectionFirstRow + 3; r++) {
            for (int c = sectionFirstColumn; c < sectionFirstColumn + 3; c++) {
                if (board[r][c] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isPlaceable(int[][] board, int number, int row, int col) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, col) &&
                !isNumberInSection(board, number, row, col);
    }

    private static void printBoard(int[][] board) {
        for (int r = 0; r < GRID_SIZE; r++) {
            //minden negyedik sor elé beszúrunk vízszintes vonalakat
            if (r % 3 == 0 && r != 0) {
                System.out.println("-----------");
            }
            for (int c = 0; c < GRID_SIZE; c++) {
                //minden negyedik oszlop elé beszúrunk egy függőleges vonalat
                if (c % 3 == 0 && c != 0) {
                    System.out.print("|");
                }
                System.out.print(board[r][c]);
            }
            System.out.println();

        }
    }
}