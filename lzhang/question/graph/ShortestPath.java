package lzhang.question.graph;

import java.util.Arrays;

import lzhang.util.BaseUtil;

/**
 * Dijkstra’s shortest path algorithm
 * https://www.geeksforgeeks.org/?p=27697
 * 
 * Time complexity: O(V^2)
 * 
 * A more efficient approach is to use adjacent list and a MinHeap (PriorityQueue),
 * https://www.geeksforgeeks.org/greedy-algorithms-set-7-dijkstras-algorithm-for-adjacency-list-representation/
 * O(ELogV)
 * 
 * Given an un-directed graph and a source vertex, find the shortest path
 * from the source to every possible destination vertex in the group.
 * 
 * Obviously, the distance from the source to itself is 0.
 * 
 * This problem can be resolved using the greedy method with a simple 2-D
 * array to represent the graph.
 * 
 * Greedy is because each time we only choose the vertex that has the shortest
 * distance to travel to.
 * 
 * @author lzhang
 *
 */
public class ShortestPath extends BaseUtil {
    public static void test() {
        /*
         * Graph representation in 2-d array: element graph[1][7] whose value
         * is 11 means that from node 1 to node 7 is 11 in distance. Zero graph[i][j]
         * means there is path from node i to node j. 
         */
        int graph[][] = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
               };
        
        ShortestPath p = new ShortestPath();
        p.dijkstraMethod(graph, 0);
    }

    /**
     * Use 2 arrays to track both chosen vertices and distance of each vertex from the source.
     * 
     * @param arr
     * @param srcIndex
     */
    private void dijkstraMethod(int[][] arr, int srcIndex) {
        // total number of vertices
        int V = arr.length;
        
        /*
         * Shortest path tree: if spt[i] is true, it means node i is already on
         * the shortest path tree or its shortest distance from the src is 
         * finalized.
         */
        boolean[] spt = new boolean[V];
        Arrays.fill(spt, false);
        
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // initialize distance for the source node first
        dist[srcIndex] = 0;
        
        // Find shortest path for all vertices
        for(int i = 0; i < V-1; i++) { // why V-1?
            /*
             * Pick up the vertex that has the least distance (from someone including itself)
             * This node must NOT be included in spt yet. 
             */
            int u = getMinDistanceNode(arr, dist, spt);
            spt[u] = true;
            
            /*
             * If vertex u is the target that we find to find, we can break from here and
             * return dist[u].
             */
            
            // update distances to all its adjacent nodes
            int[] adj = arr[u];
            for(int v = 0; v < V; v++) {
                /*
                 * An adjacent node must be arr[u][v] > 0. Also make sure that
                 * the node v has not been included in the shortest path tree yet,
                 * and the from node u is already included.
                 */
                if( !spt[v] && adj[v] != 0 && dist[u] != Integer.MAX_VALUE ) {
                    // replace the value at v if coming from vertex u is shorter
                    dist[v] = Math.min(dist[u]+adj[v], dist[v]);
                }
            }
        }
        
        /*
         * By now all nodes are processed. Value dist[j] is the shortest distance from
         * source node srcIndex.
         */
        print( String.format("Shortest distance to each vertex from source %d is:", srcIndex));
        for(int i = 0; i < dist.length; i++) {
            print( String.format("From vertex %d to %d: %d", srcIndex, i, dist[i]) );
        }
    }
    
    /**
     * Find the index in dist[] with the least value and make sure that node is not
     * in spt[] yet
     * @param arr
     * @param dist
     * @param spt
     * @return
     */
    private int getMinDistanceNode(int[][] arr, int[] dist, boolean [] spt) {
        int min = Integer.MAX_VALUE;
        int pos = -1;
        
        for(int i = 0; i < dist.length; i++) {
            if(!spt[i] && dist[i] != Integer.MAX_VALUE) {
                if(dist[i] < min) {
                    pos = i;
                    min = dist[i];
                }
            }
        }
        
        return pos;
    }
}
