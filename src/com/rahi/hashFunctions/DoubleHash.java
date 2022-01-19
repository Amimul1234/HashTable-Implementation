package com.rahi.hashFunctions;

public class DoubleHash {
    public static int doubleHashGenerator( HashFunction hashFunction, String key, int i, int N ) {
        int index = (hashFunction.hashValue(key, N) + i * AuxHash.auxHashGenerator(key, N)) % N;

        index = index < 0 ? index * -1 : index;

        return index;
    }
}