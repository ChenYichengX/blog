package com.chen.blog.leetcode4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @ClassName ZeroEvenOdd
 * @Author ChenYicheng
 * @Description 1116. 打印零与奇偶数.线程题
 * @Date 2022/2/15 14:03
 */
public class ZeroEvenOdd {

    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    private int number = 0;
    private boolean flag = false; // false：打印奇数， true：打印偶数

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    /*
        打印0
     */
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 0; i < n; i++) {
                // 判断
                while (number != 0) {
                    // 阻塞
                    condition1.await();
                }
                // 干活
                printNumber.accept(0);
                // 修改标识位
                number++;
                if (flag) {
                    // 唤醒打印偶数
                    condition3.signal();
                } else {
                    // 唤醒打印奇数
                    condition2.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 2; i <= n; i += 2) {
                // 判断
                while (number == 0 || !flag) {
                    // 阻塞
                    condition3.await();
                }
                // 干活
                printNumber.accept(i);

                // 修改标识位
                number--;
                flag = false;
                // 唤醒打印0
                condition1.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 1; i <= n; i += 2) {
                // 判断
                while (number == 0 || flag) {
                    // 阻塞
                    condition2.await();
                }
                // 干活
                printNumber.accept(i);
                // 修改标识位
                number--;
                flag = true;
                // 唤醒打印0
                condition1.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}


class TestMain {
    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(4);

        new Thread(() -> {
            try {
                zeroEvenOdd.zero(value -> System.out.println(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                zeroEvenOdd.even(value -> System.out.println(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                zeroEvenOdd.odd(value -> System.out.println(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}
