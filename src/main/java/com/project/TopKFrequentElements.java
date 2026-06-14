package com.project;

// 문제: 상위 K개 빈출 요소 (Top K Frequent Elements)
// 정수 배열 nums와 정수 k가 주어졌을 때, 가장 많이 등장한 k개의 숫자를 반환하라.
// 순서는 상관없다. 시간복잡도 O(n log n) 보다 빠르게 풀어야 한다.
// 예) nums=[1,1,1,2,2,3], k=2 → [1,2]

import java.util.*;

public class TopKFrequentElements {


    // 1,1,1,2,2,3
    // 1 -> 3
    // 2 -> 2
    // 3 -> 1
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return map
                .entrySet()
                .stream()
                .sorted((a,b) -> b.getValue() - a.getValue())
                .limit(k)
                .mapToInt(e -> e.getKey())
                .toArray();
    }

    // O(n) — bucket sort 방식
    // nums 전부, map에다가 저장 1 -> 2개 이런식으로
    // 갯수별로 배열을 만들어서 3개 -> 1, 2 / 2개 -> 3 이런식으로 세팅을 해서, 위에꺼 빼서 넣기
    public int[] topKFrequentV2(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] list = new List[nums.length + 1];

        for(int key : map.keySet()){

            if(list[map.get(key)] == null){
                list[map.get(key)] = new ArrayList<>();
            }

            list[map.get(key)].add(key);
        }

        int[] result = new int[k];
        int idx = 0;

        for(int i = list.length - 1; i > 0 && idx < k; i--){
            if(list[i] != null){
                for(int j = 0; j < list[i].size(); j++){
                    result[idx++] = list[i].get(j);
                    if(idx == k) break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements sol = new TopKFrequentElements();

        System.out.println(Arrays.toString(sol.topKFrequentV2(new int[]{1, 1, 1, 2, 2, 3}, 2))); // [1, 2]
        System.out.println(Arrays.toString(sol.topKFrequentV2(new int[]{1}, 1)));                 // [1]
        System.out.println(Arrays.toString(sol.topKFrequentV2(new int[]{1,2,1,2,1,2,3,1,3,2}, 2))); // [1, 2]
    }
}
