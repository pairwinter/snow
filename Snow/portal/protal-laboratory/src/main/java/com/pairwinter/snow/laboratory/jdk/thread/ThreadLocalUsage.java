package com.pairwinter.snow.laboratory.jdk.thread;

import org.apache.commons.lang.StringUtils;

/**
 * Created by damon on 9/22/14.
 */
public class ThreadLocalUsage {
    private ThreadLocal<Integer> myNumber = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer getNextNumber(){
        myNumber.set(myNumber.get()+1);
        return myNumber.get();
    }

    private static class MyThread implements Runnable{
        private ThreadLocalUsage threadLocalUsage;

        private MyThread(ThreadLocalUsage threadLocalUsage) {
            this.threadLocalUsage = threadLocalUsage;
        }

        @Override
        public void run() {
            for (int i=0; i<3; i++) {
                System.out.println(String.format("Current thread %s's number is :%s", Thread.currentThread().getName(), threadLocalUsage.getNextNumber()));
            }
        }
    }

    public static void main(String[] args) {
        ThreadLocalUsage threadLocalUsage = new ThreadLocalUsage();

        //三个线程操作同一个对象(threadLocalUsage)的属性(myNumber).
        MyThread myThread1 = new MyThread(threadLocalUsage);
        MyThread myThread2 = new MyThread(threadLocalUsage);
        MyThread myThread3 = new MyThread(threadLocalUsage);

        Thread thread1 = new Thread(myThread1);
        Thread thread2 = new Thread(myThread2);
        Thread thread3 = new Thread(myThread3);

        thread1.start();
        thread2.start();
        thread3.start();


    }


}
