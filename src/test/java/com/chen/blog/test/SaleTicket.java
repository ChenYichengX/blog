package com.chen.blog.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SaleTicket
 * @Author ChenYicheng
 * @Description 3个售票员卖30张票
 * @Date 2022/2/8 15:42
 */
public class SaleTicket {

    /*
        在高内聚低耦合的前提下：线程 -> 操作（对外暴露的调用的方法） -> 资源类
     */
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(() -> { for (int i = 0; i < 40; i++) { ticket.saleTicket(); }},"A").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) { ticket.saleTicket(); }},"B").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) { ticket.saleTicket(); }},"C").start();

       /* new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        }, "C").start();*/
    }
}

/**
 * @Author ChenYicheng
 * @Description 资源类
 * @Date 2022/2/8 15:47
 */
class Ticket {

    private int number = 30;

    private Lock lock = new ReentrantLock();

    public void saleTicket() {

        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "张票，还剩下：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
