package com.chen.blog.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ThreadOrderAccess
 * @Author ChenYicheng
 * @Description 精确通知顺序访问
 * @Date 2022/2/10 14:11
 */
public class ThreadOrderAccess {

    /*
        多线程之间按顺序调用，A -> B -> C 三个线程启动；
        A打印1次   ->  B打印2次    ->  c打印3次。来10轮
     */

    /*
        1. 高内聚低耦合前提下：线程操作资源类
        2. 判断、干活、通知
        3. 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while,不用if）
        4. 标志位
     */

    public static void main(String[] args) {
        Resource resource = new Resource();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.printC();
            }
        }, "C").start();

    }
}

/**
 * @Author ChenYicheng
 * @Description 资源类
 * @Date 2022/2/10 14:27
 */
class Resource {
    // 1:A    2:B     3:B
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                condition1.await();
            }
            // 干活
            System.out.println(Thread.currentThread().getName());

            // 通知
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printB() {
        lock.lock();
        try {
            // 判断
            while (number != 2) {
                condition2.await();
            }
            // 干活
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName());

            // 通知
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printC() {
        lock.lock();
        try {
            // 判断
            while (number != 3) {
                condition3.await();
            }
            // 干活
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName());

            // 通知
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}