import java.math.BigInteger;
import java.security.SecureRandom;

public class BlumBlumShub {

    BigInteger p, q, m, seed;

    BlumBlumShub(int bits) {

        SecureRandom r = new SecureRandom();

        do
            p = BigInteger.probablePrime(bits, r);
        while (!p.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)));

        do
            q = BigInteger.probablePrime(bits, r);
        while (!q.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)));

        m = p.multiply(q);

        do
            seed = new BigInteger(bits * 2, r);
        while (!seed.gcd(m).equals(BigInteger.ONE));
    }

    int nextBit() {
        seed = seed.modPow(BigInteger.valueOf(2), m);
        return seed.mod(BigInteger.valueOf(2)).intValue();
    }

    BigInteger nextNumber(int bits) {

        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < bits; i++) {
            result = result.shiftLeft(1);
            result = result.or(BigInteger.valueOf(nextBit()));
        }

        return result;
    }

    public static void main(String[] args) {

        BlumBlumShub bbs = new BlumBlumShub(64);

        System.out.println("Generated Random Bits:");

        for (int i = 0; i < 20; i++)
            System.out.print(bbs.nextBit() + " ");

        System.out.println("\n\nGenerated Random Number (16 bits):");
        System.out.println(bbs.nextNumber(16));
    }
}