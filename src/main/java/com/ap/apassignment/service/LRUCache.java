package com.ap.apassignment.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LRUCache<Key, Value> {

    private int size;
    private ConcurrentLinkedQueue<Key> linkedQueue;
    private ConcurrentHashMap<Key, Value> hashMap;

    public LRUCache(final int size) {
        this.size = size;
        this.linkedQueue = new ConcurrentLinkedQueue<Key>();
        this.hashMap = new ConcurrentHashMap<Key, Value>(size);
    }

    public Value get(Key key) {
        Value value = hashMap.get(key);
        if (value != null) {
            linkedQueue.remove(key);
            linkedQueue.add(key);
        }

        System.out.println("linkedQueue ==== " + linkedQueue.toString());

        return value;
    }

    public synchronized void put(final Key key, final Value value) {

        if (hashMap.containsKey(key)) {
            System.out.println("hashMap containsKey == " + key);
            linkedQueue.remove(key);
        }

        System.out.println("linkedQueue.size() === " + linkedQueue.size());

        while (linkedQueue.size() >= size) {
            Key oldestKey = linkedQueue.poll();

            System.out.println("oldestKey == " + oldestKey);

            if (oldestKey != null) {
                hashMap.remove(oldestKey);
            }

            System.out.println("hashMap === " + hashMap.toString());
        }

        linkedQueue.add(key);
        hashMap.put(key, value);

    }

}
