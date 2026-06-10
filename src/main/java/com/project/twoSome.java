package com.project;

import java.util.HashMap;
import java.util.Map;

public class twoSome {

    public int[] twoSum(int[] nums, int target) {

        int sum = 0;

        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                sum = nums[i] + nums[j];

                if(sum == target){
                    return new int[]{i,j};
                }
            }
        }

        return new int[]{};
    }

    public int[] twoSumV2(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int a = 0; a < nums.length; a ++){

            int completion = target - nums[a];

            if(map.containsKey(completion)){
                return new int[]{map.get(completion), a};
            }
            map.put(nums[a],a);
        }

        throw new IllegalArgumentException("no solution");
    }

    public static void main(String[] args) {

        int[] k = new int[]{2, 7, 11, 15};

        twoSome t = new twoSome();

        int[] ints = t.twoSum(k, 9);

        for(int a : ints){
            System.out.println(a);
        }
    }
}
