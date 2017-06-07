package com.troy.graph;

import java.util.*;

/**
 * https://leetcode.com/problems/clone-graph/
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
         
Solution:
1) clone current node and then do dfs
 */
public class CloneGraph {

    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
     };

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        Set<Integer> visited = new HashSet<>();
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        map.put(clone.label, clone);
        dfs(node, clone, map, visited);
        return clone;
    }

    private void dfs(UndirectedGraphNode current, UndirectedGraphNode clone, Map<Integer, UndirectedGraphNode> map,  Set<Integer> visited) {
        if (visited.contains(current.label)) {
            return;
        }
        visited.add(current.label);
        for (UndirectedGraphNode adj : current.neighbors) {
            if (adj.label != current.label) {
                UndirectedGraphNode adjClone = map.get(adj.label);
                if (adjClone == null) {
                    adjClone = new UndirectedGraphNode(adj.label);
                    map.put(adjClone.label, adjClone);
                }
                clone.neighbors.add(adjClone);
                dfs(adj, adjClone, map, visited);
            } else {
                clone.neighbors.add(clone);
            }
        }
    }
}
