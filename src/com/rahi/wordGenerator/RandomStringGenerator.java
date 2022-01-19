package com.rahi.wordGenerator;

import java.util.HashMap;

public class RandomStringGenerator {

    private static String stringGenerator( int length ) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            stringBuilder.append((char) (Math.random() * 26 + 97));
        }

        return stringBuilder.toString();
    }

    public static HashMap<String, Integer> randomPairs( int numberOfPairs, int stringLengths ) {

        int uniquePairs = 0;
        HashMap<String, Integer> pairs = new HashMap<>();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {

            if (uniquePairs == numberOfPairs) {
                return pairs;
            }

            String key = stringGenerator(stringLengths);

            if (!pairs.containsKey(key)) {
                pairs.put(key, uniquePairs + 1);
                uniquePairs++;
            }
        }

        return null;
    }
}
