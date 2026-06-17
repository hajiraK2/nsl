import java.util.*;

public class SDES {

    // Permutation tables
    static int[] P10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    static int[] P8 = {6, 3, 7, 4, 8, 5, 10, 9};
    static int[] P4 = {2, 4, 3, 1};
    static int[] IP = {2, 6, 3, 1, 4, 8, 5, 7};
    static int[] IP_INV = {4, 1, 3, 5, 7, 2, 8, 6};
    static int[] EP = {4, 1, 2, 3, 2, 3, 4, 1};

    // S-Boxes
    static int[][] S0 = {
        {1, 0, 3, 2},
        {3, 2, 1, 0},
        {0, 2, 1, 3},
        {3, 1, 3, 2}
    };

    static int[][] S1 = {
        {0, 1, 2, 3},
        {2, 0, 1, 3},
        {3, 0, 1, 0},
        {2, 1, 0, 3}
    };

    // Permutation function
    static String permute(String input, int[] table) {
        StringBuilder output = new StringBuilder();
        for (int i : table) {
            output.append(input.charAt(i - 1));
        }
        return output.toString();
    }

    // Left shift
    static String leftShift(String input, int shifts) {
        return input.substring(shifts) + input.substring(0, shifts);
    }

    // XOR operation
    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) ^ b.charAt(i));
        }
        return result.toString();
    }

    // S-Box lookup
    static String sBox(String input, int[][] sbox) {
        int row = Integer.parseInt("" + input.charAt(0) + input.charAt(3), 2);
        int col = Integer.parseInt("" + input.charAt(1) + input.charAt(2), 2);
        int val = sbox[row][col];
        return String.format("%2s", Integer.toBinaryString(val)).replace(' ', '0');
    }

    // Generate keys K1 and K2
    static String[] generateKeys(String key) {
        key = permute(key, P10);
        String left = key.substring(0, 5);
        String right = key.substring(5);

        left = leftShift(left, 1);
        right = leftShift(right, 1);

        String K1 = permute(left + right, P8);

        left = leftShift(left, 2);
        right = leftShift(right, 2);

        String K2 = permute(left + right, P8);

        return new String[]{K1, K2};
    }

    // Function fk
    static String fk(String input, String key) {
        String left = input.substring(0, 4);
        String right = input.substring(4);

        String temp = permute(right, EP);
        temp = xor(temp, key);

        String leftPart = temp.substring(0, 4);
        String rightPart = temp.substring(4);

        String s0 = sBox(leftPart, S0);
        String s1 = sBox(rightPart, S1);

        String combined = permute(s0 + s1, P4);
        String result = xor(left, combined);

        return result + right;
    }

    // Swap function
    static String swap(String input) {
        return input.substring(4) + input.substring(0, 4);
    }

    // Encryption
    static String encrypt(String plaintext, String key) {
        String[] keys = generateKeys(key);
        String ip = permute(plaintext, IP);

        String round1 = fk(ip, keys[0]);
        String swapped = swap(round1);
        String round2 = fk(swapped, keys[1]);

        return permute(round2, IP_INV);
    }

    // Decryption
    static String decrypt(String ciphertext, String key) {
        String[] keys = generateKeys(key);
        String ip = permute(ciphertext, IP);

        String round1 = fk(ip, keys[1]);
        String swapped = swap(round1);
        String round2 = fk(swapped, keys[0]);

        return permute(round2, IP_INV);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 10-bit key: ");
        String key = sc.next();

        System.out.print("Enter 8-bit plaintext: ");
        String plaintext = sc.next();

        String cipher = encrypt(plaintext, key);
        System.out.println("Encrypted: " + cipher);

        String decrypted = decrypt(cipher, key);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}