package com.chen.blog.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestLeetCode8
 * @Author ChenYicheng
 * @Description 力扣
 * @Date 2022/1/18 9:44
 */
public class TestLeetCode8 {

    /**
     * @Author ChenYicheng
     * @Description 539. 最小时间差-测试
     * @Date 2022/1/18 9:45
     */
    @Test
    public void test1() {
        List timePoints = Arrays.asList("23:59", "00:00", "23:59");
        int minDifference = findMinDifference(timePoints);
        System.out.println(minDifference);
    }

    /**
     * @Author ChenYicheng
     * @Description 746. 使用最小花费爬楼梯-测试
     * @Date 2022/1/18 16:34
     */
    @Test
    public void test2() {
        int[] cost = {};
        int a = minCostClimbingStairs(cost);
        System.out.println(a);
    }

    /**
     * @Author ChenYicheng
     * @Description 539. 最小时间差
     * @Date 2022/1/18 9:45
     */
    public int findMinDifference(List<String> timePoints) {
        int[] arr = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            String[] s = timePoints.get(i).split(":");
            arr[i] = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
        }
        Arrays.sort(arr);
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            result = Math.min(result, arr[i] - arr[i - 1]);
        }
        return Math.min(result, arr[0] + 1440 - arr[arr.length - 1]);
    }

    /**
     * @Author ChenYicheng
     * @Description 746. 使用最小花费爬楼梯
     * @Date 2022/1/18 16:33
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }
        return Math.min(dp[cost.length - 2], dp[cost.length - 1]);
    }
}
