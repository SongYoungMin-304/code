package com.project;

import java.util.HashMap;
import java.util.Map;

public class twoSome {

    public int[] twoSum(int[] nums, int target) {

        for(int i =0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                int sum = nums[i] + nums[j];

                if(sum == target) return new int[]{i,j};
            }
        }

        throw new RuntimeException();

    }

    public int[] twoSumV2(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){

            int completion = target - nums[i];

            if(map.containsKey(completion)){
                return new int[]{map.get(completion),i};
            }else{
                map.put(nums[i],i);
            }

        }

        throw new RuntimeException();
    }

    public static void main(String[] args) {

        int[] k = new int[]{2, 7, 11, 15};

        twoSome t = new twoSome();

        int[] ints = t.twoSumV2(k, 9);

        for(int a : ints){
            System.out.println(a);
        }
    }
}
