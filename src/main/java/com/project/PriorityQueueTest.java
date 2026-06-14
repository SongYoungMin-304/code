package com.project;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> b - a);

        queue.add(3);
        queue.add(1);
        queue.add(2);

        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }


        Map<Integer, Integer> map = new HashMap<>();

        map.put(2, 15);
        map.put(3, 1);
        map.put(1, 200);

        PriorityQueue<Map.Entry<Integer, Integer>> queueV2 =
                new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            queueV2.add(entry);
        }

        while(!queueV2.isEmpty()){
            System.out.println(queueV2.poll());
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queueV3 =
                new PriorityQueue<>((a,b) -> b.getKey() - a.getKey());


        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            queueV3.add(entry);
        }


        while(!queueV3.isEmpty()){
            System.out.println(queueV3.poll());
        }



    }

}
