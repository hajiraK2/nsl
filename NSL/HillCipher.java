import java.util.Scanner;

public class HillCipher {

    static int[][] key = new int[2][2];
    static int[][] inverse = new int[2][2];

    static void findInverse() {

        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % 26;
        if (det < 0) det += 26;

        int detInv = 0;

        for (int i = 1; i < 26; i++)
            if ((det * i) % 26 == 1) {
                detInv = i;
                break;
            }

        inverse[0][0] = ( key[1][1] * detInv) % 26;
        inverse[0][1] = (-key[0][1] * detInv) % 26;
        inverse[1][0] = (-key[1][0] * detInv) % 26;
        inverse[1][1] = ( key[0][0] * detInv) % 26;

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                if (inverse[i][j] < 0)
                    inverse[i][j] += 26;
    }

    static String encrypt(String text) {

        int[] p = new int[2];
        int[] c = new int[2];

        text = text.toUpperCase();

        for (int i = 0; i < 2; i++)
            p[i] = text.charAt(i) - 'A';

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                c[i] += key[i][j] * p[j];

            c[i] %= 26;
        }

        return "" + (char)(c[0] + 'A') + (char)(c[1] + 'A');
    }

    static String decrypt(String text) {

        int[] c = new int[2];
        int[] p = new int[2];

        text = text.toUpperCase();

        for (int i = 0; i < 2; i++)
            c[i] = text.charAt(i) - 'A';

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                p[i] += inverse[i][j] * c[j];

            p[i] %= 26;
        }

        return "" + (char)(p[0] + 'A') + (char)(p[1] + 'A');
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Key Matrix (2x2):");

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                key[i][j] = sc.nextInt();

        findInverse();

        System.out.println("Inverse Matrix:");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                System.out.print(inverse[i][j] + " ");
            System.out.println();
        }

        System.out.print("Enter 2-letter plaintext: ");
        String text = sc.next();

        String cipher = encrypt(text);
        System.out.println("Encrypted Text: " + cipher);

        System.out.println("Decrypted Text: " + decrypt(cipher));
    }
}