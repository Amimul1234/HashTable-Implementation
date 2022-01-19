package com.rahi.hashFunctions;

public class AuxHash {
    public static int auxHashGenerator( String key, int HASH_TABLE_SIZE ) {
        int hash = (key.hashCode() * 3) % HASH_TABLE_SIZE;
        hash = hash < 0 ? hash * -1 : hash;
        return hash;
    }
}
