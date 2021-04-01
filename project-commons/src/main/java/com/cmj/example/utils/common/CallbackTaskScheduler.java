package com.cmj.example.utils.common;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mengjie_chen
 * @description 异步回调任务
 * @date 2020/10/8
 */
public class CallbackTaskScheduler extends Thread {

    /**
     * 任务调度队列
     */
    private static final ConcurrentLinkedQueue<CallbackTask> queue = new ConcurrentLinkedQueue<>();
    /**
     * 线程休眠时间
     */
    private long sleepTime = 200;
    /**
     * java线程池
     */
    private static final ExecutorService jPool = Executors.newFixedThreadPool(10);
    /**
     * guava线程池
     */
    private static final ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool);

    private static final CallbackTaskScheduler callbackTaskScheduler = new CallbackTaskScheduler();

    private CallbackTaskScheduler() {
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            this.handleTask();
            this.threadSleep();
        }
    }

    /**
     * 添加任务
     *
     * @param callbackTask
     * @return void
     * @author mengjie_chen
     * @date 2020/10/8
     */
    public static void add(CallbackTask callbackTask) {
        queue.add(callbackTask);
    }

    /**
     * 线程休眠
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/10/8
     */
    private void threadSleep() {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行任务
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/10/8
     */
    private void handleTask() {
        while (Objects.nonNull(queue.peek())) {
            this.handleTask(queue.poll());
        }
    }

    /**
     * 执行任务
     *
     * @param task
     * @return void
     * @author mengjie_chen
     * @date 2020/10/8
     */
    private <R> void handleTask(CallbackTask<R> task) {
        ListenableFuture<R> future = gPool.submit(task::execute);
        Futures.addCallback(future, new FutureCallback<R>() {
            @Override
            public void onSuccess(@Nullable R r) {
                task.onBack(r);
            }

            @Override
            public void onFailure(Throwable throwable) {
                task.onException(throwable);
            }
        }, gPool);
    }
}
