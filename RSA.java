import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    BigInteger p, q, n, phi, e, d;
    SecureRandom random = new SecureRandom();

    RSA() {

        p = BigInteger.probablePrime(1024, random);
        q = BigInteger.probablePrime(1024, random);

        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(65537);

        while (!phi.gcd(e).equals(BigInteger.ONE))
            e = e.add(BigInteger.TWO);

        d = e.modInverse(phi);
    }

    BigInteger encrypt(BigInteger msg) {
        return msg.modPow(e, n);
    }

    BigInteger decrypt(BigInteger cipher) {
        return cipher.modPow(d, n);
    }

    public static void main(String[] args) {

        RSA rsa = new RSA();

        String text = "HELLO";
        BigInteger msg = new BigInteger(text.getBytes());

        System.out.println("Original Message: " + text);

        BigInteger encrypted = rsa.encrypt(msg);
        System.out.println("Encrypted Message: " + encrypted);

        BigInteger decrypted = rsa.decrypt(encrypted);

        System.out.println("Decrypted Message: "
                + new String(decrypted.toByteArray()));
    }
}