package com.chen.blog.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName TestLiKou
 * @Author ChenYicheng
 * @Description 力扣测试题
 * @Date 2022/1/4 9:31
 */
public class TestLiKou {

    /**
     * @Author ChenYicheng
     * @Description 1.两数之和
     * @Date 2022/1/4 9:32
     */
    @Test
    public void test1() {
        int[] nums = new int[2];
        int target = 11;
        nums[0] = 2;
        nums[1] = 9;

        int[] ints = twoSum(nums, target);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    @Test
    public void test2() {
        String s = "anagram";
        String t = "nagaram";
        boolean anagram = isAnagram(s, t);
        System.out.println(anagram);
    }

    @Test
    public void test3() {
        String s = "a?c";
        String s1 = modifyString(s);
        System.out.println(s1);
    }

    /**
     * @Author ChenYicheng
     * @Description 71题
     * @Date 2022/1/6 10:23
     */
    @Test
    public void test4() {
        String s = "/a/./b/../../c/";
//        String s1 = simplifyPath(s);
        String s1 = simplifyPath2(s);
        System.out.println(s1);
    }

    /**
     * @Author ChenYicheng
     * @Description 71.简化路径-我的写法
     * @Date 2022/1/7 9:29
     */
    public String simplifyPath2(String path) {
        String replace = path.replace("//", "/");

        String[] split = replace.split("/");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }

        list.removeIf(item -> item.equals("."));
        List<String> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("..")) {
                if(result.size()> 0){
                    result.remove(result.size()-1);
                }
            } else if(!list.get(i).equals("")){
                result.add(list.get(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : result) {
            sb.append("/");
            sb.append(s);
        }

        if (sb.length() == 0) {
            sb.append("/");
        }
        return sb.toString();
    }

    /**
     * @Author ChenYicheng
     * @Description 第1题
     * @Date 2022/1/4 9:57
     */
    public int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                index[0] = i;
                index[1] = map.get(target - nums[i]);
                return index;
            }
            map.put(nums[i], i);
        }
        return index;
    }

    /**
     * @Author ChenYicheng
     * @Description 第242题
     * @Date 2022/1/4 9:57
     */
    public boolean isAnagram(String s, String t) {
        char[] chars = s.toCharArray();
        char[] tChars = t.toCharArray();

        if (chars.length != tChars.length) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (char aChar : chars) {
            if (map.containsKey(aChar)) {
                map.put(aChar, map.get(aChar) + 1);
            } else {
                map.put(aChar, 1);
            }
        }

        for (char aChar : tChars) {
           /* Integer integer = map.get(aChar);
            if(integer == null){
                return false;
            }*/
            if (map.containsKey(aChar)) {
                map.put(aChar, map.get(aChar) - 1);
            }
        }

//        Collection<Integer> values = map.values();
        Set<Character> characters = map.keySet();
        for (Character character : characters) {
            if (map.get(character) > 0) {
                return false;
            }
        }
        return true;
       /* Optional<Integer> first = map.values().stream().filter(item -> item == 0 ? false : true).findFirst();

        if (first.isPresent()) {
            return false;
        }
        return true;*/

        /*Line 22: error: cannot find symbol
        Collection<Integer> values = map.values();
        ^
        symbol:   class Collection
        location: class Solution*/
    }

    /**
     * @Author ChenYicheng
     * @Description 第349题
     * @Date 2022/1/4 14:12
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();

        for (int i : nums1) {
            set.add(i);
        }

        Set<Integer> result = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                result.add(i);
            }
        }
        int[] ints = new int[result.size()];
        int start = 0;
        for (int item : result) {
            ints[start] = item;
            start++;
        }
        return ints;
    }


    /**
     * @Author ChenYicheng
     * @Description 1576替换所有问号
     * @Date 2022/1/5 9:21
     */
    public String modifyString(String s) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '?') {
                continue;
            }
            if (chars.length == 1) {
                chars[i] = 'a';
                continue;
            }
            if (i == 0 && chars[1] != 'a') {
                chars[i] = 'a';
                continue;
            } else if (i == 0 && chars[1] == 'a') {
                chars[i] = 'b';
                continue;
            }
            chars[i] = chars[i - 1] == 'a' ? (char) (chars[i - 1] + 1) : (char) (chars[i - 1] - 1);

            if (i != chars.length - 1) {
                if (chars[i] == chars[i + 1] && (chars[i - 1] == 'c' || chars[i - 1] == 'd')) {
                    chars[i] = 'a';
                } else if (chars[i] == chars[i + 1]) {
                    chars[i] = 'c';
                }
            }
        }
        return new String(chars);
    }

    /**
     * @Author ChenYicheng
     * @Description 71.简化路径-堆栈写法
     * @Date 2022/1/6 9:59
     */
    public String simplifyPath(String path) {
        /*
            //    ->  /
            ../   返回上一级
            ./    当前目录
            /     在结尾去掉
        */

        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String s : split) {
            if (s.equals("..") && !stack.isEmpty()) {
                stack.pop();
            }
            if (!s.equals(".") && !s.equals("..") && !s.equals("")) {
                stack.push(s);
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            builder.insert(0, pop);
            builder.insert(0, "/");
        }
        if (builder.length() == 0) {
            builder.append("/");
        }
        return builder.toString();
    }
}
