package lzhang.question.array;

import java.util.ArrayList;
import java.util.List;

import lzhang.util.BaseUtil;

/**
 * When the buildings in a city are projected to an imaginary canvas behind,
 * what you see is a counter line of the tall buildings.
 * 
 * Lower buildings that are completely overlapping with tall ones are not contributing
 * to any part of the overall skyline.
 * 
 * A building can be described as (left, height, right).
 * Each piece of skyline is a rectangle depicted by just (x, and height). You can
 * consider infinite for its right attribute.
 *
 * The solution is to 
 * - divide the building list into 2 halves;
 * - for each list, decompose each each building into 2 strips (skylines);
 * 
 * For example for the first half of the buildings {(1,11,5), (2,6,7), (3,13,9), (12,7,16)},
 * the strips will be (1,11), (5,0), (2,6), (7,0), (3,13), (9,0), (12,7), (16,0).
 * After sorting on x-coordinate, (1,11), (2,6), (3,13), (5,0), (7,0), (9,0), (12,7), (16,0).
 * Merge those consecutive stripes with same height (0), (1,11), (2,6), (3,13), (9,0), (12,7), (16,0).
 * Remove the second one - shadow, (1,11), (3,13), (9,0), (12,7), (16,0).
 * 
 * At the end, merge sort the 2 skylines.
 * 
 * Why do the merge sort?
 * https://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
 * 
 * Let me use my method decomposeAndSort()
 * 
 * @author lzhang
 *
 */
public class CitySkyline extends BaseUtil {
    static class Building {
        int left, right, height;
        
        public Building(int left, int right, int height) {
            this.left = left;
            this.right = right;
            this.height = height;
        }
        
        @Override
        public String toString() {
            return String.format("(%d, %d, %d)", left, right, height);
        }
    }
    
    static class Skyline {
        int x, height;
        
        public Skyline(int x, int h) {
            this.x = x;
            this.height = h;
        }
        
        @Override
        public String toString() {
            return String.format("(%d, %d)", x, height);
        }
    }
    
    public static void test() {
        /*
         * Buildings: (left, height, right)
         * {{1, 11, 5}, {2, 6, 7}, {3, 13, 9}, {12, 7, 16}, {14, 3, 25}, {19, 18, 22}, {23, 13, 29}, {24, 4, 28}};
         * 
         * Expects:
         * (1, 11),  (3, 13),  (9, 0),  (12, 7),  (16, 3),  (19, 18), (22, 3),  (23, 13),  (29, 0)
         */
        List<Building> buildings = new ArrayList<>();
        
        int[][] arr = {{1, 11, 5}, {2, 6, 7}, {3, 13, 9}, {12, 7, 16}, {14, 3, 25}, {19, 18, 22}, {23, 13, 29}, {24, 4, 28}};
        for(int i = 0; i < arr.length; i++) {
            buildings.add( new Building(arr[i][0], arr[i][2], arr[i][1]));
        }
        
        CitySkyline p = new CitySkyline();
        List<Skyline> result = p.mergeSort(buildings, 0, buildings.size() - 1);
        
        print("Skyline using mergeSort: ");
        for(Skyline s : result) {
            System.out.print(s.toString() + " ");
        }
    }
    
    /**
     * This one works.
     * https://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
     * 
     * @param buildings
     * @param l
     * @param h
     * @return
     */
    private List<Skyline> mergeSort(List<Building> buildings, int l, int h) {
        if(buildings == null || l < 0 || h >= buildings.size()) {
            return null;
        }
        
        List<Skyline> result = new ArrayList<>();
        if(l == h) {
            // here is the meat - decomposing this building into 2 strips!
            Building b = buildings.get(l);
            result.add( new Skyline(b.left, b.height) );
            result.add( new Skyline(b.right, 0) );
            
            return result;
        }
        
        int mid = (l + h)/2;
        List<Skyline> left = this.mergeSort(buildings, l, mid);
        List<Skyline> right = this.mergeSort(buildings, mid + 1, h);
        
        
        return merge(left, right);
    }
    
    private List<Skyline> merge(List<Skyline> left, List<Skyline> right) {
        if(left == null) return right;
        if(right == null) return left;
        
        List<Skyline> result = new ArrayList<>();
        
        // indexes from each branch
        int l = 0, r = 0;
        
        // To store current heights of two branches
        int h1 = 0, h2 = 0;
        
        // final skyline (x, y)
        int x, maxh;
        
        Skyline ls, rs;
        while(l < left.size() && r < right.size()) {
            ls = left.get(l);
            rs = right.get(r);
            
            if(ls.x < rs.x) {
                x = ls.x;
                h1 = ls.height;
     
                // Choose height as max of two heights
                maxh = Math.max(h1, h2);
                l++;
            } else {
                x = rs.x;
                h2 = rs.height;
     
                // Choose height as max of two heights
                maxh = Math.max(h1, h2);
                r++;
            }
            
            addSkyline(result, new Skyline(x, maxh));
        }
        
        // in case any branch still has anything
        while(l < left.size()) {
            addSkyline(result, left.get(l++) );
        }
        
        while(r < right.size()) {
            addSkyline(result, right.get(r++) );
        }
        
        return result;
    }
    
    private void addSkyline(List<Skyline> result, Skyline s) {
        if(result.isEmpty()) {
            result.add(s);
            return;
        }
        
        Skyline prev = result.get( result.size() - 1 );
        
        // Check for redundant Skyline, a Skyline is
        // redundant if it has same height or x as previous
        if (prev.height == s.height) {
        	// no need to add the second one
            return;
        }
        
        if (prev.x == s.x) {
        	// only need one but update the height to their max
            prev.height = Math.max(prev.height, s.height);
            return;
        }
        
        // add this one since it's none of the either situation above
        result.add(s);
    }
}
