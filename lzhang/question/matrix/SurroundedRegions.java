package lzhang.question.matrix;

/**
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

*/
public class SurroundedRegions {
    public static void test() {
        char[][] arr = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        
        int rows = arr.length;
        int cols = arr[0].length;
        
        SurroundedRegions p = new SurroundedRegions();
        p.capture(arr, rows, cols);
        
        System.out.println("SurroundedRegions completed");
    }
    
    private void capture(char[][] arr, int rows, int cols) {
        for(int r = 0; r < rows; r++) { // rows
            boolean overturned = false;
            for(int c = 0; c < cols; c++) { // columns
                // flag to indicate whether this cell is a candidate to be captured eventually
                boolean candidate = arr[r][c] == 'O';
                
                if(candidate) {
                    // temporarily mark this cell
                    arr[r][c] = 'Y';
                    
                    // should we try to capture this now?
                    if(r == 0 || r == rows -1 || c == 0 || c == cols - 1 || // on one of the edges
                            arr[r-1][c] == 'O' || // the cell above was not capture - determined that should not be
                            arr[r][c-1] == 'O') { // the cell on the left side was intentionally not captured
                        overturned = true;
                    }
                }
                
                if(overturned) {
                    unmark(arr, r, c, rows, cols);
                    overturned = false;
                } else if(candidate) {
                    // temporarily mark any one below it so that we can either undo it or convert to 'X'
                    int i = r + 1;
                    while(arr[i][c] == 'O') {
                        arr[i][c] = 'Y';
                        i++;
                    }
                }
            }
        }
        
        // final capture
        finalCapture(arr, rows, cols);
    }
    
    /**
     * Convert any 'Y' back to 'O'.
     * Use the ripple effect to expand to following 3 directions:
     *  - up,
     *  - left, and
     *  - down
     *  
     * @param arr
     * @param row
     * @param col
     */
    private void unmark(char[][] arr, int row, int col, int rows, int cols) {
        if(row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }
        
        if(arr[row][col] == 'Y') {
            arr[row][col] = 'O';
        } else {
            return;
        }
        
        // moving up
        unmark(arr, row - 1, col, rows, cols);
        
        // moving to left
        unmark(arr, row, col - 1, rows, cols);
        
        // moving down
        unmark(arr, row + 1, col, rows, cols);
    }
    
    // change any 'Y' into 'X'
    private void finalCapture(char[][] arr, int rows, int cols) {
        for(int r = 0; r < rows; r++) { // rows
            for(int c = 0; c < cols; c++) { // columns
                if(arr[r][c] == 'Y') {
                    arr[r][c] = 'X';
                }
            }
        }
    }
}
