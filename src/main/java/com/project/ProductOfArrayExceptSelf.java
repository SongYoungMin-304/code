package com.project;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4};
        printResult(productExceptSelf(nums1));
        // expected: [24, 12, 8, 6]

        int[] nums2 = {-1, 1, 0, -3, 3};
        printResult(productExceptSelf(nums2));
        // expected: [0, 0, 9, 0, 0]
    }

    public static int[] productExceptSelf(int[] nums) {

        // 1 2 3 4

        // 1  1  2 6
        // 24 12 4 1

        int []left = new int[nums.length];
        int []right = new int[nums.length];

        left[0] = 1;
        right[nums.length - 1] = 1;

        for(int i = 1; i < nums.length; i++){
            left[i] = left[i - 1] * nums[i - 1];
        }

        for(int i = nums.length - 2; i >= 0; i--){
            right[i] = right[i+1] * nums[i+1];
        }

        int [] result = new int[nums.length];

        for(int i = 0; i < result.length; i++){
            result[i] = left[i] * right[i];
        }

        // 여기에 풀이 작성
        return result;
    }

    private static void printResult(int[] result) {
        System.out.println(Arrays.toString(result));
    }

}
