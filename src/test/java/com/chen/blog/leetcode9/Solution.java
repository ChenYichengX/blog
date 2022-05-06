package com.chen.blog.leetcode9;

import org.junit.Test;

import java.util.*;

/**
 * @author 陈奕成
 * @create 2022 04 13 22:54
 */
public class Solution {
    /**
     * @describe 806. 写字符串需要的行数
     * @author 陈奕成
     * @date 2022/4/13 22:54
     */
    public int[] numberOfLines(int[] widths, String s) {
        int[] result = {1, 0};
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            result[1] += widths[chars[i] - 97];
            if (result[1] > 100) {
                result[1] = widths[chars[i] - 97];
                result[0]++;
            }
        }
        return result;
    }

    @Test
    public void test1() {
        int[] widths = {10, 1, 10};
        int[] aabs = numberOfLines(widths, "aab");
        for (int aab : aabs) {
            System.out.println(aab);
        }
    }

    /**
     * @describe 1672. 最富有客户的资产总量
     * @author 陈奕成
     * @date 2022/4/14 23:23
     */
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int i = 0; i < accounts.length; i++) {
            int temp = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                temp += accounts[i][j];
            }
            max = Math.max(max, temp);
        }
        return max;
    }

    @Test
    public void test2() {
        int[][] accounts = {{2, 4}, {3, 7}, {1, 6}};
        int i = maximumWealth(accounts);
        System.out.println(i);
    }

    /**
     * @describe 819. 最常见的单词
     * @author 陈奕成
     * @date 2022/4/17 23:39
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        char[] chars = paragraph.toCharArray();
        Set<String> banneds = new HashSet<>();
        for (String b : banned) {
            String s2 = b.toLowerCase();
            banneds.add(s2);
        }
        Map<String, Integer> keys = new HashMap<>();
        int n = chars.length;
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isLetter(chars[i])) {
                continue;
            }
            int j = i;
            while (i < n && Character.isLetter(chars[i])) {
                i++;
            }
            String sub = paragraph.substring(j, i).toLowerCase();
            // 验证是否为敏感词
            if (banneds.contains(sub)) {
                continue;
            }
            // 放入 map
            if (keys.containsKey(sub)) {
                keys.put(sub, keys.get(sub) + 1);
            } else {
                keys.put(sub, 1);
            }
        }
        final String[] maxKey = {""};
        final Integer[] maxValue = {0};
        keys.forEach((key, value) -> {
            if (value > maxValue[0]) {
                maxKey[0] = key;
                maxValue[0] = value;
            }
        });
        return maxKey[0];
    }

    /**
     * @describe 测试-819. 最常见的单词
     * @author 陈奕成
     * @date 2022/4/17 23:39
     */
    @Test
    public void test3() {
        String paragraph = "a, a, a, a, b,b,b,c, c";
        String[] banned = {"a"};
        String s = mostCommonWord(paragraph, banned);
        System.out.println(s);
    }

    /**
     * number×10 ≤ n，那么说明 number×10 是下一个字典序整数；
     * 如果 number % 10 = 9  或 number + 1 > n，那么说明末尾的数位已经搜索完成，退回上一位，即除 10
     *
     * @describe 386. 字典序排数
     * @author 陈奕成
     * @date 2022/4/18 21:36
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            ans.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return ans;
    }

    /**
     * @describe 第二种做法-利用字符排序
     * @author 陈奕成
     * @date 2022/4/18 21:49
     */
    public List<Integer> lexicalOrder2(int n) {
        String[] ans = new String[n];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(i + 1);
        }
        Arrays.sort(ans);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            res.add(Integer.parseInt(ans[i]));
        }
        return res;
    }

    @Test
    public void test4() {
        List<Integer> list = lexicalOrder2(13);
        list.forEach(item -> System.out.println(item));
    }

    /**
     * @describe 821. 字符的最短距离
     * @author 陈奕成
     * @date 2022/4/19 20:54
     */
    public int[] shortestToChar(String s, char c) {
        List<Integer> cList = new ArrayList<>();
        int i1 = s.indexOf(c);
        cList.add(i1);
        while ((i1 = s.indexOf(c, i1 + 1)) != -1) {
            cList.add(i1);
        }
        int[] res = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < cList.size(); j++) {
                min = Math.min(min, Math.abs(i - cList.get(j)));
            }
            res[i] = min;
        }
        return res;
    }

    /**
     * @describe 测试-821. 字符的最短距离
     * @author 陈奕成
     * @date 2022/4/19 20:54
     */
    @Test
    public void test5() {
        String s = "leetcode";
        char c = 'e';
        int[] ints = shortestToChar(s, c);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }


    /**
     * 注意   \t 是一个字符，不是两个
     *
     * @describe 388. 文件的最长绝对路径
     * @author 陈奕成
     * @date 2022/4/20 22:18
     */
    public int lengthLongestPath(String input) {
        int n = input.length();
        int pos = 0;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        while (pos < n) {
            // 检测文件深度
            int depth = 1;
            while (pos < n && input.charAt(pos) == '\t') {
                depth++;
                pos++;
            }
            // 统计当前文件名长度
            boolean isFile = false;
            int len = 0;
            while (pos < n && input.charAt(pos) != '\n') {
                if (input.charAt(pos) == '.') {
                    isFile = true;
                }
                len++;
                pos++;
            }
            // 跳过当前换行符
            pos++;

            while (stack.size() >= depth) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                len += stack.peek() + 1;
            }

            if (isFile) {
                ans = Math.max(ans, len);
            } else {
                stack.push(len);
            }
        }
        return ans;
    }

    /**
     * @describe 测试-388. 文件的最长绝对路径
     * @author 陈奕成
     * @date 2022/4/20 22:19
     */
    @Test
    public void test6() {
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
//        String input = "d";
//        String input = "file1.txt\nfile2.txt\nlongfile.txt";
        int i = lengthLongestPath(input);
        System.out.println(i);
    }


    /**
     * @describe 824. 山羊拉丁文
     * @author 陈奕成
     * @date 2022/4/21 0:50
     */
    public String toGoatLatin(String sentence) {
        String[] s = sentence.split(" ");
        StringBuilder str = new StringBuilder("");
        Set<Character> yuan = new HashSet<>();
        yuan.add('a');
        yuan.add('e');
        yuan.add('i');
        yuan.add('o');
        yuan.add('u');
        yuan.add('A');
        yuan.add('E');
        yuan.add('I');
        yuan.add('O');
        yuan.add('U');
        for (int i = 0; i < s.length; i++) {
            char c = s[i].charAt(0);
            if (yuan.contains(c)) {
                s[i] += "ma";
            } else {
                s[i] = s[i].substring(1) + s[i].charAt(0) + "ma";
            }
            for (int j = 0; j <= i; j++) {
                s[i] += 'a';
            }
            str.append(s[i]);
            if(i != s.length-1){
                str.append(" ");
            }
        }
        return str.toString();
    }

    /**
     * @describe 测试-824. 山羊拉丁文
     * @author 陈奕成
     * @date 2022/4/21 0:50
     */
    @Test
    public void test7() {
        String sentence = "I speak Goat Latin";
        String s = toGoatLatin(sentence);
        System.out.println(s);
    }

    /**
     * @describe 587. 安装栅栏
     * @author 陈奕成
     * @date 2022/4/23 11:51
     */
    public int[][] outerTrees(int[][] trees) {
        return null;
    }

    /**
     * @describe 测试-587. 安装栅栏
     * @author 陈奕成
     * @date 2022/4/23 11:51
     */
    public void test8(){
        int[][] tree = {{1,1},{2,2},{2,0},{2,4},{3,3},{4,2}};

        int[][] ints = outerTrees(tree);
        for (int[] anInt : ints) {
            System.out.print("[");
            for (int i : anInt) {
                System.out.print(i+",");
            }
            System.out.println("],");
        }
    }

    /**
     * @describe 868. 二进制间距
     * @author 陈奕成
     * @date 2022/4/24 19:50
     */
    public int binaryGap(int n) {
        String s = Integer.toBinaryString(n);
        System.out.println(s);
        char[] chars = s.toCharArray();
        int firstIndex = -1;
        int secIndex = -2;
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '0'){
                continue;
            }
            if(chars[i] == '1' && firstIndex == -1){
                firstIndex = i;
            }
            if(chars[i] == '1'){
                secIndex = i;
                maxLength = Math.max(maxLength,secIndex - firstIndex);
                firstIndex = i;
            }
        }
        return maxLength;
    }


    /**
     * @describe 测试-868. 二进制间距
     * @author 陈奕成
     * @date 2022/4/24 19:50
     */
    @Test
    public void test9(){
        int n = 22;
        int i = binaryGap(n);
        System.out.println(i);
    }
}
