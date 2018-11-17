package lzhang.question.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/snake-ladder-problem-2/
 * 
 * By throwing dice, valued at 1 - 6 each time, one can move from starting
 * cell 0, to the final destination (n-1). For example, if the dice value is
 * 3, you can move by 3 cells from your current cell.
 * 
 * There are 2 special situations:
 * - ladder: if you end in a cell that is the base of a ladder, you have to climb
 *   up to wherever the ladder leads to;
 * - snake: if you land in a cell where snake head is, you would have to fall to
 *   to wherever its tail points to.
 *   
 * The problem for a given board size n and an array move[n] which tells which actual
 * cell you will end up with (due to ladders or snakes), find the minimum number of
 * dice throws that allows you to complete game.
 * 
 * @author lzhang
 *
 */
public class SnakeLadderGame extends BaseUtil {
    static class Cell {
    	// 0 - n-1 to name each cell
        int cellIndex;
        
        // keep track of number of throws so far to land in this cell
        int throwCount = 0;
        
        // optional - remember all of the dice face value for all past throws
        List<Integer> diceValues;
        
        public Cell(int key, int throwCount) {
            this.cellIndex = key;
            this.throwCount = throwCount;
            
            diceValues = new ArrayList<Integer>();
        }
        
        public void addAll(List<Integer> all) {
            diceValues.addAll(all);
        }
        
        public void addDiceValue(int diceValue) {
            diceValues.add(diceValue);
        }
        
        @Override
        public String toString() {
            return String.format("key: %d, throwCount: %d, diceValues: %s", cellIndex, throwCount, 
                    Arrays.toString(diceValues.toArray(new Integer[0])));
        }
    }
    
    public static void test() {
        SnakeLadderGame p = new SnakeLadderGame();
        
        int n = 30;
        int[] moves = new int[n];
        
        /*
         * By default every cell has value 0 meaning there is no additional
         * moves to be forced (no ladder or snake).
         */
        Arrays.fill(moves, 0);
        
        // specify ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;
     
        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;
        
        int minThrows = p.throwAndMove(moves, n);
        print("Minimum number of dice throws: " + minThrows);
    }

    /**
     * It's a kind of graph problem: cell (vertex) i could connect (move) to 6 other
     * vertices i+1, i+2, ..., j+6, one corresponds to one face value of the dice throw.
     * BFS will be used with a Queue.
     * 
     * But still don't understand why this is minimum number throws!
     * @param moves
     * @param n
     */
    private int throwAndMove(int[] moves, int n) {
        if(moves == null || n <= 0) return -1;
        
        // create queue for DFS
        Queue<Cell> queue = new LinkedList<Cell>();
        
        // keep track which have been visited alredy
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        
        // push the source vertex to queue
        queue.add( new Cell(0, 0) );
        visited[0] = true;
        
        int cellIndex;
        Cell v = null;
        while(!queue.isEmpty()) {
            v = queue.poll();
            if(v.cellIndex == n-1) {
                // reached destination;
                break;
            }
            
            // for each adjacent vertices (one for each dice face value)
            for(int i = v.cellIndex+1; i <= (v.cellIndex+6) && i < n; i++) {
                if(visited[i]) continue;
                
                // determine which cell to end up with
                cellIndex = (moves[i] == 0? i : moves[i]);
                if(cellIndex < 0 || cellIndex >= n) {
                	// don't move - always stay within the board
                    cellIndex = v.cellIndex;
                }
                
                Cell t = new Cell(cellIndex, v.throwCount + 1);
                
                // carry over all past throws
                t.addAll(v.diceValues);
                
                // plus this throw
                t.addDiceValue(i - v.cellIndex);
                
                queue.add( t );
                visited[i] = true;
            }
        }
        
        print(v.toString());
        return v.throwCount;
    }
}
