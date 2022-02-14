package com.chen.blog.leetcode;

import org.junit.Test;

/**
 * @ClassName TestLeetCode5
 * @Author ChenYicheng
 * @Description leetCode每日一题
 * @Date 2022/1/13 10:24
 */
public class TestLeetCode5 {

    /**
     * @Author ChenYicheng
     * @Description 747. 至少是其他数字两倍的最大数-测试
     * @Date 2022/1/13 10:30
     */
    @Test
    public void test1() {
        int[] s = {3, 6, 1, 0};
        int i = dominantIndex(s);
        System.out.println(i);
    }

    /**
     * @Author ChenYicheng
     * @Description 5. 最长回文子串-测试
     * @Date 2022/1/13 13:43
     */
    @Test
    public void test2() {
        String s = "aacabdkacaa";
        String i = longestPalindrome(s);
        System.out.println(i);
    }

    /**
     * @Author ChenYicheng
     * @Description 747. 至少是其他数字两倍的最大数
     * @Date 2022/1/13 10:25
     */
    public int dominantIndex(int[] nums) {
        int max = 0;
        int tmp = 0;
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                tmp = max;
                max = nums[i];
                maxIndex = i;
            } else if (nums[i] > tmp) {
                tmp = nums[i];
            }
        }
        if (tmp * 2 > max) {
            return -1;
        }
        return maxIndex;
    }

    /**
     * @Author ChenYicheng
     * @Description 5. 最长回文子串
     * @Date 2022/1/13 13:42
     */
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();

        if (chars.length == 1) {
        return s;
    }

        if (chars.length == 2 && chars[0] == chars[1]) {
        return s;
    } else if (chars.length == 2) {
        return chars[0] + "";
    }

    int prevIdx = 0;
    int nextIdx = 0;
    int length = 0;
        for (int i = 1; i < chars.length; i++) {
        // 判断跟前一个是否相等
        int nums[] = getPrev(i, chars);

        int next = nums[1];
        int prev = nums[0];
        for (; prev >= 0 && next < chars.length; prev--, next++) {
            if (chars[prev] == chars[next]) {
                if (next - prev > length) {
                    nextIdx = next;
                    prevIdx = prev;
                    length = next - prev;
                }
            } else {
                break;
            }
        }
    }
    StringBuilder builder = new StringBuilder();
        for (int i = prevIdx; i <= nextIdx; i++) {
        builder.append(chars[i]);
    }
        return builder.toString();
}

    /**
     * @Author ChenYicheng
     * @Description 获取前一个下标
     * @Date 2022/1/13 17:53
     */
    public int[] getPrev(int idx, char[] chars) {
        int[] nums = new int[2];
        nums[0] = idx - 1;
        nums[1] = idx + 1;
        for (int i = idx; i > 0; i--) {
            if (chars[i] == chars[i - 1]) {
                nums[1] = idx;
                nums[0] = i - 1;
            } else {
                return nums;
            }
        }
        return nums;
    }
}
