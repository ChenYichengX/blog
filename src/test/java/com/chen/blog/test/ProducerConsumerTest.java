package com.chen.blog.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ProducerConsumerTest
 * @Author ChenYicheng
 * @Description 生产消费线程
 * @Date 2022/2/8 17:08
 */
public class ProducerConsumerTest {

    /**
     * @Author ChenYicheng
     * @Description 2个线程，操作一个初始值为 0 的变量，一个对其 +1，一个对其 -1；交替实现10轮
     * @Date 2022/2/8 17:16
     */
    public static void main(String[] args) {

        /*
            高内聚低耦合：线程  -> 操作  -> 资源类
                        判断、干活、通知
                        多线程交互中，必须要防止多线程的虚假唤醒也即（多线程的判断中，不许用 if 用 while）
         */
        AirCondition airCondition = new AirCondition();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                airCondition.add();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                airCondition.sub();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                airCondition.add();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                airCondition.sub();
            }
        }, "D").start();
    }
}

/**
 * @Author ChenYicheng
 * @Description 资源类
 * @Date 2022/2/8 17:18
 */
class AirCondition {
    private int number = 0;

    /**
     * @Author ChenYicheng
     * @Description +1
     * @Date 2022/2/8 17:32
     */
    /*public synchronized void add() throws InterruptedException {
        // 判断
        while (number != 0) {
            // 阻塞
            this.wait();
        }
        // 干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // 通知
        this.notifyAll();
    }*/

    /**
     * @Author ChenYicheng
     * @Description -1
     * @Date 2022/2/8 17:32
     */
    /*public synchronized void sub() throws InterruptedException {
        // 判断
        while (number == 0) {
            // 阻塞
            this.wait();
        }
        // 干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // 通知
        this.notifyAll();
    }*/

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() {
        lock.lock();
        try {
            // 判断
            while (number != 0) {
                // 阻塞
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sub() {
        lock.lock();
        try {
            // 判断
            while (number == 0) {
                // 阻塞
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
