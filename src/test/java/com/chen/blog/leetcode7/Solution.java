package com.chen.blog.leetcode7;

import org.elasticsearch.index.analysis.NameOrDefinition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈奕成
 * @create 2022 03 10 13:36
 */
public class Solution {

    /**
     * 589. N 叉树的前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        helper(root,list);
        return list;
    }

    public void helper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        if(root.children != null){
            for (Node ch : root.children) {
                helper(ch, res);
            }
        }
    }

    @Test
    public void test1(){
        Node node = new Node(3);
        List<Node> arr = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(6));
        arr.add(new Node(1));
        arr.add(new Node(2,nodes));
        arr.add(new Node(4));
        node.children = arr;
        List<Integer> preorder = preorder(node);
        preorder.forEach(item -> System.out.println(item));
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
