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
        return;
    }

    public List<Integer> getNewsFeed(int userId) {
        return null;
    }

    public void follow(int followerId, int followeeId) {
        return;
    }

    public void unfollow(int followerId, int followeeId) {
        return;
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
