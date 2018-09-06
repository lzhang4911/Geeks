package lzhang.question;

public class WaterJar {
    /*
     * Given a tank with capacity C liters which is completely filled in starting. Everyday tank is filled with l liters of water and in the case of overflow extra water is thrown out. Now on i-th day i liters of water is taken out for drinking. We need to find out the day at which tank will become empty the first time.
     *
     */
    public static int daysToEmptyJar(int capacity, int dailyAddition) {
        int remains = capacity;
        int day = 1;
        
        while(true) {
            if(remains <= day) {
                break;
            }
            
            remains -= day;
            
            // prepare to move to next day
            day++;
            remains = Math.min(remains + dailyAddition, capacity);
        }
        
        return day;
    }
}
