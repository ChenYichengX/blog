package com.chen.blog.leetcode5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName DiningPhilosophers
 * @Author ChenYicheng
 * @Description 1226. 哲学家进餐
 * @Date 2022/2/22 10:24
 */
public class DiningPhilosophers {

    public DiningPhilosophers() {

    }

    // 5个叉子的标志位，是否能被使用（true:可被使用；false：不能（已被拿起））
    private boolean[] forks = {true, true, true, true, true};

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (!isEat(philosopher)) {
                condition.await();
            }
            // 干活（拿起叉子-》吃饭）
            pickLeftFork.run();
            pickRightFork.run();
            if (philosopher == 0) {
                forks[4] = false;
                forks[0] = false;
            } else {
                forks[philosopher - 1] = false;
                forks[philosopher] = false;
            }
            eat.run();
            // 通知（放下叉子）
            putLeftFork.run();
            putRightFork.run();
            if (philosopher == 0) {
                forks[4] = true;
                forks[0] = true;
            } else {
                forks[philosopher - 1] = true;
                forks[philosopher] = true;
            }
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * @Author ChenYicheng
     * @Description 判断当前是否能 eat
     * @Date 2022/2/23 11:10
     */
    private boolean isEat(int philosopher) {
        if (philosopher == 0) {
            return (forks[4] && forks[0]);
        } else {
            return (forks[philosopher - 1] && forks[philosopher]);
        }
    }
}

/**
 * 高内聚低耦合的情况下：线程  -》 操作  -》 资源类
 * 判断、干活、通知
 * 使用 while 代替 if （防止虚假唤醒）
 * 标志位、（实现精确通知、顺序访问）
 */
class TestDiningMain {
    public static void main(String[] args) {

        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    diningPhilosophers.wantsToEat(
                            0,
                            () -> System.out.print("\t0拿起左边叉子"),
                            () -> System.out.print("\t0拿起右边叉子"),
                            () -> System.out.print("\t0吃面"),
                            () -> System.out.print("\t0放下左边叉子"),
                            () -> System.out.print("\t0放下右边叉子\n")
                    );
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    diningPhilosophers.wantsToEat(
                            1,
                            () -> System.out.print("\t1拿起左边叉子"),
                            () -> System.out.print("\t1拿起右边叉子"),
                            () -> System.out.print("\t1吃面"),
                            () -> System.out.print("\t1放下左边叉子"),
                            () -> System.out.print("\t1放下右边叉子\n")
                    );
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    diningPhilosophers.wantsToEat(
                            2,
                            () -> System.out.print("\t2拿起左边叉子"),
                            () -> System.out.print("\t2拿起右边叉子"),
                            () -> System.out.print("\t2吃面"),
                            () -> System.out.print("\t2放下左边叉子"),
                            () -> System.out.print("\t2放下右边叉子\n")
                    );
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    diningPhilosophers.wantsToEat(
                            3,
                            () -> System.out.print("\t3拿起左边叉子"),
                            () -> System.out.print("\t3拿起右边叉子"),
                            () -> System.out.print("\t3吃面"),
                            () -> System.out.print("\t3放下左边叉子"),
                            () -> System.out.print("\t3放下右边叉子\n")
                    );
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    diningPhilosophers.wantsToEat(
                            4,
                            () -> System.out.print("\t4拿起左边叉子"),
                            () -> System.out.print("\t4拿起右边叉子"),
                            () -> System.out.print("\t4吃面"),
                            () -> System.out.print("\t4放下左边叉子"),
                            () -> System.out.print("\t4放下右边叉子\n")
                    );
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "E").start();

    }
}
