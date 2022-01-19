package com.rahi.collisionResolutionMethods.seperateChaining;

public class HashNode {

    private String key;
    private int value;
    private final int hashValue;
    private HashNode nextNode;

    public HashNode( String key, int value, int hashValue ) {
        this.key = key;
        this.value = value;
        this.hashValue = hashValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey( String key ) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue( int value ) {
        this.value = value;
    }

    public int getHashValue() {
        return hashValue;
    }

    public HashNode getNextNode() {
        return nextNode;
    }

    public void setNextNode( HashNode nextNode ) {
        this.nextNode = nextNode;
    }
}
