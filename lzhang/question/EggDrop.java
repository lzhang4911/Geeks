package lzhang.question;

/**
 * n eggs, k floors, find the minimum number of tries to determine the critical
 * floor from which egg will start to break if dropped.
 * 
 * @author lzhang
 *
 */
public class EggDrop {
    public static int minTries() {
        int floors = 36;
        int eggs = 2;
        
        return dp(floors, eggs);
    }
    
    /**
     * FIXME: not working yet.
     * 
     * @param floors
     * @param eggs
     * @return
     */
    private static int dp(int eggs, int floors) {
        if(floors <= 0 || eggs <= 0) return 0;
        
        if(floors == 1) return 1;
        
        int mem[][] = new int[floors + 1][eggs + 1];
        
        for(int i = 0; i < floors; i++) {
            for(int j = 0; j < eggs; j++) {
                if(i == 0 || j == 0) {
                    mem[i][j] = 0;
                } else {
                    mem[i][j] = Math.min(
                            1 + dp(floors - 1, eggs - 1), // in case egg breaks from floor i
                            dp(floors + 1, eggs) // egg not broken
                            );
                }
            }
        }
        
        return mem[floors][eggs];
    }
}
