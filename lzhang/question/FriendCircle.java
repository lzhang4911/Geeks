package lzhang.question;

/**
 * Given N students in a classroom. There might be a few small groups (circles)
 * of students as close friends. If A is a friend of B and B is a friend of C, then
 * A and C are friends as well (transitive).
 * 
 * The friend circle can be depicted using N x N matrix:
 * 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
 *
 * Noticed that every value on the diagonal is always 1, that simply means that
 * is person is always a friend of himself. Person 1 and 2 are friends. The 3rd
 * person is a friend of himself. So, there are 2 friend circles there.
 * 
 * @author lzhang
 *
 */
public class FriendCircle {
    public static int test() {
        // friend matrix
//        int[][] m = {
//                {1,1,0},
//                {1,1,1},
//                {0,1,1}
//        };
        
        int[][] m = {
                {1,1,0},
                {1,1,0},
                {0,0,1}
        };
        
        FriendCircle p = new FriendCircle();
        int result = p.findCircle(m);
        
        return result;
    }
    
    
    /**
     * Here is the idea:
     * 
     * 1 scan rows from top to bottom on rows;
     * 2 scan from left to right for each given row;
     * 3 increment count whenever 1 is encountered during the scan, plus doing the followings:
     *   - mark the cell to -1 (meaning processed already);
     *   - crawl to left/right/downward as far as possible;
     * Repeat 1-2-3.
     *   
     * @param m
     * @return
     */
    private int findCircle(int[][] m) {
        int count = 0;
        int M = m.length;
        
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                if(m[i][j] == 1) {
                    count++;
                    
                    crawl(m, i, j, M);
                }
            }
        }
        
        return count;
    }
    
    private void crawl(int[][] m, int row, int col, int M) {
        if(row < 0 || row >= M || col < 0 || col >= M) {
            // reached boundary already
            return;
        }
        
        if(m[row][col] < 1) {
            // either 0 or -1: no need to process
            return;
        } else {
            m[row][col] = -1;
            
            crawl(m, row, col-1, M); // toward left
            crawl(m, row, col+1, M); // toward right
            crawl(m, row+1, col, M); // downward
        }
    }
}
