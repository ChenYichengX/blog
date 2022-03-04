package com.chen.blog.leetcode4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestLeetCode
 * @Author ChenYicheng
 * @Description 力扣2-15
 * @Date 2022/2/15 9:35
 */
public class TestLeetCode {

    /**
     * @Author ChenYicheng
     * @Description 测试 - 1380. 矩阵中的幸运数
     * @Date 2022/2/15 9:36
     */
    @Test
    public void test1() {
//        int[][] nums = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
        int[][] nums = {{1, 10, 4, 2}, {9, 3, 8, 7}, {15, 16, 17, 12}};

        List<Integer> integers = luckyNumbers(nums);
        System.out.println(integers);
    }


    /**
     * @Author ChenYicheng
     * @Description 1380. 矩阵中的幸运数
     * @Date 2022/2/15 9:36
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        // 同一行中最小，同一列中最大
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                    minIdx = j;
                }
            }
            boolean flag = false;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][minIdx] > matrix[i][minIdx]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                list.add(matrix[i][minIdx]);
            }
        }
        return list;
    }
}
