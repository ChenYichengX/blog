package com.chen.blog.leetcode10;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 398. 随机数索引
 *
 * @author 陈奕成
 * @create 2022 04 25 1:01
 */
public class Solution {

    private Map<Integer, String> map = new HashMap<>();

    public Solution(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + "," + i);
            } else {
                map.put(nums[i], i + "");
            }
        }
    }

    public int pick(int target) {
        String s = map.get(target);
        String[] split = s.split(",");
        Random random = new Random();
        return Integer.parseInt(split[random.nextInt(split.length)]);
    }
}

class TestMain {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};
        Solution obj = new Solution(nums);
        int pick = obj.pick(3);
        System.out.println(pick);
    }

}
