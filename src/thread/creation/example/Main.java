package thread.creation.example;

import static java.lang.System.*;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(
      new Runnable() {
        @Override
        public void run() {
          // code that will run in a new thread
          out.println(
            "We are now in thread " + Thread.currentThread().getName()
          );
          out.println(
            "Current thread priority is + " +
            Thread.currentThread().getPriority()
          );
        }
      }
    );
    thread.setName("New Worker Thread");
    thread.setPriority(Thread.MAX_PRIORITY);

    out.println(
      "We are in thread: " +
      Thread.currentThread().getName() +
      " before startin a new thread"
    );
    thread.start();
    out.println(
      "We are in thread: " +
      Thread.currentThread().getName() +
      " after starting a new thread"
    );
  }
}
