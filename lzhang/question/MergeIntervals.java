package lzhang.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/*
 * Given a list of overlapping intervals, how to merge them.
 * Intervals (2, 5) and (4, 7) shall be merged into (2, 7)
 */
public class MergeIntervals {
    static class Interval {
        public int start;
        public int end;
        
        public Interval(int s, int e) { start = s; end = e; }
        
        @Override
        public String toString() {
            return String.format("[%d, %d]", start, end);
        }
    }

    public static void test() {
        // Create a list of overlapping intervals
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 4));
        list.add(new Interval(2, 3)); // completely covered by previous
        list.add(new Interval(3, 6)); // partially overlapping over 1st one
        list.add(new Interval(5, 9)); // partially overlapping with the merged one (1, 6) to become (1, 9)
        list.add(new Interval(11, 15));
        list.add(new Interval(14, 18)); // merge with previous one as (11, 18)
        
        MergeIntervals p = new MergeIntervals();
        // works
        //p.merge(list);
        
        p.mergeWithStack(list);
        
        for(Interval i : list) {
            System.out.print("(" + i.start + ", " + i.end + ") ");
        }
        
        System.out.println("end");
    }
    
    /**
     * Time in O(logn) for sorting, constant space.
     * 
     * To merge overlapping intervals, we must first sort the list according to
     * their start value. Then find overlapping pairs to merge.
     * 
     * If the start time of the second interval resides within the (start, end) of
     * the first interval, these 2 intervals are overlapping.
     *  
     * @param arr
     */
    private void merge(List<Interval> list) {
        if(list == null) {
            return;
        }
        
        // first sort the list on their start time
        Collections.sort(list, new Comparator<Interval>() {

            @Override
            public int compare(Interval i1, Interval i2) {
                if(i1 == null && i2 == null) {
                    return 0;
                } else if(i1 != null && i2 != null) {
                    if(i1.start > i2.start) {
                        return 1;
                    } else if(i1.start == i2.start) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else if(i1 != null) {
                    return 1;
                } else {
                    return -1;
                }
            }
            
        });
        
        int index = 0;
        
        // ensure there are at least 2 elements left
        while(index < list.size() - 1) {
            // get the next 2 consecutive elements from the list
            Interval a = list.get(index++);
            Interval b = list.get(index);
            

            if(a.start <= b.start && b.end <= a.end) {
                // a completely covers b then delete b
                list.remove(index--);
            } else if(a.start <= b.start && b.start < a.end) {
                // a and b overlaps some 
                list.remove(index--);
                list.remove(index);
                
                // replace with the merged one
                list.add(index, new Interval(a.start, b.end));
            }
        }
    }
    
    /**
     * Time in O(logn) for sorting, O(n) for space
     * @param list
     */
    private void mergeWithStack(List<Interval> list) {
        if(list == null) {
            return;
        }
        
        // first sort the list on their start time
        Collections.sort(list, new Comparator<Interval>() {

            @Override
            public int compare(Interval i1, Interval i2) {
                if(i1 == null && i2 == null) {
                    return 0;
                } else if(i1 != null && i2 != null) {
                    if(i1.start > i2.start) {
                        return 1;
                    } else if(i1.start == i2.start) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else if(i1 != null) {
                    return 1;
                } else {
                    return -1;
                }
            }
            
        });
        
        Interval cur;
        Stack<Interval> s = new Stack<>();
        
        // push the first one into satck
        s.push(list.get(0));
        
        // ensure there are at least 2 elements left
        for(int index = 1; index < list.size(); index++) {
            Interval top = s.peek();
            cur = list.get(index);
            
            if(top.end < cur.start) {
                // no overlapping
                s.push(cur);
            } else if(top.end >= cur.start) {
                // 2 intervals are interleaved
                top.end = cur.end;
            }
        }
        
        list.clear();
        while(!s.isEmpty()) {
            list.add(0, s.pop());
        }
    }
}
