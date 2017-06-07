package com.troy.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tusroy
 * Date 06/20/2015
 *  
 * Video link - https://youtu.be/ID00PMy0-vE
 *  
 * Solution:
 * Disjoint sets using path compression and union by rank
 * Supports 3 operations
 * 1) makeSet(a): create a Node object a with data and rank=0 and parent to itself
 * 2) union(a,b): Call findSets for a and b to get the representative node(root of that tree) of them. Then compare the rank
 * 		the one with higher rank becomes root. If ranks are same then make any node as parent and increment rank by 1 
 * 3) findSet(a): find the root node (representative) of node a.
 * 
 * For m operations and total n elements time complexity is O(m*f(n)) where f(n) is 
 * very slowly growing function. For most cases f(n) <= 4 so effectively
 * total time will be O(m). Proof in Coreman book.
 */
public class DisjointSet {

    private Map<Long, Node> map = new HashMap<>();

    class Node {
        long data;
        Node parent;
        int rank;
    }

    /**
     * Create a set with only one element.
     */
    public void makeSet(long data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    /**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if data1 and data2 are in different set before union else false.
     */
    public boolean union(long data1, long data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        //if they are part of same set do nothing
        if (parent1.data == parent2.data) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    /**
     * Finds the representative of this set
     */
    public long findSet(long data) {
        return findSet(map.get(data)).data;
    }

    /**
     * Find the representative recursively and does path
     * compression as well.
     */
    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }

    public static void main(String args[]) {
        DisjointSet ds = new DisjointSet();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));
    }
}