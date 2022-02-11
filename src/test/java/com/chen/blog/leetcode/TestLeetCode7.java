package com.chen.blog;

import org.junit.Test;

/**
 * @ClassName TestLeetCode7
 * @Author ChenYicheng
 * @Description 力扣题
 * @Date 2022/1/17 10:28
 */
public class TestLeetCode7 {

    /**
     * @Author ChenYicheng
     * @Description 测试-509.斐波那契数
     * @Date 2022/1/17 10:29
     */
    @Test
    public void test1() {
        int fib = fib(7);
        System.out.println(fib);
    }

    /**
     * @Author ChenYicheng
     * @Description 1137. 第 N 个泰波那契数-测试
     * @Date 2022/1/17 14:39
     */
    @Test
    public void test2() {
        int tribonacci = tribonacci(7);
        System.out.println(tribonacci);
    }

    /**
     * @Author ChenYicheng
     * @Description 1220. 统计元音字母序列的数目-测试
     * @Date 2022/1/17 14:40
     */
    @Test
    public void test3() {
        int i = countVowelPermutation2(1);
        System.out.println(i);
    }


    /**
     * @Author ChenYicheng
     * @Description 509.斐波那契数
     * @Date 2022/1/17 10:28
     */
    public int fib(int n) {
        // f(2) = f(1) + f(0)
        int f0 = 0;
        int f1 = 1;
        int temp = 0;

        for (int i = 1; i <= n; i++) {
            f0 = f1;
            f1 = temp;
            temp = f0 + f1;
        }
        return temp;
    }


    /**
     * @Author ChenYicheng
     * @Description 1137. 第 N 个泰波那契数
     * @Date 2022/1/17 12:00
     */
    public int tribonacci(int n) {
        // f(3) = f(0) + f(1) + f(2)
        int f0 = 0;
        int f1 = 0;
        int f2 = 1;
        int temp = 1;

        if (n < 2) return n;
        if (n == 2) return 1;

        for (int i = 3; i <= n; i++) {
            f0 = f1;
            f1 = f2;
            f2 = temp;
            temp = f0 + f1 + f2;
        }
        return temp;
    }


    /**
     * @Author ChenYicheng
     * @Description 1220. 统计元音字母序列的数目
     * @Date 2022/1/17 14:38
     */
    public int countVowelPermutation(int n) {
        long mod = 1000000007;
        long[] dp = {1, 1, 1, 1, 1};
        long[] ndp = new long[5];

        for (int i = 2; i <= n; i++) {
            // a 前面可以为 e、u、i
            ndp[0] = (dp[1] + dp[2] + dp[4]) % mod;
            // e 前面可以为 a、i
            ndp[1] = (dp[0] + dp[2]) % mod;
            // i 前面可以为 e、o
            ndp[2] = (dp[1] + dp[3]) % mod;
            // o 前面可以为 i
            ndp[3] = (dp[2]) % mod;
            // u 前面可以为 i、o
            ndp[4] = (dp[2] + dp[3]) % mod;

            System.arraycopy(ndp, 0, dp, 0, 5);
        }
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[i]) % mod;
        }
        return (int) ans;
    }


    /**
     * @Author ChenYicheng
     * @Description 2
     * @Date 2022/1/17 17:44
     */
    public int countVowelPermutation2(int n) {
        long mod = 1000000007;
        long a = 1, e = 1, i = 1, o = 1, u = 1;

        for (int k = 2; k <= n; k++) {
            long aPrev = (e + u + i) % mod;
            long ePrev = (a + i) % mod;
            long iPrev = (e + o) % mod;
            long oPrev = (i) % mod;
            long uPrev = (i + o) % mod;

            a = aPrev;
            e = ePrev;
            i = iPrev;
            o = oPrev;
            u = uPrev;
        }
        return (int) ((a + e + o + i + u) % mod);
    }
}
