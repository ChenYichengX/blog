package com.chen.blog.leetcode6;

import org.junit.Test;

import java.util.Scanner;

/**
 * @ClassName LeetCode1143
 * @Author ChenYicheng
 * @Description 1143. 最长公共子序列
 * @Date 2022/3/2 17:21
 */
public class LeetCode1143 {

    /**
     * @Author ChenYicheng
     * @Description 1143
     * @Date 2022/3/4 10:27
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * @Author ChenYicheng
     * @Description 1143-测试
     * @Date 2022/3/4 10:31
     */
    @Test
    public void test1() {
        String st1 = "消息国艺人的网去问人体安与哦研普朗克吗发阿斯蒂芬部法国红酒看部八年北京发价格压燃研福建巡抚安立刻就会网液理论化工加客户给及";
        String st2 = new StringBuilder(st1).reverse().toString();
        System.out.println(longestCommonSubsequence(st1, st2));
    }


    /**
     * @Author ChenYicheng
     * @Description 2104. 子数组范围和
     * @Date 2022/3/4 10:27
     */
    public long subArrayRanges(int[] nums) {
        long result = 0L;
        for (int i = 0; i < nums.length; i++) {
            int maxVal = Integer.MIN_VALUE;
            int minVal = Integer.MAX_VALUE;
            for (int j = i; j < nums.length; j++) {
                minVal = Math.min(minVal, nums[j]);
                maxVal = Math.max(maxVal, nums[j]);
                result += maxVal - minVal;
            }
        }
        return result;
    }

    /**
     * @Author ChenYicheng
     * @Description 2104. 子数组范围和-测试
     * @Date 2022/3/4 10:31
     */
    @Test
    public void test2() {
        int[] nums = {1, 2, 3};
        long l = subArrayRanges(nums);
        System.out.println(l);
    }


    @Test
    public void test3(){
        String str = "hello world";
        StringBuilder sb = new StringBuilder(str);
        str = sb.reverse().toString();
        System.out.println();
    }


}
