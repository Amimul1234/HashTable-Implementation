package com.rahi.collisionResolutionMethods.doubleHashing;

import com.rahi.hashFunctions.HashFunction;
import com.rahi.hashFunctions.HashFunctionOne;
import com.rahi.hashFunctions.HashFunctionTwo;
import com.rahi.wordGenerator.RandomStringGenerator;

import java.util.HashMap;
import java.util.Scanner;

public class DoubleHashingTester {

    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);
        HashFunction hashFunction;

        System.out.print("Input 1 to use hash function one and 2 to user hash function 2: ");
        int hashFunctionSelector = scanner.nextInt();

        if (hashFunctionSelector == 1) {
            hashFunction = new HashFunctionOne();
        } else if (hashFunctionSelector == 2) {
            hashFunction = new HashFunctionTwo();
        } else {
            throw new IllegalStateException("Wrong function number given");
        }

        System.out.print("Please enter hash table size: ");
        int hashTableSize = scanner.nextInt();

        DoubleHashing doubleHashing = new DoubleHashing(hashTableSize, hashFunction);
        HashMap<String, Integer> stringIntegerHashMap = RandomStringGenerator.randomPairs(10000, 7);

        assert stringIntegerHashMap != null;
        stringIntegerHashMap.forEach(doubleHashing::insert);

        System.out.println("Number of collision is:" + doubleHashing.getCollisionNumber());

        String[] keys = stringIntegerHashMap.keySet().toArray(new String[0]);

        for (int i = 0; i < 1000; i++) {
            int random = (int) (Math.random() * (9999 + 1));
            doubleHashing.search(keys[random]);
        }
    }
}
