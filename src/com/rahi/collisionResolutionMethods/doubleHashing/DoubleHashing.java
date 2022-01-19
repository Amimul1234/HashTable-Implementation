package com.rahi.collisionResolutionMethods.doubleHashing;

import com.rahi.hashFunctions.AuxHash;
import com.rahi.hashFunctions.HashFunction;

class DoubleHashing {

    private final int HASH_TABLE_SIZE;
    private int currentSize;
    private int collisionNumber;
    private int probing;
    private final HashNode[] table;
    private final HashFunction hashFunction;

    public DoubleHashing( int tableSize, HashFunction hashFunction ) {

        this.hashFunction = hashFunction;
        HASH_TABLE_SIZE = tableSize;
        table = new HashNode[HASH_TABLE_SIZE];

        currentSize = 0;
        collisionNumber = 0;

        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int search( String key ) {

        int firstHashValue = hashFunction.hashValue(key, HASH_TABLE_SIZE);
        int stepSize = AuxHash.auxHashGenerator(key, HASH_TABLE_SIZE);

        while (table[firstHashValue] != null && !table[firstHashValue].getKey().equals(key)) {
            probing++;
            firstHashValue += stepSize;
            firstHashValue %= HASH_TABLE_SIZE;
        }

        return table[firstHashValue].getValue();
    }

    public void insert( String key, int value ) {

        if (currentSize == HASH_TABLE_SIZE) {
            throw new IllegalStateException("Table is full");
        }

        int hashValue1 = hashFunction.hashValue(key, HASH_TABLE_SIZE);
        int stepSize = AuxHash.auxHashGenerator(key, HASH_TABLE_SIZE);

        while (table[hashValue1] != null) {
            collisionNumber++;
            hashValue1 += stepSize;
            hashValue1 %= HASH_TABLE_SIZE;
        }

        currentSize++;
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

    public int getCollisionNumber() {
        return collisionNumber;
    }

    public int getProbing() {
        return probing;
    }
}
