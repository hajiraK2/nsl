import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input prime number p
        System.out.print("Enter a prime number (p): ");
        BigInteger p = sc.nextBigInteger();

        // Input primitive root g
        System.out.print("Enter a primitive root (g): ");
        BigInteger g = sc.nextBigInteger();

        // Alice private key
        System.out.print("Enter Alice's private key (a): ");
        BigInteger a = sc.nextBigInteger();

        // Bob private key
        System.out.print("Enter Bob's private key (b): ");
        BigInteger b = sc.nextBigInteger();

        // Compute public keys
        BigInteger A = g.modPow(a, p); // Alice's public key
        BigInteger B = g.modPow(b, p); // Bob's public key

        System.out.println("\nAlice's Public Key (A): " + A);
        System.out.println("Bob's Public Key (B): " + B);

        // Compute shared secret keys
        BigInteger keyAlice = B.modPow(a, p);
        BigInteger keyBob = A.modPow(b, p);

        System.out.println("\nShared Secret Key computed by Alice: " + keyAlice);
        System.out.println("Shared Secret Key computed by Bob: " + keyBob);

        // Verify
        if (keyAlice.equals(keyBob)) {
            System.out.println("\nKey Exchange Successful! Shared key is same.");
        } else {
            System.out.println("\nKey Exchange Failed!");
        }

        sc.close();
    }
}