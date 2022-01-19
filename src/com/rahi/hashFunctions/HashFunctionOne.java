package com.rahi.hashFunctions;

public class HashFunctionOne implements HashFunction {

    @Override
    public int hashValue( String key, int tableSize ) {
        int hash = 23;
        char[] chars = key.toCharArray();

        for (char c : chars)
            hash = hash * 31 + c;

        hash = hash < 0 ? hash * -1 : hash;

        return hash % tableSize;
    }
}
