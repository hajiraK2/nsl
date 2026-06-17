import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 1024;
    private SecureRandom random = new SecureRandom();

    // Constructor: Generate keys
    public RSA() {
        p = BigInteger.probablePrime(bitLength, random);
        q = BigInteger.probablePrime(bitLength, random);

        n = p.multiply(q);
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Choose e
        e = BigInteger.valueOf(65537); // common choice
        while (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.TWO);
        }

        // Compute d
        d = e.modInverse(phi);
    }

    // Encrypt message
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // Decrypt message
    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, n);
    }

    // Getters
    public BigInteger getPublicKeyE() { return e; }
    public BigInteger getModulusN() { return n; }

    public static void main(String[] args) {

        RSA rsa = new RSA();

        String text = "HELLO";
        BigInteger message = new BigInteger(text.getBytes());

        System.out.println("Original Message: " + text);

        // Encrypt
        BigInteger encrypted = rsa.encrypt(message);
        System.out.println("Encrypted Message: " + encrypted);

        // Decrypt
        BigInteger decrypted = rsa.decrypt(encrypted);
        String decryptedText = new String(decrypted.toByteArray());

        System.out.println("Decrypted Message: " + decryptedText);
    }
}