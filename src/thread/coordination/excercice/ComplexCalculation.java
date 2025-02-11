package excercice;

import java.math.BigInteger;

public class ComplexCalculation {

  public BigInteger calculateResult(
    BigInteger base1,
    BigInteger power1,
    BigInteger base2,
    BigInteger power2
  ) {
    BigInteger result;
    /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
    final PowerCalculatingThread thread1 = new PowerCalculatingThread(
      base1,
      power1
    );
    final PowerCalculatingThread thread2 = new PowerCalculatingThread(
      base2,
      power2
    );

    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    result = thread1.getResult().add(thread2.getResult());
    return result;
  }

  private static class PowerCalculatingThread extends Thread {

    private BigInteger result = BigInteger.ONE;
    private BigInteger base;
    private BigInteger power;

    public PowerCalculatingThread(BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    @Override
    public void run() {
      /*
           Implement the calculation of result = base ^ power
           */
      pow();
    }

    private void pow() {
      for (
        BigInteger i = BigInteger.ZERO;
        i.compareTo(power) != 0;
        i = i.add(BigInteger.ONE)
      ) {
        this.result = this.result.multiply(this.base);
      }
    }

    public BigInteger getResult() {
      return this.result;
    }
  }
}
