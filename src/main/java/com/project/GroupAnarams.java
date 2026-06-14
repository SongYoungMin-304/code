package com.project;

// 문제: Group Anagrams
//
// 문자열 배열 strs가 주어질 때,
// 서로 애너그램(Anagram)인 문자열끼리 그룹화하여 반환하라.
//
// 애너그램:
// 문자의 구성은 같고 순서만 다른 문자열
//
// 예시 1:
// Input: ["eat","tea","tan","ate","nat","bat"]
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//
// 예시 2:
// Input: [""]
// Output: [[""]]
//
// 예시 3:
// Input: ["a"]
// Output: [["a"]]
//
// 제한사항:
// 1 <= strs.length <= 10^4
// 0 <= strs[i].length <= 100
// strs[i]는 소문자 영어 알파벳으로만 구성됨
//
// Hint:
// 애너그램은 정렬했을 때 같은 문자열이 된다.
// ex) "eat" -> "aet"
//     "tea" -> "aet"

import java.util.*;

public class GroupAnarams {

    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for(String str: strs){

            char[] charArray = str.toCharArray();

            String s = new String(charArray);

            if (!map.containsKey(s)) {
                map.put(s, new ArrayList<>());
            }

            map.get(s).add(str);
        }

        List<List<String>> list = new ArrayList();

        for(String key : map.keySet()){
            list.add(map.get(key));
        }

        return list;
    }

    public static void main(String[] args) {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = groupAnagrams(strs);

        System.out.println(result);
    }

}
