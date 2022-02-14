package com.chen.blog.leetcode;

import org.junit.Test;

/**
 * @ClassName TestLeetCode11
 * @Author ChenYicheng
 * @Description 力扣
 * @Date 2022/1/24 15:12
 */
public class TestLeetCode11 {


    /**
     * @Author ChenYicheng
     * @Description 55. 跳跃游戏-测试
     * @Date 2022/1/24 15:13
     */
    @Test
    public void test1() {
        int[] nums = {0, 2, 3};
        System.out.println(canJump2(nums));
    }

    /**
     * @Author ChenYicheng
     * @Description 1688. 比赛中的配对次数-测试
     * @Date 2022/1/25 10:14
     */
    @Test
    public void test2() {
        System.out.println(numberOfMatches(1));
    }


    /**
     * @Author ChenYicheng
     * @Description 55. 跳跃游戏
     * @Date 2022/1/24 15:13
     */
    public boolean canJump(int[] nums) {

        int maxLength = nums[0];
        if (maxLength >= nums.length - 1) {
            return true;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (maxLength >= i) {
                maxLength = Math.max((nums[i] + i), maxLength);
                if (maxLength >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @Author ChenYicheng
     * @Description 55. 跳跃游戏
     * @Date 2022/1/24 15:13
     */
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) { // 这是判断当前是否可达！！！
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @Author ChenYicheng
     * @Description 1688. 比赛中的配对次数
     * @Date 2022/1/25 9:45
     */
    public int numberOfMatches(int n) {
        // 说明是偶数
        int temp = 0;
        if (n % 2 == 0) {
            temp = n / 2;
        } else {
            temp = n / 2 + 1;
        }
        int sum = n / 2;
        while (temp >= 2) {
            int t = temp / 2;
            sum += t;
            if (temp % 2 != 0) {
                temp = t + 1;
            }else{
                temp = t;
            }
        }
        return sum;
    }
}
