package com.rahi.hashFunctions;

public class DoubleHash {
    public static int doubleHashGenerator( HashFunction hashFunction, String key, int i, int N ) {
        return (hashFunction.hashValue(key, N) + i * AuxHash.auxHashGenerator(key, N)) % N;
    }
}
