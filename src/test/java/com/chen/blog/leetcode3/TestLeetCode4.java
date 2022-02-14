package com.chen.blog.leetcode3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestLeetCode4
 * @Author ChenYicheng
 * @Description 力扣2-10
 * @Date 2022/2/10 10:07
 */
public class TestLeetCode4 {

    @Test
    public void test1() {
        List<String> strings = simplifiedFractions(6);
        System.out.println(strings);
    }


    /**
     * @Author ChenYicheng
     * @Description 1447. 最简分数
     * @Date 2022/2/10 10:07
     */
    public List<String> simplifiedFractions(int n) {
        List<String> stringList = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1) {
                    stringList.add(j + "/" + i);
                }
            }
        }
        return stringList;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
