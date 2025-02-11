package thread.sycronizationtopic;

public class App {

  private static int counter1;
  private static int counter2;

  private static final Object lock1 = new Object();
  private static final Object lock2 = new Object();

  public static void increment1() {
    synchronized (lock1) {
      counter1++;
    }
  }

  public static void increment2() {
    synchronized (lock2) {
      counter2++;
    }
  }

  public static void process() {
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 100; ++i) {
        increment1();
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 100; ++i) {
        increment2();
      }
    });

    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("The counter1 is: " + counter1);
    System.out.println("The counter2 is: " + counter2);
  }

  public static void main(String[] args) {
    process();
  }
}
