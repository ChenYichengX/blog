package com.chen.blog.leetcode4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @ClassName FizzBuzz
 * @Author ChenYicheng
 * @Description 1195. 交替打印字符串
 * @Date 2022/2/18 16:02
 */
public class FizzBuzz {

    private int n;
    private int number = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private int getCurrNumber(int curr) {
        // 能被 3、5 整除
        if (curr % 3 == 0 && curr % 5 == 0) {
            return 4;
        }
        // 能被 5 整除
        if (curr % 5 == 0) {
            return 3;
        }
        // 能被 3 整除
        if (curr % 3 == 0) {
            return 2;
        }
        return 1;
    }


    /**
     * @Author ChenYicheng
     * @Description 能被 3 整除
     * @Date 2022/2/18 16:07
     */
    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        try {
            while (number <= n) {
                // 判断当前数字能否被 3 整除
                if (getCurrNumber(number) == 2) {
                    // 干活 -> 打印 fizz
                    printFizz.run();
                    // 唤醒
                    number++;
                    condition.signalAll();
                } else {
                    condition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * @Author ChenYicheng
     * @Description 能被 5 整除
     * @Date 2022/2/18 16:07
     */
    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lock();
        try {
            while (number <= n) {
                // 判断当前数字能否被 5 整除
                if (getCurrNumber(number) == 3) {
                    // 干活 -> 打印 buzz
                    printBuzz.run();
                    // 唤醒
                    number++;
                    condition.signalAll();
                } else {
                    condition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * @Author ChenYicheng
     * @Description 同时能被 3、5整除
     * @Date 2022/2/18 16:08
     */
    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        lock.lock();
        try {
            while (number <= n) {
                // 判断当前数字能否被 3、5 整除
                if (getCurrNumber(number) == 4) {
                    // 干活 -> 打印 fizzbuzz
                    printFizzBuzz.run();
                    // 唤醒
                    number++;
                    condition.signalAll();
                } else {
                    condition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * @Author ChenYicheng
     * @Description 不能，直接打印数字出来
     * @Date 2022/2/18 16:09
     */
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (number <= n) {
                // 判断当前数字能否被 3、5 整除
                if (getCurrNumber(number) == 1) {
                    // 干活 -> 打印 x
                    printNumber.accept(number);
                    // 唤醒
                    number++;
                    condition.signalAll();
                } else {
                    condition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}

class TestFizzBuzzMain {

    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz(15);

        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                fizzBuzz.number((value) -> System.out.println(value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();

    }
}
