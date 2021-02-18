package juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @description:
 * @author: Zhoust
 * @date: 2020/06/22 下午4:47
 * @version: V1.0
 */
@Slf4j
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2, 3, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 10; i++) {
            SendNoticeTask task = new SendNoticeTask(i);
            try {
                executorService.execute(task);
            } catch (Exception e) {
                log.info("e");
            }
            System.out.println(executorService.toString());
        }
        System.out.println("Main thread finish");
    }

    static class SendNoticeTask implements Runnable {

        private int count;

        public SendNoticeTask(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start to send " + count + "...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finish " + Thread.currentThread().getName() +"(send " + count + ")");
        }

    }

}