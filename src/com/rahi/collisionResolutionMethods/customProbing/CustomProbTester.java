package com.rahi.collisionResolutionMethods.customProbing;

import com.rahi.hashFunctions.HashFunction;
import com.rahi.hashFunctions.HashFunctionOne;
import com.rahi.hashFunctions.HashFunctionTwo;
import com.rahi.wordGenerator.RandomStringGenerator;

import java.util.HashMap;
import java.util.Scanner;

public class CustomProbTester {

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

        CustomProb customProb = new CustomProb(hashTableSize, hashFunction);
        HashMap<String, Integer> stringIntegerHashMap = RandomStringGenerator.randomPairs(10000, 7);

        assert stringIntegerHashMap != null;
        stringIntegerHashMap.forEach(customProb::insert);

        System.out.println("Number of collision is:" + customProb.getTotalCollisionNumber());

        String[] keys = stringIntegerHashMap.keySet().toArray(new String[0]);

        for (int i = 0; i < 1000; i++) {
            int random = (int) (Math.random() * (9999 + 1));
            customProb.search(keys[random]);
        }

        System.out.println("Average probes : " + customProb.getProbing() / 1000.0);
    }
}
