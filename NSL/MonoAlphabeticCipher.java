import java.util.Scanner;

public class MonoAlphabeticCipher {

    static String encrypt(String text, String key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch))
                result += key.charAt(alphabet.indexOf(ch));
            else
                result += ch;
        }

        return result;
    }

    static String decrypt(String text, String key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch))
                result += alphabet.charAt(key.indexOf(ch));
            else
                result += ch;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 26-letter key (no duplicates): ");
        String key = sc.nextLine().toUpperCase();

        if (key.length() != 26) {
            System.out.println("Invalid key! Key must contain exactly 26 letters.");
            return;
        }

        System.out.println("Enter the text: ");
        String text = sc.nextLine();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
    }
}