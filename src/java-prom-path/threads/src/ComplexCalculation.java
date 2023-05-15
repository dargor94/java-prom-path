import java.math.BigInteger;


/**
 * Ejercicio realzado en curso udemy
 *
 * @url: https://globallogic.udemy.com/course/java-multithreading-concurrency-performance-optimization
 */
public class ComplexCalculation {

    public static void main(String[] args) {
        var main = new ComplexCalculation();
        var result = main.calculateResult(BigInteger.valueOf(10), BigInteger.valueOf(2),
                BigInteger.valueOf(2), BigInteger.valueOf(3));
        System.out.println("result = " + result);
    }

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;

        PowerCalculatingThread powT1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread powT2 = new PowerCalculatingThread(base2, power2);

        powT1.start();
        powT2.start();
        try {
            powT1.join();
            powT2.join();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        result = powT1.getResult().add(powT2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private final BigInteger base;
        private final BigInteger power;
        private BigInteger result = BigInteger.ONE;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            result = base.pow(power.intValue());
        }

        public BigInteger getResult() {
            return result;
        }
    }
}