package thread.creation.example;

import java.lang.Thread.UncaughtExceptionHandler;

public class AddExceptionToThread {

  public static void main(String[] args) throws InterruptedException {
    final Thread thread = new Thread(
      new Runnable() {
        @Override
        public void run() {
          throw new RuntimeException("Intentional Exception");
        }
      }
    );
    thread.setName("Misbehaving thread");
    thread.setUncaughtExceptionHandler(
      new UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
          System.out.println(
            "A critical error happened in thread " +
            t.getName() +
            " the error is " +
            e.getMessage()
          );
        }
      }
    );
    thread.start();
  }
}
