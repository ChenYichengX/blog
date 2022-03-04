package com.chen.blog.leetcode4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName H2O
 * @Author ChenYicheng
 * @Description 1117. H2O 生成
   现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
   存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
   氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
   这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
   你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * @Date 2022/2/18 10:32
 */
public class H2O {

    public H2O() {

    }

    private int number = 0; // 标志位，0,1 打印 H; 2打印 O
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try{
            // 判断
            while (number == 2){
                condition.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();

            number++;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try{
            // 判断
            while (number != 2){
                condition.await();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();

            number=0;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

class TestH2OMain {
    public static void main(String[] args) {
        H2O h2O = new H2O();

        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    h2O.oxygen(() -> System.out.println("O"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();


        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    h2O.hydrogen(() -> System.out.println("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
