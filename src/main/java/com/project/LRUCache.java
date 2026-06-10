package com.project;

// 문제: LRU 캐시 (LRU Cache)
// LRU(Least Recently Used) 캐시를 설계하라.
// - LRUCache(capacity): 캐시 최대 크기로 초기화
// - get(key): key가 있으면 값 반환, 없으면 -1 반환
// - put(key, value): key가 있으면 값 갱신, 없으면 추가.
//   용량 초과 시 가장 오래 전에 사용된 항목을 제거한다.
// - get, put 모두 O(1) 으로 동작해야 한다.
// 예) capacity=2, put(1,1), put(2,2), get(1)=1, put(3,3) → key 2 제거

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // 이중 연결 리스트 노드
    private static class Node {
        int key, val;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;  // key → 노드 (O(1) 조회)
    private final Node head, tail;         // 더미 노드: head(최근) ↔ tail(오래됨)

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        moveToFront(node);  // 최근 사용됐으므로 앞으로 이동
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            moveToFront(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            addToFront(node);

            if (map.size() > capacity) {
                Node lru = removeLast();  // 가장 오래된 항목 제거
                map.remove(lru.key);
            }
        }
    }

    // 노드를 현재 위치에서 제거하고 맨 앞으로 이동
    private void moveToFront(Node node) {
        remove(node);
        addToFront(node);
    }

    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private Node removeLast() {
        Node lru = tail.prev;
        remove(lru);
        return lru;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);            // {1=1}
        cache.put(2, 2);            // {1=1, 2=2}
        System.out.println(cache.get(1)); // 1   → {2=2, 1=1}
        cache.put(3, 3);            // key 2 제거 → {1=1, 3=3}
        System.out.println(cache.get(2)); // -1
        cache.put(4, 4);            // key 1 제거 → {3=3, 4=4}
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
    }
}
