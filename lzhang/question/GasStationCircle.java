package lzhang.question;

/**
 * One a circular road, there are n gas stations. At each station, the amount of
 * gas that you can add is gas[i]. It takes an amount of gas cost[i] to move to the
 * next station. Find out the starting station from which you complete the entire 
 * circle (back to the starting station).
 * 
 * Assume the tank can hold infinite amount of gas.
 *   
 * @author lzhang
 *
 */
public class GasStationCircle {

    public static int test() {
        // expecting to start from station 3, the 4th station
//        int[] gas = {1,2,3,4,5};
//        int[] cost = {3,4,5,1,2};
        
        // expect an output -1, no such station to complete the circle
        int[] gas  = {2,3,4};
        int[] cost = {3,4,3};
        
        GasStationCircle p = new GasStationCircle();
        int result = p.findStartStation(gas, cost);
        
        return result;
    }
    
    /**
     * Try to start from each station and see which one can successfully
     * allow the driver to make a circle.
     * 
     * Return -1 if such a station doesn't exist.
     * 
     * @param gas
     * @param cost
     * @return
     */
    private int findStartStation(int[] gas, int[] cost) {
        for(int i = 0; i < gas.length; i++) {
            int curStation = i;
            int gasInTank = 0;
            int stationCount = 0;
            
            while(true) {
                if(stationCount == gas.length) {
                    // succeeded!
                    return i;
                }
                
                if(gasInTank + gas[curStation] < cost[curStation]) {
                    // This will not make it. Let's try a different starting station.
                    gasInTank = 0;
                    stationCount = 0;
                    
                    break;
                }
                
                gasInTank += gas[curStation] - cost[curStation];
                stationCount++;
                curStation++;
                
                // Note the index must be rotated
                curStation %= gas.length;
            }
        }
        
        return -1;
    }
}
