package com.troy.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date 04/06/2013
 * @author Tushar Roy
 *
 * Data structure to support following operations
 * extracMin - O(logn)
 * addToHeap - O(logn)
 * containsKey - O(1)
 * decreaseKey - O(logn)
 * getKeyWeight - O(1)
 *
 * Solution:
 * 1) It is a combination of binary heap(array<Node>) and hash map<key, Integer>
 * 2) Heap contains node object which has key(e.g. vertex number) and weight whereas map contains key and index in heap as value
 * 3) To extract min value, remove first element from heap and replace it with last element. Then heapify the array to children direction
 * 4) To add, add at the end of array and heapify to parent direction
 *
 */
public class BinaryMinHeap<T> {

    private List<Node> heap = new ArrayList<>();
    private Map<T,Integer> map = new HashMap<>();
        
    public class Node {
        int weight;
        T key;
    }

    /**
     * Checks where the key exists in heap or not
     */
    public boolean containsData(T key){
        return map.containsKey(key);
    }

    /**
     * Add key and its weight to they heap
     */
    public void add(int weight,T key) {
        Node node = new Node();
        node.weight = weight;
        node.key = key;
        heap.add(node);
        int size = heap.size();
        int current = size - 1;
        int parentIndex = (current - 1) / 2;
        map.put(node.key, current);

        while (parentIndex >= 0) {
            Node parentNode = heap.get(parentIndex);
            Node currentNode = heap.get(current);
            if (parentNode.weight > currentNode.weight) {
                swap(parentNode,currentNode);
                updatePositionMap(parentNode.key,currentNode.key,parentIndex,current);
                current = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Get the heap min without extracting the key
     */
    public T min(){
        return heap.get(0).key;
    }

    /**
     * Checks with heap is empty or not
     */
    public boolean empty(){
        return heap.size() == 0;
    }

    /**
     * Decreases the weight of given key to newWeight
     */
    public void decrease(T data, int newWeight){
        Integer position = map.get(data);
        heap.get(position).weight = newWeight;
        int parent = (position -1 )/2;
        while(parent >= 0){
            if(heap.get(parent).weight > heap.get(position).weight){
                swap(heap.get(parent), heap.get(position));
                updatePositionMap(heap.get(parent).key,heap.get(position).key,parent,position);
                position = parent;
                parent = (parent-1)/2;
            }else{
                break;
            }
        }
    }

    /**
     * Get the weight of given key
     */
    public Integer getWeight(T key) {
        Integer position = map.get(key);
        if( position == null ) {
            return null;
        } else {
            return heap.get(position).weight;
        }
    }

    /**
     * Returns the min node of the heap
     */
    public Node extractMinNode() {
        int size = heap.size() -1;
        Node minNode = new Node();
        minNode.key = heap.get(0).key;
        minNode.weight = heap.get(0).weight;

        int lastNodeWeight = heap.get(size).weight;
        heap.get(0).weight = lastNodeWeight;
        heap.get(0).key = heap.get(size).key;
        map.remove(minNode.key);
        map.remove(heap.get(0));
        map.put(heap.get(0).key, 0);
        heap.remove(size);

        int currentIndex = 0;
        size--;
        while(true){
            int left = 2*currentIndex + 1;
            int right = 2*currentIndex + 2;
            if(left > size){
                break;
            }
            if(right > size){
                right = left;
            }
            int smallerIndex = heap.get(left).weight <= heap.get(right).weight ? left : right;
            if(heap.get(currentIndex).weight > heap.get(smallerIndex).weight){
                swap(heap.get(currentIndex), heap.get(smallerIndex));
                updatePositionMap(heap.get(currentIndex).key,heap.get(smallerIndex).key,currentIndex,smallerIndex);
                currentIndex = smallerIndex;
            }else{
                break;
            }
        }
        return minNode;
    }
    /**
     * Extract min value key from the heap
     */
    public T extractMin(){
        Node node = extractMinNode();
        return node.key;
    }

    private void printPositionMap(){
        System.out.println(map);
    }

    private void swap(Node node1,Node node2){
        int weight = node1.weight;
        T data = node1.key;
        
        node1.key = node2.key;
        node1.weight = node2.weight;
        
        node2.key = data;
        node2.weight = weight;
    }

    private void updatePositionMap(T data1, T data2, int pos1, int pos2){
        map.remove(data1);
        map.remove(data2);
        map.put(data1, pos1);
        map.put(data2, pos2);
    }
    
    public void printHeap(){
        for(Node n : heap){
            System.out.println(n.weight + " " + n.key);
        }
    }
    
    public static void main(String args[]){
        BinaryMinHeap<String> heap = new BinaryMinHeap<String>();
        heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.add(2,"AFR");
        heap.decrease("Pramila", 1);
        heap.printHeap();
        heap.printPositionMap();
    }
}
