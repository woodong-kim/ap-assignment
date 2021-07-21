package com.ap.apassignment.service;

import org.junit.jupiter.api.Test;

public class LRUCacheTest {

    @Test
    public void LRUTest(){

        /**
         * 캐시의 공간(queue)이 크기를 3으로 생성
         * 다섯캐의 캐시 데이터를 put 한다.
         */
        LRUCache lru = new LRUCache(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.put(5, 5);

        /**
         * 기대응답
         * LRU 알고리즘 구현에 따라 1,2 Eviction 처리되고
         * 3,4,5 만이 캐시 공간에 저장되어 있는 상태로 조회된다.
         */
        System.out.println("Data :" + lru.get(1));
        System.out.println("Data :" + lru.get(2));
        System.out.println("Data :" + lru.get(3));
        System.out.println("Data :" + lru.get(4));
        System.out.println("Data :" + lru.get(5));
    }

}