import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a prime number (p): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter a primitive root (g): ");
        BigInteger g = sc.nextBigInteger();

        System.out.print("Enter Alice's private key (a): ");
        BigInteger a = sc.nextBigInteger();

        System.out.print("Enter Bob's private key (b): ");
        BigInteger b = sc.nextBigInteger();

        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);

        System.out.println("\nAlice's Public Key (A): " + A);
        System.out.println("Bob's Public Key (B): " + B);

        BigInteger keyA = B.modPow(a, p);
        BigInteger keyB = A.modPow(b, p);

        System.out.println("\nShared Secret Key computed by Alice: " + keyA);
        System.out.println("Shared Secret Key computed by Bob: " + keyB);

        if (keyA.equals(keyB))
            System.out.println("\nKey Exchange Successful! Shared key is same.");
        else
            System.out.println("\nKey Exchange Failed!");
    }
}