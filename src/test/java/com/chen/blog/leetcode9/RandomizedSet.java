package com.chen.blog.leetcode9;

import java.util.*;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 * @author 陈奕成
 * @create 2022 04 13 21:34
 */
public class RandomizedSet {

    /**
     * 实现RandomizedSet 类：
     *
     * RandomizedSet() 初始化 RandomizedSet 对象
     * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
     * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
     * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
     * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1)
     */

    private Set<Integer> randomSet;

    public RandomizedSet() {
        randomSet = new HashSet<>();
    }

    public boolean insert(int val) {
        if(randomSet.contains(val)){
            return false;
        }
        randomSet.add(val);
        return true;
    }

    public boolean remove(int val) {
        if(randomSet.contains(val)){
            randomSet.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        List<Integer> list = new ArrayList<>(randomSet);
        int i = new Random().nextInt(randomSet.size());
        return list.get(i);
    }
}
