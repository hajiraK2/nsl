import java.math.BigInteger;
import java.util.Scanner;

public class FermatEuler {

    static BigInteger phi(BigInteger n) {

        BigInteger result = n, temp = n;

        for (BigInteger i = BigInteger.valueOf(2);
             i.multiply(i).compareTo(temp) <= 0;
             i = i.add(BigInteger.ONE)) {

            if (temp.mod(i).equals(BigInteger.ZERO)) {

                while (temp.mod(i).equals(BigInteger.ZERO))
                    temp = temp.divide(i);

                result = result.subtract(result.divide(i));
            }
        }

        if (temp.compareTo(BigInteger.ONE) > 0)
            result = result.subtract(result.divide(temp));

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of a: ");
        BigInteger a = sc.nextBigInteger();

        System.out.print("Enter prime number p (for Fermat): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter number n (for Euler): ");
        BigInteger n = sc.nextBigInteger();

        if (p.isProbablePrime(100))
            System.out.println("\nFermat Result (a^(p-1) mod p): "
                    + a.modPow(p.subtract(BigInteger.ONE), p));
        else
            System.out.println("\np is not prime. Fermat's theorem not applicable.");

        if (a.gcd(n).equals(BigInteger.ONE)) {

            BigInteger ph = phi(n);

            System.out.println("\nEuler Totient φ(n): " + ph);

            System.out.println("Euler Result (a^φ(n) mod n): "
                    + a.modPow(ph, n));
        }
        else
            System.out.println("\na and n are not coprime. Euler's theorem not applicable.");
    }
}