package com.rahi.hashFunctions;

public class CustomProb {
    public static int customHash( HashFunction hashFunction, String key, int i, int N ) {
        int C1 = 23;
        int C2 = 31;
        return (hashFunction.hashValue(key, N) + C1 * i * AuxHash.auxHashGenerator(key, N) + C2 * i * i) % N;
    }
}
