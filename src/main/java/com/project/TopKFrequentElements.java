package com.project;

// 문제: 상위 K개 빈출 요소 (Top K Frequent Elements)
// 정수 배열 nums와 정수 k가 주어졌을 때, 가장 많이 등장한 k개의 숫자를 반환하라.
// 순서는 상관없다. 시간복잡도 O(n log n) 보다 빠르게 풀어야 한다.
// 예) nums=[1,1,1,2,2,3], k=2 → [1,2]

import java.util.*;

public class TopKFrequentElements {

    // O(n) — bucket sort 방식
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 각 숫자가 몇 번 나왔는지 센다
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // 2. 빈도수를 index로 하는 버킷 생성 (최대 빈도수 = nums.length)
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int num : count.keySet()) {
            int freq = count.get(num);
            if (bucket[freq] == null) bucket[freq] = new ArrayList<>();
            bucket[freq].add(num);
        }

        // 3. 빈도수 높은 버킷부터 k개 수집
        int[] result = new int[k];
        int idx = 0;
        for (int freq = bucket.length - 1; freq >= 1 && idx < k; freq--) {
            if (bucket[freq] != null) {
                for (int num : bucket[freq]) {
                    result[idx++] = num;
                    if (idx == k) break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements sol = new TopKFrequentElements();

        System.out.println(Arrays.toString(sol.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2))); // [1, 2]
        System.out.println(Arrays.toString(sol.topKFrequent(new int[]{1}, 1)));                 // [1]
        System.out.println(Arrays.toString(sol.topKFrequent(new int[]{1,2,1,2,1,2,3,1,3,2}, 2))); // [1, 2]
    }
}
