package thread.reentrantlock;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker{
    private Executor executor;
    private ExecutorService executorService;
    ScheduledExecutorService  ScheduledExecutorService;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
}

public class ProducerConsumerWithLocks {
    public static void main(String[] args) {
        Thread t1 = new Thread(
      new Runnable() {
        @Override
        public void run() {
          
        }
      }
    );
    Thread t2 = new Thread(
      new Runnable() {
        @Override
        public void run() {
          
        }
      }
    );
    }
}
