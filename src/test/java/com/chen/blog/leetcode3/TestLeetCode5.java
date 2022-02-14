package com.chen.blog.leetcode3;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName TestLeetCode5
 * @Author ChenYicheng
 * @Description 2022-2-11力扣每日一题
 * @Date 2022/2/11 14:04
 */
public class TestLeetCode5 {

    /**
     * @Author ChenYicheng
     * @Description 1984题测试
     * @Date 2022/2/11 14:05
     */
    @Test
    public void test1() {
        int[] nums = {9, 4, 1, 7};
        System.out.println(minimumDifference(nums, 2));
    }

    /**
     * @Author ChenYicheng
     * @Description 1984. 学生分数的最小差值
     * @Date 2022/2/11 14:04
     */
    public int minimumDifference(int[] nums, int k) {
        if(k == 1){
            return 0;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

        }
        return 0;
    }
}
