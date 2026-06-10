package com.project;

// 문제: 애너그램 그룹화 (Group Anagrams)
// 문자열 배열 strs가 주어졌을 때, 애너그램끼리 묶어서 반환하라.
// 애너그램: 같은 문자들로 이루어진 문자열 (순서만 다름)
// 예) ["eat","tea","tan","ate","nat","bat"] → [["bat"],["nat","tan"],["ate","eat","tea"]]

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            map.computeIfAbsent(key, v -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams sol = new GroupAnagrams();

        System.out.println(sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        // [[bat], [nat, tan], [ate, eat, tea]]

        System.out.println(sol.groupAnagrams(new String[]{""}));
        // [[""]]

        System.out.println(sol.groupAnagrams(new String[]{"a"}));
        // [[a]]
    }
}
