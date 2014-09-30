package com.pairwinter.snow.laboratory.jdk.thread;

/**
 * Executor是一个执行器接口，用来管理Thread,为并发编程提供支持，使之变得简单。
 * Java SE5的java.util.concurrent包中的执行器（Executor）将为你管理Thread对象，从而简化了并发编程。
 * Executor在客户端和执行任务之间提供了一个间接层，Executor代替客户端执行任务。
 * Executor允许你管理异步任务的执行，而无须显式地管理线程的生命周期。
 * Executor在Java SE5/6中时启动任务的优选方法。
 * Executor引入了一些功能类来管理和使用线程Thread，
 * 其中包括线程池，Executor，Executors，ExecutorService，CompletionService，Future，Callable等
 *
 * ExecutorService,执行器服务接口，继承自Executor，它对内部任务的管理进行规范。
 *
 * Future:任务执行结果Handler对象，通过get方法可获取任务的结果，如果get时任务还未结束，那么此时会阻塞线程。
 *
 * Executors:工具类，提供多种ExecutorService实现的执行器。比如线程池执行器，定时线程池执行器。
 *
 *
 *
 * Created by damon on 9/30/14.
 */
public class ThreadPoolUsage {
}
