package com.chen.blog.testInterface;

/**
 * @ClassName TestInterface
 * @Author ChenYicheng
 * @Description 接口
 * @Date 2022/1/14 10:32
 */
public interface TestInterface {

    /**
     * @Author ChenYicheng
     * @Description 求和
     * @Date 2022/1/17 11:11
     */
    default int sum(int a, int b) {
        return a + b;
    }
}
