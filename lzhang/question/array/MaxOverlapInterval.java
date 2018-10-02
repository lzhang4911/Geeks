package lzhang.question.array;

import java.util.Arrays;

import lzhang.util.BaseUtil;

/**
 * This is one of the common question such as the max number of flights in air
 * that I was interviewed at Google on 07/18/2018. Similar question would be to
 * ask the max number of quests at what time during a party.
 * 
 * Each individual has a start and end time. The duration among many individuals
 * over a period of time may or may not have overlaps. The question is to find
 * out when there are most of the overlaps and the count.
 * 

Example :

Input: arrl[] = {1, 2, 9, 5, 5}
       exit[] = {4, 5, 12, 9, 12}
First guest in array arrives at 1 and leaves at 4, 
second guest arrives at 2 and leaves at 5, and so on.

Output: 3/5
There are maximum 3 guests at time 5.  

 * 
 * Instead of working on a list of intervals directly
 * [1, 4], [2, 5], [9, 12], [5, 9], [5, 12]
 * we can treat both start and end arrays independently by sorting both of them
 * in increasing order. Then walk through the entire duration [min_start, max_end],
 * and see when there are the most overlaps.
 * 
 * @author lzhang
 *
 */
public class MaxOverlapInterval extends BaseUtil {
    public static void test() {
        // if intervals are given, convert them into 2 integer arrays
        int[] start = {1, 2, 9, 5, 5};
        int[] end = {4, 5, 12, 9, 12};
        
        MaxOverlapInterval p = new MaxOverlapInterval();
        p.findMaxOverlaps(start, end);
    }
    
    /**
     * This is one of the common question such as the max number of flights in air
     * that I was interviewed at Google on 07/18/2018. Similar question would be to
     * ask the max number of quests at what time during a party.
     * 
     * Each individual has a start and end time. The duration among many individuals
     * over a period of time may or may not have overlaps. The question is to find
     * out when there are most of the overlaps and the count.
     * 
     * Instead of working on a list of intervals directly
     * [1, 4], [2, 5], [9, 12], [5, 9], [5, 12]
     * we can treat both start and end arrays independently by sorting both of them
     * in increasing order. Then walk through the entire duration [min_start, max_end],
     * and see when there are the most overlaps.
     * 
     * @author lzhang
     *
     */
    private void findMaxOverlaps(int[] start, int[] end) {
        // sort both arrays
        sort(start);
        sort(end);
        
        // walk through all starts
        int cur_overlaps = 1;
        int max_overlaps = cur_overlaps;
        int when_max_overlaps = start[0];
        
        int j = 0;
        for(int i = 1; i < start.length; i++) {
            // one more started and it may be overlapping wither others
            cur_overlaps++;
            
            /*
             * Concurrent overlaps are determined by how many has ended BEFORE
             * the LAST one starts
             */
            if(start[i] > end[j]) {
                // one {more] has ended
                cur_overlaps--;
                j++;
            }
            
            if(cur_overlaps > max_overlaps) {
                max_overlaps = cur_overlaps;
                when_max_overlaps = start[i];
            }
        }
        
        print( String.format("Max overlaps: %d at %d", max_overlaps, when_max_overlaps));
    }
    
    private void sort(int[] arr) {
        Arrays.sort(arr);
    }
}
