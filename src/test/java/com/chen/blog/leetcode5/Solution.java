package com.chen.blog.leetcode5;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName Solution
 * @Author ChenYicheng
 * @Description 917. 仅仅反转字母
 * @Date 2022/2/23 9:34
 */
public class Solution {

    /**
     * @Author ChenYicheng
     * @Description 537. 复数乘法-测试
     * @Date 2022/2/25 17:42
     */
    @Test
    public void test2() {
        String num1 = "1+-1i";
        String num2 = "1+-1i";
        System.out.println(complexNumberMultiply(num1, num2));
    }

    /**
     * @Author ChenYicheng
     * @Description 测试-917. 仅仅反转字母
     * @Date 2022/2/23 9:35
     */
    @Test
    public void test1() {
        String str = "a-bC-dEf-ghIj";
        System.out.println(reverseOnlyLetters(str));
    }


    /**
     * @Author ChenYicheng
     * @Description 917. 仅仅反转字母
     * @Date 2022/2/23 9:34
     */
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (true) {
            while (i < j && (chars[i] < 65 || (chars[i] > 90 && chars[i] < 97) || chars[i] > 122)) {
                i++;
            }
            while (i < j && (chars[j] < 65 || (chars[j] > 90 && chars[j] < 97) || chars[j] > 122)) {
                j--;
            }
            if (i >= j) {
                break;
            }
            char temp = chars[j];
            chars[j] = chars[i];
            chars[i] = temp;
            i++;
            j--;
        }
        return new String(chars);
    }

    /**
     * @Author ChenYicheng
     * @Description 537. 复数乘法
     * @Date 2022/2/25 17:42
     */
    public String complexNumberMultiply(String num1, String num2) {
        // 复数乘法：设z1=a+bi，z2=c+di 任意两个复数，那么它们的积(a+bi)(c+di)=(ac-bd)+(bc+ad)i。

        String[] split = num1.split("\\+");
        int s1 = Integer.parseInt(split[0]);
        int x1 = Integer.parseInt(split[1].substring(0, split[1].indexOf("i")));

        String[] split2 = num2.split("\\+");
        int s2 = Integer.parseInt(split2[0]);
        int x2 = Integer.parseInt(split2[1].substring(0, split2[1].indexOf("i")));

        String result = (s1 * s2 - x1 * x2) + "+" + (x1 * s2 + s1 * x2) + "i";

        return result;
    }

    /**
     * @Author ChenYicheng
     * @Description convert验证
     * @Date 2022/3/1 13:51
     */
    @Test
    public void test3() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        String convert = convert(s, numRows);
        System.out.println(convert);
    }

    /**
     * @Author ChenYicheng
     * @Description 6. Z 字形变换
     * @Date 2022/3/1 11:18
     */
    public String convert(String s, int numRows) {
        char[][] ch = new char[numRows][10];

        char[] chars = s.toCharArray();
        int temp = 0;
        for (int i = 0, j = 1; j <= chars.length; j++) {
            ch[i][temp] = chars[j - 1];
            i++;
            if (j % numRows == 0) {
                temp++;
                i = 0;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < ch[i].length; j++) {
                builder.append(ch[i][j]);
            }
        }
        return builder.toString();
    }
}
