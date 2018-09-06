package lzhang.question;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a an array of (positive) integers, find all possible combinations that
 * sum up to a given number.
 * 
 * For example, from array [3,9,8,4,5,7,10], find all combinations that sum to
 * 15
 * 
 * @author lzhang
 *
 */
public class SumFromCombination {

    public static void printCombinations(Integer[] arr, int target) {
        // find all combinations of 3 elements that sum to the target value
        printCombinations(new ArrayList<Integer>(Arrays.asList(arr)), target, 3, new ArrayList<Integer>());
    }

    /**
     * 
     * @param numbers
     * @param target
     * @param selected
     */
    private static void printCombinations(ArrayList<Integer> numbers, int target, int combinationLen, ArrayList<Integer> selected) {
        int selectedSum = 0;
        for (int x : selected) {
            selectedSum += x;
        }
        
        if (selected.size() > combinationLen || selectedSum > target) {
            // sum of this combination already overflowed
            return;
        }
        
        if (selected.size() == combinationLen && selectedSum == target) {
            // find a combination
            System.out.println("sum(" + Arrays.toString(selected.toArray()) + ")=" + target);
        }
        
        for (int i = 0; i < numbers.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<Integer>();
            
            // the remaining that are not chosen yet
            for (int j = i + 1; j < numbers.size(); j++) {
                remaining.add(numbers.get(j));
            }
            
            // choose the element at i
            Integer n = numbers.get(i);
            selected.add(n);
            
            // or clone the selected and add the chosen one to the clone
//            ArrayList<Integer> selected_rec = new ArrayList<Integer>(selected);
//            selected_rec.add(n);
//            printCombinations(remaining, target, combinationLen, selected_rec);
            
            printCombinations(remaining, target, combinationLen, selected);
            
            // unchosen the one from the original selected list unless a clone was used
            selected.remove(selected.size() - 1);
        }
    }
}
