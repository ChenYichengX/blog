package com.chen.blog.leetcode4;

import org.junit.Test;

/**
 * @ClassName TestLeetCode1791
 * @Author ChenYicheng
 * @Description 1791. 找出星型图的中心节点
 * @Date 2022/2/18 9:17
 */
public class TestLeetCode1791 {

    /**
     * @Author ChenYicheng
     * @Description 测试-1791. 找出星型图的中心节点
     * @Date 2022/2/18 9:17
     */
    @Test
    public void test1() {
        int[][] edges = {{1, 2}, {2, 3}, {4, 2}};
        System.out.println(findCenter(edges));
    }

    /**
     * @Author ChenYicheng
     * @Description 1791. 找出星型图的中心节点
     * @Date 2022/2/18 9:17
     */
    public int findCenter(int[][] edges) {
        // 二维数组里，每一行都有一个相同的。找出每个相同的
        int temp1 = edges[0][0];
        int temp2 = edges[0][1];

        for (int j = 0; j < edges[1].length; j++) {
            if(edges[1][j] == temp1){
                return temp1;
            }
            if(edges[1][j] == temp2){
                return temp2;
            }
        }
        return 0;
    }
}
