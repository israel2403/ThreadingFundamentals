package deamon;

/**
 * Main
 */
import java.math.BigInteger;

public class Main {

  public static void main(String[] args) {
    final Thread thread = new Thread(
      new LongComputation(
        BigInteger.valueOf(200000L),
        BigInteger.valueOf(1000000L)
      )
    );
    thread.setDaemon(true); 
    thread.start();
    thread.interrupt();
  }

  private static class LongComputation implements Runnable {

    private BigInteger base;
    private BigInteger power;

    public LongComputation(final BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    @Override
    public void run() {
      System.out.println(base + "^" + power + " = " + pow(base, power));
    }

    private BigInteger pow(final BigInteger base, final BigInteger power) {
      BigInteger result = BigInteger.ONE;
      for (
        BigInteger i = BigInteger.ZERO;
        i.compareTo(power) != 0;
        i = i.add(BigInteger.ONE)
      ) {
        result = result.multiply(base);
      }
      return result;
    }
  }
}
