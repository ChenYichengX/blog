package com.chen.blog;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestLeetCode2
 * @Author ChenYicheng
 * @Description 力扣每日一题2
 * @Date 2022/1/7 9:31
 */
public class TestLeetCode2 {

    /**
     * @Author ChenYicheng
     * @Description 2022-1-7日，1614括号的最大深度
     * @Date 2022/1/7 9:33
     */
    @Test
    public void test1() {
        String str = "(1)+((2))+(((3)))";
        int i = maxDepth(str);
        System.out.println(i);
    }

    /**
     * @Author ChenYicheng
     * @Description 2022-1-10日，306括号的最大深度
     * @Date 2022/1/10 10:57
     */
    @Test
    public void test2() {
        String num = "111";
        boolean additiveNumber = isAdditiveNumber(num);
        System.out.println(additiveNumber);
    }

    /**
     * @Author ChenYicheng
     * @Description 1614.括号的最大深度
     * @Date 2022/1/7 9:34
     */
    public int maxDepth(String s) {
        char[] chars = s.toCharArray();

        int count = 0, max = 0;

        for (char aChar : chars) {
            if (aChar == '(') {
                count++;
                max = count > max ? count : max;
            } else if (aChar == ')') {
                count--;
            }
        }
        return max;
    }

    /**
     * @Author ChenYicheng
     * @Description leetCode:306.累加数
     * @Date 2022/1/10 9:24
     */
    public boolean isAdditiveNumber(String num) {
        List<BigInteger> tmp = new ArrayList<>();
        if(num.length() < 3){
            return false;
        }
        return dfs(num, tmp);
    }

    boolean dfs(String num, List<BigInteger> tmp) {
        int size = tmp.size();

        if(size >= 3 && tmp.get(size-1).compareTo(tmp.get(size-2).add(tmp.get(size-3))) == 0){
            return false;
        }
        if(num.length() == 0 && size >= 3){
            return true;
        }

        for (int i = 0; i < num.length(); i++) {
            String substring = num.substring(0, i + 1);
            if(substring.charAt(0) == '0' && substring.length() != 1){
                return false;
            }
            tmp.add(new BigInteger(substring));

            if(dfs(num.substring(i+1),tmp)){
                return true;
            }
            tmp.remove(tmp.size()-1);
        }
        return false;
    }
}
