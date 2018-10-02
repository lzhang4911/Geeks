package lzhang.question.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/minimum-steps-reach-target-knight/
 * 
 * Given a chess board, the initial position the Knight is, the the target cell that the
 * Knight eventually needs be in, find the minimum step the Knight can perform.
 * 
 * Assume there is no cells that are block by others.
 * 
 * This is a typical un-weighted graph for the shortest path. 
 * @author lzhang
 *
 */
public class KnightMinSteps extends BaseUtil {
    static class Cell {
        int x;
        int y;
        
        // steps jumped from the source to current cell
        int steps;
        
        public Cell(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
        
        @Override
        public boolean equals(Object o) {
            if(o == null) return false;
            
            Cell that = (Cell)o;
            return (this.x == that.x && this.y == that.y);
        }
        
        @Override
        public String toString() {
            return String.format("(x=%d, y=%d, steps=%d)", x, y, steps);
        }
    }

    public static void test() {
        // the chess board is N x N
        int N = 30;
        
        // chess board. will be used for tracking as well
        boolean[][] chessBoard = new boolean[N][N];
        
        Cell startPos = new Cell(0, 0, 0);
        Cell targetPos = new Cell(N-1, N-1, 0);
        
        KnightMinSteps p = new KnightMinSteps();
        
        // FIXME: the recursion approach doesn't work yet
        chessBoard[0][0] = true;
        int steps = p.minStepsRecur(chessBoard, startPos, targetPos);
        print("Minimum steps jumped[minStepsRecur]: " + steps);
        
        // reset the chess board
        chessBoard[0][0] = true;
        for(int i = 0; i < N; i++) {
            Arrays.fill(chessBoard[i], false);
        }
        p.minStepsIter(chessBoard, startPos, targetPos);
    }
    
    /**
     * FIXME: Still not working!
     * 
     * The critical part i this solution is that the base condition which returns 0!
     * The number of steps is maintained by the increment at line 92
     *   steps = Math.min(steps, 1 + t);
     * 
     * @param chessBoard
     * @param curCell
     * @param targetCell
     */
    private int minStepsRecur(boolean[][] chessBoard, Cell curCell, Cell targetCell) {
        if(curCell.equals(targetCell)) {
            // already reached the destination, 0 jumps to complete!
            return 0;
        }
        
        int steps = Integer.MAX_VALUE;
        List<Cell> adj = getAdjacentCells(chessBoard, curCell);
        for(Cell c : adj) {
            chessBoard[c.x][c.y] = true;
            int t = this.minStepsRecur(chessBoard, c, targetCell);
            if(t != Integer.MAX_VALUE) {
                steps = Math.min(steps, 1 + t);
            }
        }
        
        return steps;
    }
    
    /**
     * BFS for the graph which gives the correct answer: 20 if N = 30.
     * 
     * How to we know this returns the minimum step? What if there are more
     * than one path? Because of BFS, the first one that reaches the destination
     * must come from the shortest path.
     * 
     * @param chessBoard
     * @param startCell
     * @param targetCell
     */
    private void minStepsIter(boolean[][] chessBoard, Cell startCell, Cell targetCell) {
        Queue<Cell> q = new LinkedList<>();
        q.add(startCell);
        chessBoard[startCell.x][startCell.y] = true;
        
        while(!q.isEmpty()) {
            Cell top = q.poll();
            
            if(top.equals(targetCell)) {
                // reached the destination
                print("Minimum steps jumped[minStepsIter]: " + top.steps);
                return;
            }
            
            // process its adjacency
            List<Cell> adj = getAdjacentCells(chessBoard, top);
            for(Cell c : adj) {
                chessBoard[c.x][c.y] = true;
                q.add(c);
            }
        }
    }
    
    /**
     * Make sure that
     * - this cell hasn't been visited yet;
     * - the cell is on the board.
     * 
     * @param chessBoard
     * @param x
     * @param y
     * @param steps
     * @return
     */
    private Cell cell(boolean[][] chessBoard, int x, int y, int steps) {
        int n = chessBoard.length;
        
        if((x < 0 || x >= n) || (y < 0 || y >= n) || chessBoard[x][y]) {
            return null;
        }
        
        return new Cell(x, y, steps);
    }
    
    /**
     * The adjacent cells for Knight at curCell in general has 8 cells around it.
     * 
     * @param curCell
     * @return
     */
    private List<Cell> getAdjacentCells(boolean[][] chessBoard, Cell curCell) {
        List<Cell> adj = new ArrayList<>();
        
        for(int i = 0; i < 8; i++) {
            // Note that the steps increment here is for iterative method only!
            Cell c = this.cell(chessBoard, curCell.x + dx[i], curCell.y + dy[i], curCell.steps + 1);
            if(c != null) {
                adj.add(c);
            }
        }
        
        return adj;
    }
    private static final int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
    private static final int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};
}
