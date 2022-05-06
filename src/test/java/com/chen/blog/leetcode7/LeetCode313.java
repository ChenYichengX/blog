package com.chen.blog.leetcode7;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈奕成
 * @create 2022 03 13 15:01
 */
public class LeetCode313 {

    /**
     * @describe 393. UTF-8 编码验证
     * @author 陈奕成
     * @date 2022/3/13 15:26
     */
    public boolean validUtf8(int[] data) {
       return false;
    }

    /**
     * @describe 测试-393. UTF-8 编码验证
     * @author 陈奕成
     * @date 2022/3/13 15:26
     */
    @Test
    public void test1() {

    }


    /**
     * @describe 599. 两个列表的最小索引总和
     * @author 陈奕成
     * @date 2022/3/14 13:50
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int max = Integer.MAX_VALUE;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                Integer integer = map.get(list2[i]);
                if ((integer + i) < max) {
                    max = (integer + i);
                    result.clear();
                    result.add(list2[i]);
                }else if ((integer + i) == max){
                    result.add(list2[i]);
                }
            }
        }
        String[] resultS = result.toArray(new String[]{});
        return resultS;
    }

    /**
     * @describe 测试-599. 两个列表的最小索引总和
     * @author 陈奕成
     * @date 2022/3/14 13:50
     */
    @Test
    public void test2() {
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"KFC", "Shogun", "Burger King"};
        String[] restaurant = findRestaurant(list1, list2);
        for (int i = 0; i < restaurant.length; i++) {
            System.out.println(restaurant[i]);
        }
    }
}
