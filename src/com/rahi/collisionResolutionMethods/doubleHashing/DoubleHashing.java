package com.rahi.collisionResolutionMethods.doubleHashing;

import com.rahi.hashFunctions.AuxHash;
import com.rahi.hashFunctions.DoubleHash;
import com.rahi.hashFunctions.HashFunction;

public class DoubleHashing {

    private final int HASH_TABLE_SIZE;
    private int currentSize;
    private int totalCollisionNumber;
    private int probing;
    private final HashNode[] table;
    private final HashFunction hashFunction;

    public DoubleHashing( int tableSize, HashFunction hashFunction ) {

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

        int hashValue1 = DoubleHash.doubleHashGenerator(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);

        while (table[hashValue1] != null && !table[hashValue1].getKey().equals(key)) {
            probing++;
            numberOfCollision++;
            hashValue1 = DoubleHash.doubleHashGenerator(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);
        }

        return table[hashValue1].getValue();
    }

    public void insert( String key, int value ) {

        int numberOfCollision = 0;

        if (currentSize == HASH_TABLE_SIZE) {
            throw new IllegalStateException("Table is full");
        }

        int hashValue1 = DoubleHash.doubleHashGenerator(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);

        while (table[hashValue1] != null) {
            numberOfCollision++;
            hashValue1 = DoubleHash.doubleHashGenerator(hashFunction, key, numberOfCollision, HASH_TABLE_SIZE);
        }

        currentSize++;
        totalCollisionNumber += numberOfCollision;
        table[hashValue1] = new HashNode(key, value);
    }

    public void remove( String key ) {

        int hashValue1 = hashFunction.hashValue(key, HASH_TABLE_SIZE);
        int stepSize = AuxHash.auxHashGenerator(key, HASH_TABLE_SIZE);

        while (table[hashValue1] != null && !table[hashValue1].getKey().equals(key)) {
            hashValue1 += stepSize;
            hashValue1 %= HASH_TABLE_SIZE;
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
