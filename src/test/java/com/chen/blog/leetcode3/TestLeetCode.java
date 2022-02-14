package com.chen.blog.leetcode3;

import org.junit.Test;

/**
 * @ClassName TestLeetCode
 * @Author ChenYicheng
 * @Description 力扣
 * @Date 2022/2/9 15:27
 */
public class TestLeetCode {

    /**
     * @Author ChenYicheng
     * @Description 2006题测试
     * @Date 2022/2/9 17:01
     */
    @Test
    public void test() {
        int[] nums = {2, 3, 2, 4};
        int i = countKDifference(nums, 1);
        System.out.println(i);
    }

    /**
     * @Author ChenYicheng
     * @Description 2006. 差的绝对值为 K 的数对数目
     * @Date 2022/2/9 17:01
     */
    public int countKDifference(int[] nums, int k) {
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(Math.abs(nums[i] - nums[j]) == k){
                    sum++;
                }
            }
        }
        return sum;
    }
}
