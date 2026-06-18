import java.util.*;

public class trans {

    static Integer[] getOrder(String key) {

        Integer[] order = new Integer[key.length()];

        for (int i = 0; i < key.length(); i++)
            order[i] = i;

        Arrays.sort(order, Comparator.comparingInt(i -> key.charAt(i)));

        return order;
    }

    static String singleEncrypt(String text, String key) {

        int cols = key.length();
        int rows = (int)Math.ceil((double)text.length() / cols);

        char[][] mat = new char[rows][cols];

        int k = 0;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = (k < text.length()) ? text.charAt(k++) : 'X';

        Integer[] order = getOrder(key);

        String result = "";

        for (int c : order)
            for (int r = 0; r < rows; r++)
                result += mat[r][c];

        return result;
    }

    static String singleDecrypt(String cipher, String key) {

        int cols = key.length();
        int rows = cipher.length() / cols;

        char[][] mat = new char[rows][cols];

        Integer[] order = getOrder(key);

        int k = 0;

        for (int c : order)
            for (int r = 0; r < rows; r++)
                mat[r][c] = cipher.charAt(k++);

        String result = "";

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result += mat[i][j];

        return result;
    }

    static String doubleEncrypt(String text, String k1, String k2) {
        return singleEncrypt(singleEncrypt(text, k1), k2);
    }

    static String doubleDecrypt(String text, String k1, String k2) {
        return singleDecrypt(singleDecrypt(text, k2), k1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Transposition Cipher Program ===");
        System.out.println("1. Single Encryption and Decryption");
        System.out.println("2. Double Encryption and Decryption");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter text: ");
        String text = sc.nextLine().toUpperCase().replaceAll(" ", "");

        switch (choice) {

            case 1:

                System.out.print("Enter key: ");
                String key = sc.nextLine().toUpperCase();

                String enc = singleEncrypt(text, key);

                System.out.println("Encrypted: " + enc);
                System.out.println("Decrypted: " + singleDecrypt(enc, key));

                break;

            case 2:

                System.out.print("Enter first key: ");
                String k1 = sc.nextLine().toUpperCase();

                System.out.print("Enter second key: ");
                String k2 = sc.nextLine().toUpperCase();

                String enc2 = doubleEncrypt(text, k1, k2);

                System.out.println("Encrypted: " + enc2);
                System.out.println("Decrypted: " + doubleDecrypt(enc2, k1, k2));

                break;

            default:
                System.out.println("Invalid choice!");
        }
    }
}