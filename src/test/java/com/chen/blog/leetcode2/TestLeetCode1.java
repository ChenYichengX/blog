package com.chen.blog.leetcode2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestLeetCode1
 * @Author ChenYicheng
 * @Description 力扣
 * @Date 2022/1/26 9:59
 */
public class TestLeetCode1 {

    @Test
    public void test() {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});

        int count = detectSquares.count(new int[]{11, 10});
        System.out.println(count);
        int count1 = detectSquares.count(new int[]{14, 8});
        System.out.println(count1);

        detectSquares.add(new int[]{11, 2});
        int count2 = detectSquares.count(new int[]{11, 10});
        System.out.println(count2);
    }
}

class DetectSquares {

    Map<Integer, Map<Integer,Integer>> xMap = new HashMap<>();
    Map<Integer, Map<Integer,Integer>> yMap = new HashMap<>();

    public DetectSquares() {

    }

    public void add(int[] point) {

    }

    public int count(int[] point) {
        return 0;
    }
}