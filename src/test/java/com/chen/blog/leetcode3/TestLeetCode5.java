package com.chen.blog.leetcode3;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName TestLeetCode5
 * @Author ChenYicheng
 * @Description 2022-2-11力扣每日一题
 * @Date 2022/2/11 14:04
 */
public class TestLeetCode5 {

    /**
     * @Author ChenYicheng
     * @Description 540. 有序数组中的单一元素 测试
     * @Date 2022/2/14 11:26
     */
    @Test
    public void test2() {
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        int i = singleNonDuplicate(nums);
        System.out.println(i);
    }

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
        if (k == 1) {
            return 0;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

        }
        return 0;
    }


    /**
     * @Author ChenYicheng
     * @Description 540. 有序数组中的单一元素
     * @Date 2022/2/14 11:25
     */
    public int singleNonDuplicate(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        if(nums[0] != nums[1] && nums[1] == nums[2]){
            return nums[0];
        }
        for (int i = 0, j = 1; i < nums.length - 2; i+=2, j+=2) {
            if(nums[i] != nums[j]){
                return nums[i];
            }
        }
        return nums[nums.length-1];
    }
}
