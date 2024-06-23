package thread.creation.cases.study;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

  private static final int MAX_PASSWORD = 9999;

  public static void main(String[] args) {
    final Random random = new Random();

    final Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

    List<Thread> threads = Arrays.asList(
      new AscendingHackerThread(vault),
      new DescendingHackerThread(vault),
      new PoliceThread()
    );

    for (Thread thread : threads) {
      thread.start();
    }
  }

  private static class Vault {

    private int password;

    public Vault(final int password) {
      this.password = password;
    }

    public boolean isCorrectPassword(final int guess) {
      try {
        Thread.sleep(5);
      } catch (InterruptedException e) {}
      return this.password == guess;
    }
  }

  private abstract static class HackerThread extends Thread {

    protected Vault vault;

    public HackerThread(final Vault vault) {
      this.vault = vault;
      this.setName(this.getClass().getSimpleName());
      this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void start() {
      System.out.println("Starting thread " + this.getName());
      super.start();
    }
  }

  private static class AscendingHackerThread extends HackerThread {

    public AscendingHackerThread(final Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = 0; guess < MAX_PASSWORD; guess++) {
        if (vault.isCorrectPassword(guess)) {
          System.out.println(this.getName() + " guessed the password " + guess);
          System.exit(0);
        }
      }
    }
  }

  private static class DescendingHackerThread extends HackerThread {

    public DescendingHackerThread(final Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
        if (vault.isCorrectPassword(guess)) {
          System.out.println(this.getName() + " guessed the password " + guess);
          System.exit(0);
        }
      }
    }
  }

  private static class PoliceThread extends Thread {

    @Override
    public void run() {
      for (int i = 10; i > 0; i--) {
        try {
          Thread.sleep(1000);
        } catch (Exception e) {}
        System.out.println(i);
      }

      System.out.println("Game over for you hackers");
      System.exit(0);
    }
  }
}
