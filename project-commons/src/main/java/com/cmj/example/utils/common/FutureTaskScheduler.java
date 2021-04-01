package com.cmj.example.utils.common;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mengjie_chen
 * @description 任务调度工具类
 * @date 2020/10/7
 */
public class FutureTaskScheduler extends Thread {

    /**
     * 异步任务队列
     */
    private static final ConcurrentLinkedQueue<ExecuteTask> queue = new ConcurrentLinkedQueue<>();
    /**
     * 线程休眠时间
     */
    private long sleepTime = 200;
    /**
     * 线程池
     */
    private final ExecutorService pool = Executors.newFixedThreadPool(10);
    private static final FutureTaskScheduler futureTaskScheduler = new FutureTaskScheduler();

    private FutureTaskScheduler() {
        this.start();
    }

    /**
     * 添加任务
     *
     * @param executeTask
     * @return void
     * @author mengjie_chen
     * @date 2020/10/7
     */
    public static void add(ExecuteTask executeTask) {
        queue.add(executeTask);
    }

    @Override
    public void run() {
        while (true) {
            this.handleTask();
            this.threadSleep();
        }
    }

    /**
     * 休眠线程
     *
     * @param
     * @return void
     * @author mengjie_chen
     * @date 2020/10/7
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
     * @date 2020/10/7
     */
    private void handleTask() {
        while (Objects.nonNull(queue.peek())) {
            ExecuteTask executeTask = queue.poll();
            this.handleTask(executeTask);
        }
    }

    /**
     * 执行任务
     *
     * @param executeTask
     * @return void
     * @author mengjie_chen
     * @date 2020/10/8
     */
    private void handleTask(ExecuteTask executeTask) {
        pool.execute(new ExecuteRunnable(executeTask));
    }

    class ExecuteRunnable implements Runnable {
        private ExecuteTask executeTask;

        ExecuteRunnable(ExecuteTask executeTask) {
            this.executeTask = executeTask;
        }

        public void run() {
            executeTask.execute();
        }
    }
}
