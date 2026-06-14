package com.project;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutiveV2(nums1));
        // expected: 4
        // sequence: 1, 2, 3, 4

        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutiveV2(nums2));
        // expected: 9
        // sequence: 0,1,2,3,4,5,6,7,8

        int[] nums3 = {1, 2, 0, 1};
        System.out.println(longestConsecutiveV2(nums3));
        // expected: 3
        // sequence: 0,1,2
    }

    public static int longestConsecutive(int[] nums) {
        // 여기에 풀이 작성
        Arrays.sort(nums);
        int result = 1;
        int max = 0;

        for(int i = 0; i < nums.length; i++){

            if(i > 0){
                if(nums[i] - nums[i-1] == 1) {
                    result++;
                }
                else{
                   if(max < result) max = result;
                   result = 0;
                }
            }
        }
        return max;
    }


    public static int longestConsecutiveV2(int[] nums) {
        // 여기에 풀이 작성
        HashSet<Integer> set = new HashSet<>();

        int result = 1;
        int max = 0;

        for(int num: nums){
            set.add(num);
        }

        for(int num: nums){
            while(set.contains(num + 1)){
                num = num+1;
                result++;
            }
            if(result > max) max = result;
            result = 1;
        }

        return max;
    }

}
