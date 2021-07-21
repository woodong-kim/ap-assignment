package com.ap.apassignment.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class LRUCacheTest {


    @Test
    public void lruTest(){

        LRUCache lru = new LRUCache(3);

        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.put(5, 5);

        System.out.println("Data :" + lru.get(1));
        System.out.println("Data :" + lru.get(2));
        System.out.println("Data :" + lru.get(3));
        System.out.println("Data :" + lru.get(4));
        System.out.println("Data :" + lru.get(5));
    }

}