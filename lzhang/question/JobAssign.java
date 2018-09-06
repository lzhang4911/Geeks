package lzhang.question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of jobs and the total number of machines. The time to complete
 * each job maybe different, how to assign these jobs so that the total completion
 * time is the least (or close to the optimal?).
 * 
 * I chose an approach that may NOT always be optimized, but very close! (Interview question from Addepar)
 * 
 * The optimized:
 * https://www.geeksforgeeks.org/find-minimum-time-to-finish-all-jobs-with-given-constraints/
 *  
 * @author lzhang
 *
 */
public class JobAssign {
    public static int test() {
//        int[] job = {3, 4, 5, 5, 6, 15};
        int[] job = {10, 7, 8, 12, 6, 8};
        
        // total number of machines to work on assigned jobs
        int assignees = 4;
        
        int res = better(job, assignees);
        res = mine(job, assignees);
        
        return res;
    }
    
    private static int better(int[] job, int assignees) {
        // low and upper limit of time to complete all jobs
        int minTime = getLongestJob(job), maxTime = getTotalSum(job);
        
        /*
         * Use kind of binary search approach to test if all of jobs can be
         * completed with given time (minTime + maxTime)/2 while both ends
         * are moving to each other closer and closer.
         */
        int midTime;
        while(true) {
            midTime = (minTime + maxTime)/2;
            
            if(isPossible(job, assignees, midTime)) {
                maxTime = midTime - 1;
            } else {
                minTime = midTime + 1;
            }
            
            if(minTime >= maxTime) {
                // best answer found
                return minTime;
            }
        }
    }
    
    private static int getTotalSum(int[] job) {
        int sum = 0;
        for(int j : job) {
            sum += j;
        }
        
        return sum;
    }
    
    private static int getLongestJob(int[] job) {
        int longest = job[0];
        for(int j = 1; j < job.length; j++) {
            if(job[j] > longest) {
                longest = job[j];
            }
        }
        
        return longest;
    }
    
    private static boolean isPossible(int[] job, int assignees, int time) {
        int workersNeeded = 1;
        int timeNeeded = 0;
        
        for(int j : job) {
            if(timeNeeded + j > time) {
                /*
                 * This worker was close to full and cannot handle this job without overtime. 
                 * Let's give this job to the next person.
                 */
                workersNeeded++;
                
                timeNeeded = j;
            } else {
                timeNeeded += j;
            }
        }
        
        return (workersNeeded <= assignees && timeNeeded <= time);
    }
    
    /**
     * Verify if it is possible for these number of assignees to finish
     * all of the jobs within give time.
     * @param args
     * @throws Exception
     */
    private static int mine(int[] job, int assignees) {
        // first sort the array
        Arrays.sort(job);
        
        // calculate the average that each machine should process
        int avg = getTotalSum(job) / assignees;
        System.out.println("avg: " + avg);
        
        int s = 0, e = job.length - 1;
        int subSum = 0;
        
        List<Integer> jobs = new ArrayList<>();
        int maxTime = 0;
        
        int pointer = e;
        
        while( assignees > 0) {
            // if it's reached the target or no more items to assign
            if(subSum >= avg || s > e) {
                print(jobs, assignees);
                
                if(subSum > maxTime) {
                    maxTime = subSum;
                }
                
                jobs.clear();
                subSum = 0;
                assignees--;
            } else {
                if(subSum + job[pointer] > avg) {
                    pointer = s;
                }
                
                jobs.add(job[pointer]);
                subSum += job[pointer];
                
                if(pointer == e) {
                    e--;
                } else {
                    s++;
                }
                
                // greedy always
                pointer = e;
            }
        }
        
        return maxTime;
    }
    
    private static void print(List<Integer> list, int groupId) {
        if(list == null) {
            return;
        }
        
        System.out.println("\n\nJob group " + groupId + ":");
        int sum = 0;
        for(Integer i : list) {
            sum += i;
            System.out.print(i + " ");
        }
        
        System.out.print(" (" + sum + ")");
    }
}