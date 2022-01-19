package com.rahi.hashFunctions;

public class HashFunctionTwo implements HashFunction {

    @Override
    public int hashValue( String key, int tableSize ) {

        int hash = 23;
        char[] chars = key.toCharArray();

        for (char c : chars)
            hash = (key.hashCode() + c) * 31 + 31;

        hash = hash < 0 ? hash * -1 : hash;

        return hash % tableSize;
    }
}
