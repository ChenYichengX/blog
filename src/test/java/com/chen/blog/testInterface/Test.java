package com.chen.blog.testInterface;

/**
 * @ClassName Test
 * @Author ChenYicheng
 * @Description 测试
 * @Date 2022/1/14 10:35
 */
public class Test {

    @org.junit.Test
    public void test1(){
        TestInterface t1 = new TestClass();

        int sum = t1.sum(1, 2);
        System.out.println(sum);
    }
}
