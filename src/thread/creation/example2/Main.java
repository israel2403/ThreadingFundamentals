package thread.creation.example2;

import static java.lang.System.*;

public class Main {

  public static void main(String[] args) {
    final Thread thread = new NewThread();
    thread.start();
  }

  private static class NewThread extends Thread {

    @Override
    public void run() {
      // Code that executes on the new thread
      out.println("Hello from " + this.currentThread().getName());
    }
  }
}
