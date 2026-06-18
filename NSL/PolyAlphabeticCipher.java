import java.util.Scanner;

public class PolyAlphabeticCipher {

    static String encrypt(String text, String key) {
        String result = "";

        text = text.toUpperCase();
        key = key.toUpperCase();

        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                int shift = key.charAt(keyIndex % key.length()) - 'A';
                result += (char) ((ch - 'A' + shift) % 26 + 'A');
                keyIndex++;
            } else {
                result += ch;
            }
        }
        return result;
    }

    static String decrypt(String text, String key) {
        String result = "";

        text = text.toUpperCase();
        key = key.toUpperCase();

        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                int shift = key.charAt(keyIndex % key.length()) - 'A';
                result += (char) ((ch - 'A' - shift + 26) % 26 + 'A');
                keyIndex++;
            } else {
                result += ch;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String plainText = sc.nextLine();

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        String encrypted = encrypt(plainText, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
    }
}