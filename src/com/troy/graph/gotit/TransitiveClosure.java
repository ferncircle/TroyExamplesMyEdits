package com.troy.graph.gotit;

/**
 * http://www.geeksforgeeks.org/transitive-closure-of-a-graph/
 * 
 * Given a directed graph, find out if a vertex j is reachable from another vertex i for all vertex pairs (i, j) in the given 
 * graph. Here reachable mean that there is a path from vertex i to j. The reach-ability matrix is called transitive closure 
 * of a graph.

For example, consider below graph
transitiveclosure
Transitive closure of above graphs is 
     1 1 1 1 
     1 1 1 1 
     1 1 1 1 
     0 0 0 1 
The graph is given in the form of adjacency matrix say ‘graph[V][V]’ where graph[i][j] is 1 if there is an edge from vertex i 
to vertex j or i is equal to j, otherwise graph[i][j] is 0.

Floyd Warshall Algorithm can be used, we can calculate the distance matrix dist[V][V] using Floyd Warshall, if dist[i][j] is 
infinite, then j is not reachable from i, otherwise j is reachable and value of dist[i][j] will be less than V.
Instead of directly using Floyd Warshall, we can optimize it in terms of space and time, for this particular problem. 
Following are the optimizations:

1) Instead of integer resultant matrix (dist[V][V] in floyd warshall), we can create a boolean reach-ability matrix reach[V][V] 
(we save space). The value reach[i][j] will be 1 if j is reachable from i, otherwise 0.

2) Instead of using arithmetic operations, we can use logical operations. For arithmetic operation ‘+’, logical and ‘&&’ is 
used, and for min, logical or ‘||’ is used. (We save time by a constant factor. Time complexity is same though)
 */
public class TransitiveClosure {

    public boolean[][] getTransitiveClosure(int [][]graph){
        int rows = graph.length;
        int cols = graph[0].length;
        boolean[][] result = new  boolean[rows][cols];

        for(int i = 0; i < graph.length; i++){
            for(int j=0; j < graph.length; j++){
                if(graph[i][j] != 100){
                    result[i][j] = true;
                }
            }
        }
        for(int i=0; i < rows; i++){
            for(int j=0 ; j < rows; j++){
                for(int k=0; k < rows; k++){
                    result[i][j] = result[i][j] || (result[i][k] && result[k][j]);
                }
            }
        }
        return result;
    }
    
    public static void main(String args[]){
        TransitiveClosure closure = new TransitiveClosure();
        int[][] graph = {{0,2,2,4,100},{100,0,100,1,100},{100,100,0,3,100},{100,100,3,0,2},{100,3,100,100,0}};
        boolean result[][] = closure.getTransitiveClosure(graph);
        for(int i=0; i < result.length; i++){
            for(int j=0; j < result.length; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
