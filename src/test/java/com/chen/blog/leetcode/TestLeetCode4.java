package com.chen.blog;

import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName TestLeetCode4
 * @Author ChenYicheng
 * @Description leetcode
 * @Date 2022/1/12 9:56
 */
public class TestLeetCode4 {

    /**
     * @Author ChenYicheng
     * @Description 334.递增的三元子序列-测试
     * @Date 2022/1/12 9:58
     */
    @Test
    public void test1() {
        int num[] = {5, 6, 1};
        boolean b = increasingTriplet(num);
        System.out.println(b);
    }

    /**
     * @Author ChenYicheng
     * @Description 300. 最长递增子序列-测试
     * @Date 2022/1/12 11:57
     */
    @Test
    public void test2(){
        int num[] = {0};
        int i = lengthOfLIS(num);
        System.out.println(i);
    }

    /**
     * @Author ChenYicheng
     * @Description 334.递增的三元子序列
     * @Date 2022/1/12 9:57
     */
    public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < a) {
                a = nums[i];
            } else if (nums[i] < b) {
                b = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * @Author ChenYicheng
     * @Description 300. 最长递增子序列
     * @Date 2022/1/12 10:49
     */
    public int lengthOfLIS(int[] nums) {

        int[] a = new int[nums.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = 1;
        }
        int result = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    a[i] = Math.max(a[i], a[j] + 1);
                }
            }
            if(a[i] > result){
                result = a[i];
            }
        }

        return result;
    }
}
