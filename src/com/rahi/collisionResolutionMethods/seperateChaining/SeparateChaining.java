package com.rahi.collisionResolutionMethods.seperateChaining;

import com.rahi.hashFunctions.HashFunction;

import java.util.ArrayList;

public class SeparateChaining {

    private final int numberOfNodes;
    private int currentSize;
    private final HashFunction hashFunction;
    private final ArrayList<HashNode> hashNodes;

    public SeparateChaining( int numberOfNodes, HashFunction hashFunction ) {

        this.numberOfNodes = numberOfNodes;
        this.hashFunction = hashFunction;

        currentSize = 0;

        hashNodes = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++)
            hashNodes.add(null);
    }


    public void insert( String key, int value ) {

        int index = hashFunction.hashValue(key, numberOfNodes);
        HashNode headNode = hashNodes.get(index);

        currentSize++;
        HashNode newNode = new HashNode(key, value, index);

        hashNodes.set(index, newNode);
        newNode.setNextNode(headNode);
    }

    public Integer search( String key ) {

        int index = hashFunction.hashValue(key, numberOfNodes);
        HashNode headNode = hashNodes.get(index);

        while (headNode != null) {
            if (headNode.getKey().equals(key) && headNode.getHashValue() == index)
                return headNode.getValue();

            headNode = headNode.getNextNode();
        }

        return null;
    }

    public Integer remove( String key ) {

        int bucketIndex = hashFunction.hashValue(key, numberOfNodes);

        HashNode head = hashNodes.get(bucketIndex);
        HashNode previousNode = null;

        while (head != null) {

            if (head.getKey().equals(key) && bucketIndex == head.getHashValue())
                break;

            previousNode = head;
            head = head.getNextNode();
        }

        if (head == null)
            return null;

        currentSize--;

        if (previousNode != null)
            previousNode.setNextNode(head.getNextNode());
        else
            hashNodes.set(bucketIndex, head.getNextNode());

        return head.getValue();
    }


    public int size() {
        return currentSize;
    }

    public int numberOfCollisions() {

        int numberOfOccupiedBucket = 0;

        for (HashNode hashNode : hashNodes) {
            if (hashNode != null)
                numberOfOccupiedBucket++;
        }

        return currentSize - numberOfOccupiedBucket;
    }
}
