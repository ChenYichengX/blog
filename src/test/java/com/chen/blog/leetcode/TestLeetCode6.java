package com.chen.blog;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName TestLeetCode6
 * @Author ChenYicheng
 * @Description leetcode每日一题2022.1.14
 * @Date 2022/1/14 11:52
 */
public class TestLeetCode6 {

    /**
     * @Author ChenYicheng
     * @Description 373. 查找和最小的K对数字-测试
     * @Date 2022/1/14 11:52
     */
    @Test
    public void test1() {
        int num1[] = {1, 7, 11};
        int num2[] = {2, 8, 6};
        int k = 2;
        List<List<Integer>> lists = kSmallestPairs(num1, num2, k);
        System.out.println(lists);
    }


    /**
     * @Author ChenYicheng
     * @Description 264. 丑数 II-测试
     * @Date 2022/1/14 17:00
     */
    @Test
    public void test2() {
        int i = nthUglyNumber(10);
        System.out.println(i);
    }

    /**
     * @Author ChenYicheng
     * @Description 373. 查找和最小的K对数字
     * @Date 2022/1/14 11:53
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 队列
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> nums1[a[0]] + nums2[a[1]]));
        // 将 nums1 的所有索引入队
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            heap.add(new int[]{i, 0});
        }
        List<List<Integer>> ans = new ArrayList<>();

        while (k-- > 0 && !heap.isEmpty()) {
            int[] pos = heap.poll();
            ans.add(Arrays.asList(nums1[pos[0]], nums2[pos[1]]));

            // 将 nums 的下标加 1 之后继续入队
            if(++pos[1] < nums2.length){
                heap.offer(pos);
            }
        }
        return ans;
    }

    /**
     * @Author ChenYicheng
     * @Description 264. 丑数 II
     * @Date 2022/1/14 17:00
     */
    public int nthUglyNumber(int n) {
        return 0;
    }

}
