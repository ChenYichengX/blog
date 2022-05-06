package com.chen.blog.leetcode10;

import org.junit.Test;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2022 04 26 23:40
 */
public class Solution2 {

    /**
     * @describe 883. 三维形体投影面积
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上
     * @author 陈奕成
     * @date 2022/4/26 23:15
     */
    public int projectionArea(int[][] grid) {
        int xy = 0, yz = 0, zx = 0;
        for (int i = 0; i < grid.length; i++) {
            int yzHeight = 0, zxHeight = 0;
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] > 0) {
                    xy++;
                }
                yzHeight = Math.max(yzHeight, grid[j][i]);
                zxHeight = Math.max(zxHeight, grid[i][j]);
            }
            yz += yzHeight;
            zx += zxHeight;
        }
        return xy + yz + zx;
    }

    /**
     * @describe 测试-883. 三维形体投影面积
     * @author 陈奕成
     * @date 2022/4/26 23:16
     */
    @Test
    public void test1() {
        int[][] grid = {{1, 2}, {3, 4}};
        int i = projectionArea(grid);
        System.out.println(i);
    }

    /**
     * @describe 417. 太平洋大西洋水流问题
     * @author 陈奕成
     * @date 2022/4/27 22:16
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        return null;
    }
    
    /**
     * @describe 测试-417. 太平洋大西洋水流问题
     * @author 陈奕成
     * @date 2022/4/27 22:16
     */
    @Test
    public void test2(){
        
    }

    /**
     * @describe 905. 按奇偶排序数组
     * @author 陈奕成
     * @date 2022/4/28 21:23
     */
    public int[] sortArrayByParity(int[] nums) {
        for (int i = 0,j = nums.length-1; i < j; i++,j--) {
            while(nums[i] % 2 == 0 && i < j){
                i++;
            }
            while(nums[j] % 2 != 0 && j > i){
                j--;
            }
            if(j <= i){
                break;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    /**
     * @describe 测试-905. 按奇偶排序数组
     * @author 陈奕成
     * @date 2022/4/28 21:24
     */
    @Test
    public void test3(){
        int nums[] = {0,2,4,1,3,6,8};
        int[] ints = sortArrayByParity(nums);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
