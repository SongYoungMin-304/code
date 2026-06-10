package com.project;

// 문제: 정렬된 배열 합치기 (Merge Sorted Array)
// 오름차순으로 정렬된 두 배열 nums1(크기 m+n), nums2(크기 n)가 주어진다.
// nums1의 앞 m개와 nums2의 n개를 합쳐 nums1에 정렬된 상태로 저장하라.
// (nums1 뒤쪽 n개는 0으로 채워진 빈 자리)
// 예) nums1=[1,2,3,0,0,0] m=3, nums2=[2,5,6] n=3 → [1,2,2,3,5,6]

public class MergeSortedArray {

    // O(m + n) — merge from the back to avoid shifting elements
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    public static void main(String[] args) {
        MergeSortedArray sol = new MergeSortedArray();

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        sol.merge(nums1, 3, new int[]{2, 5, 6}, 3);
        printArray(nums1); // [1, 2, 2, 3, 5, 6]

        int[] nums2 = {1};
        sol.merge(nums2, 1, new int[]{}, 0);
        printArray(nums2); // [1]

        int[] nums3 = {0};
        sol.merge(nums3, 0, new int[]{1}, 1);
        printArray(nums3); // [1]
    }

    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
