package com.atguigu.springcloud.entities.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hwx
 * @date 2020/6/28
 */
class  Ticker{
    private  int number =30;

    Lock lock=new ReentrantLock();
    public  void  sale(){
        lock.lock();
        try {
            if (number>0) {
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t还剩下："+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * @author hwx
 * @date 2020/6/28
 */
public class saleTicket {

    public static void main(String[] args) {
        final Ticker ticker = new Ticker();

        new Thread(() ->{ for (int i=1;i<=40;i++)ticker.sale();},"a").start();
        new Thread(() ->{ for (int i=1;i<=40;i++)ticker.sale();},"b").start();
        new Thread(() ->{ for (int i=1;i<=40;i++)ticker.sale();},"c").start();

       new Thread(new Runnable() {
            public void run() {
                for (int i=1;i<=40;i++){
                    ticker.sale();
                }
            }
        }, "a").start();
        /*new Thread(new Runnable() {
            public void run() {
                for (int i=1;i<=40;i++){
                    ticker.sale();
                }
            }
        }, "b").start(); new Thread(new Runnable() {
            public void run() {
                for (int i=1;i<=40;i++){
                    ticker.sale();
                }
            }
        }, "c").start();*/


       /* Thread t1 = new Thread();
        Thread t2 = new Thread();
        Thread t3 = new Thread();*/


    }
}
