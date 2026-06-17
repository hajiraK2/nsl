import java.math.BigInteger;
import java.util.Scanner;

public class FermatEuler {

    // Function to compute gcd
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    // Function to compute Euler Totient Function φ(n)
    public static BigInteger phi(BigInteger n) {
        BigInteger result = n;
        BigInteger i = BigInteger.valueOf(2);

        BigInteger temp = n;

        while (i.multiply(i).compareTo(temp) <= 0) {
            if (temp.mod(i).equals(BigInteger.ZERO)) {
                while (temp.mod(i).equals(BigInteger.ZERO)) {
                    temp = temp.divide(i);
                }
                result = result.subtract(result.divide(i));
            }
            i = i.add(BigInteger.ONE);
        }

        if (temp.compareTo(BigInteger.ONE) > 0) {
            result = result.subtract(result.divide(temp));
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input values
        System.out.print("Enter value of a: ");
        BigInteger a = sc.nextBigInteger();

        System.out.print("Enter prime number p (for Fermat): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter number n (for Euler): ");
        BigInteger n = sc.nextBigInteger();

        // Fermat's Theorem
        if (p.isProbablePrime(100)) {
            BigInteger fermat = a.modPow(p.subtract(BigInteger.ONE), p);
            System.out.println("\nFermat Result (a^(p-1) mod p): " + fermat);
        } else {
            System.out.println("\n❌ p is not prime. Fermat's theorem not applicable.");
        }

        // Euler's Theorem
        if (gcd(a, n).equals(BigInteger.ONE)) {
            BigInteger phi_n = phi(n);
            BigInteger euler = a.modPow(phi_n, n);

            System.out.println("\nEuler Totient φ(n): " + phi_n);
            System.out.println("Euler Result (a^φ(n) mod n): " + euler);
        } else {
            System.out.println("\n❌ a and n are not coprime. Euler's theorem not applicable.");
        }

        sc.close();
    }
}