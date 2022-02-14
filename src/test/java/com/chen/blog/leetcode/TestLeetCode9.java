package com.chen.blog.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestLeetCode9
 * @Author ChenYicheng
 * @Description 力扣
 * @Date 2022/1/19 9:41
 */
public class TestLeetCode9 {

    /**
     * @Author ChenYicheng
     * @Description 219.存在重复元素 II-测试
     * @Date 2022/1/19 10:29
     */
    @Test
    public void test1() {
        int num[] = {1, 2, 3, 4};
        boolean b = containsNearbyDuplicate(num, 3);
        System.out.println(b);
    }

    /**
     * @Author ChenYicheng
     * @Description 213. 打家劫舍 II-测试
     * @Date 2022/1/19 18:27
     */
    @Test
    public void test3() {
        int num[] = {1, 7, 9, 2};
        int b = rob2(num);
        System.out.println(b);
    }

    /**
     * @Author ChenYicheng
     * @Description 219. 存在重复元素 II
     * @Date 2022/1/19 9:41
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }


    /**
     * @Author ChenYicheng
     * @Description 198. 打家劫舍
     * @Date 2022/1/19 13:39
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return Math.max(dp[dp.length - 1], dp[dp.length - 2]);
    }


    /**
     * @Author ChenYicheng
     * @Description 213. 打家劫舍 II
     * @Date 2022/1/19 14:06
     */
    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int i = rob2D(nums, 0, nums.length - 2);
        int j = rob2D(nums, 1, nums.length - 1);
        return Math.max(i, j);
    }

    /**
     * @Author ChenYicheng
     * @Description 返回最大值
     * @Date 2022/1/19 18:19
     */
    int rob2D(int[] nums, int start, int end) {
        int pre = nums[start];
        int curr = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = curr;
            curr = Math.max(curr, pre + nums[i]);
            pre = temp;
        }
        return curr;
    }

}
