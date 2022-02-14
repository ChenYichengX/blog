package com.chen.blog.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName TestLeetCode10
 * @Author ChenYicheng
 * @Description 力扣
 * @Date 2022/1/20 17:02
 */
public class TestLeetCode10 {


    /**
     * @Author ChenYicheng
     * @Description 740. 删除并获得点数-测试
     * @Date 2022/1/20 17:03
     */
    @Test
    public void test1() {
        int nums[] = {3, 4, 2};
        int i = deleteAndEarn(nums);
        System.out.println(i);
    }


    /**
     * @Author ChenYicheng
     * @Description 740. 删除并获得点数
     * @Date 2022/1/20 17:03
     */
    public int deleteAndEarn(int[] nums) {
        // 1.先由每个数出现的次数，算出每个数的总和
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] sum = new int[maxVal + 1];
        for (int i : nums) {
            sum[i] = sum[i] + i;
        }
        int prev = sum[0];
        int curr = Math.max(sum[0], sum[1]);
        for (int i = 2; i < sum.length; i++) {
            int temp = curr;
            curr = Math.max(curr, prev + sum[i]);
            prev = temp;
        }
        return curr;
    }
}
