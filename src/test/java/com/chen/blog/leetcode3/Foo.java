package com.chen.blog.leetcode3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Foo
 * @Author ChenYicheng
 * @Description likou
 * @Date 2022/2/10 15:50
 */
public class Foo {

    public Foo() {

    }

    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        // printFirst.run() outputs "first". Do not change or remove this line.
        try {
            while (number != 1) {
                condition1.await();
            }
            printFirst.run();
            number = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            number = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            number = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}

class TestFooMain{
    public static void main(String[] args) {

        Foo foo = new Foo();

        new Thread(() -> {
            for (int i = 0; i < 5 ; i++) {
                try {
                    foo.first(() -> System.out.println("first"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 5 ; i++) {
                try {
                    foo.second(() -> System.out.println("second"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 5 ; i++) {
                try {
                    foo.third(() -> System.out.println("third"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
