package com.example.demo.jdk7;

/**
 * @author: Jinxing
 * @date: 2018-05-29 20:38
 **/
public class Entry<Key,Value> {

    Key key; //key
    Value value;//value

    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
