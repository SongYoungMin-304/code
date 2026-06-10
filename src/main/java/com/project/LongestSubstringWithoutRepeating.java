package com.project;

// 문제: 중복 없는 가장 긴 부분 문자열 (Longest Substring Without Repeating Characters)
// 문자열 s가 주어졌을 때, 중복 문자가 없는 가장 긴 부분 문자열의 길이를 반환하라.
// 예) "abcabcbb" → 3 ("abc"), "bbbbb" → 1 ("b"), "pwwkew" → 3 ("wke")

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeating {

    // O(n) — 슬라이딩 윈도우
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int max = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 이미 윈도우 안에 있는 문자라면 left를 해당 문자 다음으로 이동
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= left) {
                left = lastIndex.get(c) + 1;
            }

            lastIndex.put(c, right);
            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeating sol = new LongestSubstringWithoutRepeating();

        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));   // 3
        System.out.println(sol.lengthOfLongestSubstring(""));         // 0
    }
}
