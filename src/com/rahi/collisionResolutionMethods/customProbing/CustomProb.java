package com.rahi.collisionResolutionMethods.customProbing;

import com.rahi.hashFunctions.CustomProbFunc;
import com.rahi.hashFunctions.HashFunction;

public class CustomProb {

    private final int HASH_TABLE_SIZE;
    private int currentSize;
    private int totalCollisionNumber;
    private int probing;
    private final HashNode[] table;
    private final HashFunction hashFunction;

    public CustomProb( int tableSize, HashFunction hashFunction ) {

        this.hashFunction = hashFunction;
        HASH_TABLE_SIZE = tableSize;
        table = new HashNode[HASH_TABLE_SIZE];

        currentSize = 0;
        totalCollisionNumber = 0;

        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int search( String key ) {

        int numberOfCollision = 0;

        int hashValue1 = CustomProbFunc.customHash(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);

        while (table[hashValue1] != null && !table[hashValue1].getKey().equals(key)) {
            probing++;
            numberOfCollision++;
            hashValue1 = CustomProbFunc.customHash(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);
        }

        return table[hashValue1].getValue();
    }

    public void insert( String key, int value ) {

        int numberOfCollision = 0;

        if (currentSize == HASH_TABLE_SIZE) {
            throw new IllegalStateException("Table is full");
        }

        int hashValue1 = CustomProbFunc.customHash(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);

        while (table[hashValue1] != null) {

            numberOfCollision++;

            hashValue1 = CustomProbFunc.customHash(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);

            totalCollisionNumber += numberOfCollision;
        }

        currentSize++;

        table[hashValue1] = new HashNode(key, value);
    }

    public void remove( String key ) {
        int numberOfCollision = 0;

        int hashValue1 = CustomProbFunc.customHash(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);

        while (table[hashValue1] != null && !table[hashValue1].getKey().equals(key)) {
            numberOfCollision++;
            hashValue1 = CustomProbFunc.customHash(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);
        }

        table[hashValue1] = null;

        currentSize--;
    }

    public int getTotalCollisionNumber() {
        return totalCollisionNumber;
    }

    public int getProbing() {
        return probing;
    }
}
