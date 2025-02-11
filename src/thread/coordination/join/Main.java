package join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    final List<Long> inputNumbers = Arrays.asList(
      1000000000L,
      3435L,
      35435L,
      2324L,
      4656L,
      23L,
      5556L
    );

    final List<FactorialThread> threads = new ArrayList<>();
    for (long inputNumber : inputNumbers) {
      threads.add(new FactorialThread(inputNumber));
    }
    for (Thread thread : threads) {
      thread.setDaemon(true);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join(2000);
    }

    for (int i = 0; i < inputNumbers.size(); i++) {
      final FactorialThread factorialThread = threads.get(i);
      if (factorialThread.isFinished()) {
        System.out.println(
          "Factorial of" +
          inputNumbers.get(i) +
          " is " +
          factorialThread.getResult()
        );
      } else {
        System.out.println(
          "The calculation for " + inputNumbers.get(i) + " is still in progress"
        );
      }
    }
  }

  public static class FactorialThread extends Thread {

    private long inputNumber;
    private BigInteger result = BigInteger.ZERO;
    private boolean isFinished = false;

    public FactorialThread(final long inputNumber) {
      this.inputNumber = inputNumber;
    }

    @Override
    public void run() {
      this.result = factorial(inputNumber);
      this.isFinished = true;
    }

    public BigInteger factorial(final long n) {
      BigInteger tempResult = BigInteger.ONE;
      for (long i = n; i > 0; i--) {
        tempResult = tempResult.multiply(BigInteger.valueOf(i));
      }
      return tempResult;
    }

    public BigInteger getResult() {
      return result;
    }

    public boolean isFinished() {
      return isFinished;
    }
  }
}
