package lzhang.question;

import java.util.Arrays;

/**
 * Each job has 3 attributes:
 *    startTime
 *    endTime,
 *    profit
 *    
 * There are a list of such jobs among which some of them may overlap with each other.
 * The profit is not constant per duration. One cannot work on more than one jobs at
 * any given point in time. How can one maximize his profit by selecting certain jobs
 * to work on?
 * 
 * If multiple jobs are conflicting in time, it's natural to choose the one that offers
 * the most profit. My solution seems more reasonable!
 * 
 * @author lzhang
 *
 */
public class WeightedJobSchedule extends BaseUtil {
    static class Job implements Comparable<Job> {
        int start;
        int end;
        int profit;
        
        public Job(int s, int e, int p) {
            this.start = s;
            this.end = e;
            this.profit = p;
        }

        /**
         * Implement this interface to allow sorting on end time
         * @param j
         * @return
         */
        @Override
        public int compareTo(Job j) {
            if(j == null) return -1;
            
            if(this.end > j.end) return 1;
            if(this.end < j.end) return -1;
            return 0;
        }
        
        @Override
        public String toString() {
            return String.format("(%d, %d, %d)", start, end, profit);
        }
    }

    /**
     * To maximize profit, there are 2 situations:
     * - take all jobs that don't overlap;
     * - for jobs that do overlap, take the one that eventually yields the most.
     * 
     * So, a chosen-and-not-chosen algorithm.
     * 
     * This method assumes that the array jobs has been sorted already.
     * 
     * @param jobs
     * @param n the length of array
     * @return
     */
    private int findMaxProfit(Job[] jobs, int startIndex, int n) {
        if(n <= 0 || startIndex >= n) return 0;
        if(n == 1) return jobs[n-1].profit;
        
        if(jobs[startIndex].end <= jobs[startIndex+1].start) {
            /*
             * The current job does not overlap with the next job. It's safe
             * to take the current job.
             */
            return jobs[startIndex].profit + this.findMaxProfit(jobs, startIndex + 1, n);
        } else {
            /*
             * It''s already known that the current job overlaps with the next one. For all
             * consecutive jobs that are overlapping with each other, we can only take one
             * job that obviously shall have the greatest profit.
             */
            int maxProfit = jobs[startIndex].profit;
            int maxEndTime = jobs[startIndex].end;
            
            int nextOverlapIndex = startIndex + 1;
            while(nextOverlapIndex < n) {
                if(maxEndTime <= jobs[nextOverlapIndex].start) {
                    // no overlap any more
                    break;
                } else {
                    // choose the max profit
                    maxProfit = Math.max(maxProfit, jobs[nextOverlapIndex].profit);
                    
                    /*
                     * Whether the next job is in conflict or not is determined by whether
                     * it overlaps with current or previous one. An effective trick is to
                     * get the newest ending time.
                     */
                    maxEndTime = Math.max(jobs[nextOverlapIndex-1].end, jobs[nextOverlapIndex].end);
                    
                    nextOverlapIndex++;
                }
            }
            
            return maxProfit + this.findMaxProfit(jobs, nextOverlapIndex, n);
        }
    }
    
    /**
     * Copied from https://www.geeksforgeeks.org/weighted-job-scheduling/
     * @param jobs
     * @param n
     * @return
     */
    private int findMaxProfit_1(Job[] jobs, int n) {
        if(n == 1) return jobs[n-1].profit;
        
        int includeThis = jobs[n-1].profit;
        int index = latestNonConflict(jobs, n);
        if(index != -1) {
            includeThis += this.findMaxProfit_1(jobs, index+1);
        }
        
        // or exclude previous one but include the current one
        int excludeThis = this.findMaxProfit_1(jobs, n-1);
        
        // whichever gives the best
        return Math.max(includeThis, excludeThis);
    }
    
    private void printJobs(Job[] arr) {
        print("Sorted jobs: ");
        
        for(Job j:  arr) {
            System.out.print(j.toString() + " ");
        }
        print("");
    }
    
 // Find the latest job (in sorted array) that doesn't
 // conflict with the job[i]. If there is no compatible job,
 // then it returns -1.
 int latestNonConflict(Job arr[], int i) {
     for (int j=i-1; j>=0; j--) {
         if (arr[j].end <= arr[i-1].start) {
             return j;
         }
     }
     
     return -1;
 }
    
    public static void test() {
        WeightedJobSchedule p = new WeightedJobSchedule();
        
        Job arr[] = {
                new Job(3, 10, 20), 
                new Job(1, 2, 50), 
                new Job(6, 19, 100),
                new Job(2, 100, 200)
                };
        
        // sort the array based on end time
        Arrays.sort(arr);
        p.printJobs(arr);
        
        int ret = p.findMaxProfit(arr, 0, arr.length);
        print("Max profit: " + ret);
    }
}
