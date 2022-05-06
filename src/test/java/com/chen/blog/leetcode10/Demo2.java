package com.chen.blog.leetcode10;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 陈奕成
 * @create 2022 04 27 23:39
 */
public class Demo2 {
    public static void main(String[] args) {
        //maxNumber为一个从传参获取的整型数，
        //要求实现startPrint方法，间隔1s，打印1到maxNumber，不能阻塞主线程
        PrintNumber printNumber = new PrintNumber();
        new Thread(()->{
            printNumber.startPrint(10);
        },"A");

        //stopTime为一个从传参获取的整型数,单位秒
        //要求实现stopPrint方法，一段时间后结束打印，不能阻塞主线程
        new Thread(()->{
            printNumber.stopPrint(10);
        },"B");
    }
}

/**
 * @author 陈奕成
 * @describe 资源类，线程操作资源类
 * @date 2022/4/27 23:42
 */
class PrintNumber {

    private int number;
    private int time = -1;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * @describe 打印数字，每隔1s
     * @author 陈奕成
     * @date 2022/4/27 23:41
     */
    public void startPrint(int maxNumber) {
        try {
            for (int i = 1; i <= maxNumber; i++) {
                if (time == 0){
                    break;
                }
                System.out.println(i);
                Thread.sleep(1000);
                time--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @describe 一段时间后结束打印
     * @author 陈奕成
     * @date 2022/4/27 23:41
     */
    public void stopPrint(int stopTime) {
        time = stopTime;
    }
}
