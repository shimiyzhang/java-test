package multithreading.virtualThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 练习
// 使用虚拟线程调度10万个任务并观察耗时：
// 再将ExecutorService改为线程池模式并对比结果。
public class Test {
    public static void main(String[] args) {
        ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            es.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 0;
            });
        }
        es.shutdown();
        while (!es.isTerminated()) {
            // 等待所有任务执行完成
        }
        long end = System.currentTimeMillis();
        System.out.println("使用虚拟线程耗时：" + (end - start) + "ms");

        ExecutorService poolEs = Executors.newFixedThreadPool(100);
        long poolStart = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            poolEs.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 0;
            });
        }
        poolEs.shutdown();
        while (!poolEs.isTerminated()) {
            // 等待所有任务执行完成
        }
        long poolEnd = System.currentTimeMillis();
        System.out.println("使用线程池模式耗时：" + (poolEnd - poolStart) + "ms");
    }
}
