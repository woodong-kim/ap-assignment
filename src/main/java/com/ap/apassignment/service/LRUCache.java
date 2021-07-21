package com.ap.apassignment.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LRUCache<Key, Value> {


    /**
     * Cache란 자주 사용되는 데이터를 미리 복사해사 메모리와 같이 빠르게 접근해서 응답할 수 있는 공간에 저장하는 것이다.
     * 여기서 중요한 것이 공간이다. 캐시에 사용되는 공간은 제한 적이라는 것이다.
     * 그래서 데이터 Eviction 이 필요하고, 이 Eviction 처리를 위해 LRU 알고리즘을 선택했다.
     * LRU(Least Recently Used) 알고리즘은 가장 최근에 사용된적이 없는 데이터 부터 갱신하는 것으로
     * 캐시의 자주 사용되는 데이터를 보관하는 목적에 맞게 데이터를 관리 할 수 있다.
     *
     * 해당 캐시의 검증을 위해 간단한 테스트 생성 LRUCacheTest
     */

    private int size;                                   // 캐시의 공간 사이즈
    private ConcurrentLinkedQueue<Key> linkedQueue;     // 캐시 관리를 위한 Queue
    private ConcurrentHashMap<Key, Value> hashMap;      // 캐시 저장 공간

    public LRUCache(final int size) {
        this.size = size;
        this.linkedQueue = new ConcurrentLinkedQueue<Key>();
        this.hashMap = new ConcurrentHashMap<Key, Value>(size);
    }

    // 캐시 조회
    public Value get(Key key) {
        Value value = hashMap.get(key);
        if (value != null) {
            /**
             * Cache 조회시 해당 데이터가 Eviction 되지 않게 하기 위해
             * queue 의 위치를 변경 [제거 후 다시 추가]
             */
            linkedQueue.remove(key);
            linkedQueue.add(key);
        }

        System.out.println("linkedQueue ==== " + linkedQueue.toString());

        return value;
    }

    // 캐시 생성
    public synchronized void put(final Key key, final Value value) {


        if (hashMap.containsKey(key)) {
            System.out.println("hashMap containsKey == " + key);

            /**
             * 캐시 생성이 요청되는 경우, 기존에 캐시된 정보가 있다면
             * Eviction 되지 않게 하기 위해
             * queue 의 위치를 변경[제거 후 아래에서 다시 추가]
             */
            linkedQueue.remove(key);
        }

        System.out.println("linkedQueue.size() === " + linkedQueue.size());

        /**
         * Eviction 처리의 핵심 로직
         * 캐시의 공간(queue)이 Full 되는 경우 가장 오래된 데이터를 추출한다.
         * linkedQueue.poll() 에 해당 하는 key로 실제 캐시 공간에서 제거
         */
        while (linkedQueue.size() >= size) {
            Key oldestKey = linkedQueue.poll();

            System.out.println("oldestKey == " + oldestKey);
            // 가장 오래된 데이터 삭제
            if (oldestKey != null) {
                hashMap.remove(oldestKey);
            }

            System.out.println("hashMap === " + hashMap.toString());
        }

        /**
         * 캐시 데이터 추가
         * queue와 캐시 공간(hashMap)에 각각 추가해 준다.
         */
        linkedQueue.add(key);
        hashMap.put(key, value);

    }

}
