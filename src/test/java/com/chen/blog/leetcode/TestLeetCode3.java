package com.chen.blog.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName TestLeetCode3
 * @Author ChenYicheng
 * @Description likou
 * @Date 2022/1/11 10:19
 */
public class TestLeetCode3 {


    /**
     * @Author ChenYicheng
     * @Description 罗马数字转整数-测试
     * @Date 2022/1/11 10:33
     */
    @Test
    public void test1() {
        String s = "XLIX";
        int i = romanToInt(s);
        System.out.println(i);
    }

    /**
     * @Author ChenYicheng
     * @Description 爬楼梯-测试
     * @Date 2022/1/11 15:47
     */
    @Test
    public void test2() {
        int i = climbStairs(5);
        System.out.println(i);
    }

    /**
     * @Author ChenYicheng
     * @Description 检查是否每一行每一列都包含全部整数
     * @Date 2022/1/11 16:00
     */
    @Test
    public void test3() {
        int[][] a = {{1, 2, 3}, {2, 1, 2}, {3, 3, 1}};
        boolean b = checkValid(a);
        System.out.println(b);
    }

    /**
     * @Author ChenYicheng
     * @Description 13. 罗马数字转整数
     * @Date 2022/1/11 10:19
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        {
            map.put('I', 1);
            map.put('V', 5);
            map.put('X', 10);
            map.put('L', 50);
            map.put('C', 100);
            map.put('D', 500);
            map.put('M', 1000);
        }
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            Integer curr = map.get(chars[i]);
            if (i == chars.length - 1) {
                sum += curr;
            } else {
                Integer next = map.get(chars[i + 1]);
                if (curr >= next) {
                    sum += curr;
                } else {
                    sum -= curr;
                }
            }
        }
        return sum;
    }


    /**
     * @Author ChenYicheng
     * @Description 70. 爬楼梯
     * @Date 2022/1/11 11:06
     */
    public int climbStairs(int n) {

        // f(n) = f(n-1) + f(n-2)
        int prevprevN = 0;
        int preN = 0;
        int temp = 1;
        for (int i = 1; i <= n; i++) {
            prevprevN = preN;
            preN = temp;
            temp = prevprevN + preN;
        }
        return temp;
    }


    /**
     * @Author ChenYicheng
     * @Description 2133. 检查是否每一行每一列都包含全部整数
     * @Date 2022/1/11 15:48
     */
    public boolean checkValid(int[][] matrix) {
        Set<Integer> map1 = new HashSet<>();
        Set<Integer> map2 = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (map1.contains(matrix[i][j])) {
                    return false;
                }
                map1.add(matrix[i][j]);
                if (map2.contains(matrix[j][i])) {
                    return false;
                }
                map2.add(matrix[j][i]);
            }
            map1.clear();
            map2.clear();
        }
        return true;
    }
}
