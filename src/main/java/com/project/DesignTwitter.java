package com.project;

// 문제: 트위터 설계 (Design Twitter)
// 간단한 트위터를 설계하라. 아래 기능을 구현해야 한다.
// - postTweet(userId, tweetId): 트윗 올리기
// - getNewsFeed(userId): 내가 팔로우한 사람 + 나의 트윗 중 최신 10개 반환
// - follow(followerId, followeeId): 팔로우
// - unfollow(followerId, followeeId): 언팔로우
// 예) 1이 트윗5 올림 → 피드:[5], 1이 2 팔로우 → 2가 트윗6 올림 → 피드:[6,5]

import java.util.*;

public class DesignTwitter {

    private int timestamp;
    private final Map<Integer, List<int[]>> tweets;  // userId → [[time, tweetId], ...]
    private final Map<Integer, Set<Integer>> follows; // userId → 팔로우한 사람들

    public DesignTwitter() {
        timestamp = 0;
        tweets = new HashMap<>();
        follows = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, v -> new ArrayList<>())
              .add(new int[]{timestamp++, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
        // 최신순 정렬 (time 내림차순)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // 내 트윗 + 팔로우한 사람들 트윗을 모두 힙에 넣기
        Set<Integer> targets = new HashSet<>();
        targets.add(userId);
        if (follows.containsKey(userId)) targets.addAll(follows.get(userId));

        for (int uid : targets) {
            if (tweets.containsKey(uid)) {
                List<int[]> list = tweets.get(uid);
                // 각 유저의 가장 최신 트윗 인덱스를 힙에 넣기
                int idx = list.size() - 1;
                pq.offer(new int[]{list.get(idx)[0], list.get(idx)[1], uid, idx});
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty() && result.size() < 10) {
            int[] top = pq.poll();
            result.add(top[1]); // tweetId

            // 같은 유저의 다음 트윗이 있으면 힙에 추가
            int nextIdx = top[3] - 1;
            if (nextIdx >= 0) {
                int uid = top[2];
                List<int[]> list = tweets.get(uid);
                pq.offer(new int[]{list.get(nextIdx)[0], list.get(nextIdx)[1], uid, nextIdx});
            }
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        follows.computeIfAbsent(followerId, v -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (follows.containsKey(followerId)) {
            follows.get(followerId).remove(followeeId);
        }
    }

    public static void main(String[] args) {
        DesignTwitter twitter = new DesignTwitter();

        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1)); // [5]

        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1)); // [6, 5]

        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1)); // [5]
    }
}
