package com.yll.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author：linlin.yang
 * @date：2018/3/6 17:12
 */
public class SimpleCallable implements Callable<String> {
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "正在执行");
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建任务
        List<FutureTask> futureTasks = new ArrayList<FutureTask>();
        for (int i = 0; i < 5; i++) {
            SimpleCallable callable = new SimpleCallable();
            FutureTask<String> futureTask = new FutureTask<String>(callable);
            futureTasks.add(futureTask);
        }

        //创建调度器
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (FutureTask futureTask : futureTasks) {
            executorService.execute(futureTask);
        }

        while (true) {
            boolean finished = true;
            for (FutureTask futureTask : futureTasks) {
//                if (futureTask.isDone()) {
//                    System.out.println(futureTask.get());
//                } else {
//                    finished = false;
//                }

                if (!futureTask.isDone()) {
                    finished = false;
                }
            }

            if (finished) {
                break;
            }
        }
    }
}
