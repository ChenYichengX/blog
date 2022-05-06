package com.chen.blog.leetcode11;

import java.util.*;

/**
 * @author 陈奕成
 * @create 2022 05 06 21:11
 */
public class RecentCounter {

    /*private Map<Integer, Integer> map = new TreeMap<>();

    public RecentCounter() {
        map.clear();
    }

    public int ping(int t) {
        if (map.containsKey(t)) {
            map.put(t, map.get(t) + 1);
        } else {
            map.put(t, 1);
        }
        int sum = 0;
        for (int i = t - 3000; i <= t; i++) {
            sum += map.get(i) != null ? map.get(i) : 0;
        }
        return sum;
    }*/

    Queue<Integer> queue;

    public RecentCounter() {
        queue = new ArrayDeque<Integer>();
    }

    public int ping(int t) {
        // offer() 如果在不违反容量限制的情况下立即执行，则将指定的元素插入到此队列中。
        queue.offer(t);
        //peek() 检索但不删除此队列的头，如果此队列为空，则返回 null
        while (queue.peek() < t - 3000) {
            // poll() 检索并删除此队列的头，如果此队列为空，则返回 null 。
            queue.poll();
        }
        return queue.size();
    }
}

class TestMain {
    public static void main(String[] args) {
        RecentCounter r = new RecentCounter();
        System.out.println(r.ping(1));
        System.out.println(r.ping(100));
        System.out.println(r.ping(100));
        System.out.println(r.ping(101));
        System.out.println(r.ping(3000));
        System.out.println(r.ping(3000));
        System.out.println(r.ping(3001));
        System.out.println(r.ping(3002));
    }
}
