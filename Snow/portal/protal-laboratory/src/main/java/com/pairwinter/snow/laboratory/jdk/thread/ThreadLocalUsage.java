package com.pairwinter.snow.laboratory.jdk.thread;

import org.apache.commons.lang.StringUtils;

/**
 * ThreadLocal是将此类型的变量绑定到当前的线程上，使得多个线程在操作同一个对象的相同属性时不至于发生冲突。
 * ThreadLocal 和 线程中ThreadLocalMap配合使用。
 * 其本质是为不同的线程创建不同的属性实例（查看ThreadLocal的set方法），并将这个属性实例放在当前Thread的ThreadLocalMap中.
 * map.set(ThreadLocal的实例对象引用, ThreadLocal实例对象的具体值);
 * 每个线程都有一个ThreadLocalMap属性。当多个线程共同操作一个对象时，对于对象中ThreadLocal的属性将不会相互冲突。
 * Created by damon on 9/22/14.
 */
public class ThreadLocalUsage {

    private static class TestObject{
        //这个属性在运行时将被各自线程单独创建并使用。
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
    }

    private static class MyThread implements Runnable{
        private TestObject testObject;

        private MyThread(TestObject testObject) {
            this.testObject = testObject;
        }

        @Override
        public void run() {
            for (int i=0; i<3; i++) {
                System.out.println(String.format("%s's number is :%s", Thread.currentThread().getName(), testObject.getNextNumber()));
            }
        }
    }

    public static void main(String[] args) {
        TestObject testObject = new TestObject();

        //三个线程操作同一个对象(testObject)的属性(myNumber).
        MyThread myThread1 = new MyThread(testObject);
        MyThread myThread2 = new MyThread(testObject);
        MyThread myThread3 = new MyThread(testObject);

        Thread thread1 = new Thread(myThread1,"Thread 1");

        Thread thread2 = new Thread(myThread2,"Thread 2");
        Thread thread3 = new Thread(myThread3,"Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();


    }


}
