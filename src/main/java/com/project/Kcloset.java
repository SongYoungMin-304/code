package com.project;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Kcloset {

    public static void main(String[] args) {

        int[][] points1 = {
                {1, 3},
                {-2, 2}
        };
        int k1 = 1;

        printResult(kClosest(points1, k1));


        int[][] points2 = {
                {3, 3},
                {5, -1},
                {-2, 4}
        };
        int k2 = 2;

        printResult(kClosest(points2, k2));
    }

    // {3, 3} = 18
    // {5, -1} = 26
    // {-2, 4} = 20

    // 우선수위 큐,

    public static int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int []> priorityQueue = new PriorityQueue<>((a,b) -> distance(a) - distance(b));

        for(int []point : points){
            priorityQueue.add(point);
        }

        int [][] result = new int[k][2];

        for(int i = 0; i < k; i++){
            int[] poll = priorityQueue.poll();

            result[i] = poll;
        }


        return result;
    }

    public static int distance(int []data){
        return data[0] * data[0] + data[1] * data[1];
    }

    private static void printResult(int[][] result) {
        for (int[] point : result) {
            System.out.println(Arrays.toString(point));
        }
        System.out.println("------");
    }
}
