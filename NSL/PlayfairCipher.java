import java.util.*;

public class PlayfairCipher {

    static char[][] matrix = new char[5][5];
    static Map<Character, int[]> map = new HashMap<>();

    static void generateMatrix(String key) {

        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        Set<Character> used = new LinkedHashSet<>();

        for (char c : key.toCharArray())
            used.add(c);

        for (char c = 'A'; c <= 'Z'; c++)
            if (c != 'J')
                used.add(c);

        Iterator<Character> it = used.iterator();

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = it.next();
                map.put(matrix[i][j], new int[]{i, j});
            }
    }

    static String prepareText(String text) {

        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        String result = "";

        for (int i = 0; i < text.length(); i++) {
            result += text.charAt(i);

            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1))
                result += 'X';
        }

        if (result.length() % 2 != 0)
            result += 'X';

        return result;
    }

    static String encrypt(String text) {

        String cipher = "";

        for (int i = 0; i < text.length(); i += 2) {

            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] p1 = map.get(a);
            int[] p2 = map.get(b);

            if (p1[0] == p2[0]) {
                cipher += matrix[p1[0]][(p1[1] + 1) % 5];
                cipher += matrix[p2[0]][(p2[1] + 1) % 5];
            }
            else if (p1[1] == p2[1]) {
                cipher += matrix[(p1[0] + 1) % 5][p1[1]];
                cipher += matrix[(p2[0] + 1) % 5][p2[1]];
            }
            else {
                cipher += matrix[p1[0]][p2[1]];
                cipher += matrix[p2[0]][p1[1]];
            }
        }
        return cipher;
    }

    static String decrypt(String text) {

        String plain = "";

        for (int i = 0; i < text.length(); i += 2) {

            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] p1 = map.get(a);
            int[] p2 = map.get(b);

            if (p1[0] == p2[0]) {
                plain += matrix[p1[0]][(p1[1] + 4) % 5];
                plain += matrix[p2[0]][(p2[1] + 4) % 5];
            }
            else if (p1[1] == p2[1]) {
                plain += matrix[(p1[0] + 4) % 5][p1[1]];
                plain += matrix[(p2[0] + 4) % 5][p2[1]];
            }
            else {
                plain += matrix[p1[0]][p2[1]];
                plain += matrix[p2[0]][p1[1]];
            }
        }
        return plain;
    }

    static void printMatrix() {

        System.out.println("\nPlayfair Matrix:");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(matrix[i][j] + " ");

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        generateMatrix(key);
        printMatrix();

        System.out.print("\nEnter Plain Text: ");
        String text = sc.nextLine();

        String prepared = prepareText(text);
        String encrypted = encrypt(prepared);

        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted Text: " + decrypted);
    }
}