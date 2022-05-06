package com.chen.blog.leetcode8;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 陈奕成
 * @create 2022 03 25 16:17
 */
public class TestLeetCode {

    /**
     * @describe 172. 阶乘后的零
     * 直接做法，会超时，只需要计算 n 的因子有多少个 2*5,也就是因子里有多少个5
     * @author 陈奕成
     * @date 2022/3/25 16:17
     */
    public int trailingZeroes(int n) {
        /*BigDecimal sum = new BigDecimal(1);
        for (int i = 1; i <= n; i++) {
            BigDecimal bigDecimal = new BigDecimal(i);
            sum = sum.multiply(bigDecimal);
        }
        System.out.println(sum);
        StringBuilder builder = new StringBuilder(sum + "");
        StringBuilder reverse = builder.reverse();
        String s = reverse.toString();
        char[] chars = s.toCharArray();
        int zeroes = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '0'){
                zeroes++;
            }else{
                break;
            }
        }
        return zeroes;*/
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }

    /**
     * @describe 测试-172. 阶乘后的零
     * @author 陈奕成
     * @date 2022/3/25 16:18
     */
    @Test
    public void test1() {
        int i = trailingZeroes(30);
        System.out.println(i);
    }

    /**
     * @describe 682. 棒球比赛
     * @author 陈奕成
     * @date 2022/3/26 18:10
     */
    public int calPoints(String[] ops) {
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("C")) {
                list.remove(list.size() - 1);
            } else if (ops[i].equals("D")) {
                list.add(list.get(list.size() - 1) * 2);
            } else if (ops[i].equals("+")) {
                list.add(list.get(list.size() - 1) + list.get(list.size() - 2));
            } else {
                list.add(Integer.parseInt(ops[i]));
            }
        }
        return list.stream().reduce(0, Integer::sum);
    }

    @Test
    public void test2() {
        String ops[] = {"5", "2", "C", "D", "+"};
        int i = calPoints(ops);
        System.out.println(i);
    }

    /**
     * @describe 693. 交替位二进制数
     * @author 陈奕成
     * @date 2022/3/28 22:37
     */
    public boolean hasAlternatingBits(int n) {
        // 右移一位后与原数异或---》如果都是111则说明原数n是满足交替（1010）的
        int m = n ^ (n >> 1);
        // 如何判断 m 都是 1 呢
        // 如果都是1 ,则+1后，按位与得到的就是0,所以通过此就行判断
        return (m & (m + 1)) == 0;
    }

    /**
     * @describe 测试-693. 交替位二进制数
     * @author 陈奕成
     * @date 2022/3/28 22:38
     */
    @Test
    public void test4() {
        boolean b = hasAlternatingBits(6);
        System.out.println(b);
    }

    /**
     * @describe 2024. 考试的最大困扰度
     * @author 陈奕成
     * @date 2022/3/29 14:52
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return 0;
    }

    /**
     * @describe 测试-2024. 考试的最大困扰度
     * @author 陈奕成
     * @date 2022/3/29 14:52
     */
    @Test
    public void test5() {

    }

    /**
     * @describe 728. 自除数
     * @author 陈奕成
     * @date 2022/3/31 11:15
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividingNumbers(i)) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * @describe 判断是否为自除数
     * @author 陈奕成
     * @date 2022/3/31 11:18
     */
    private boolean isSelfDividingNumbers(int num) {
        int temp = num;
        while (temp > 0) {
            int i = temp % 10;
            if (i == 0 || num % i != 0) {
                return false;
            }
            temp /= 10;
        }
        return true;
    }

    @Test
    public void test6() {
        List<Integer> list = selfDividingNumbers(21, 22);
        list.forEach(item -> System.out.println(item));
    }

    /**
     * @describe 420. 强密码检验器
     * @author 陈奕成
     * @date 2022/4/2 11:01
     */
    public int strongPasswordChecker(String password) {
        int length = password.length();
        if(length <= 3){
            return 6-length;
        }
        return 0;
    }
    
    /**
     * @describe 测试-420. 强密码检验器
     * @author 陈奕成
     * @date 2022/4/2 11:02
     */
    public void test7(){
        
    }
}
