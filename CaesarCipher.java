import java.util.Scanner;

public class CaesarCipher {

    static String encrypt(String text, int shift) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isUpperCase(ch))
                result += (char) ((ch - 'A' + shift) % 26 + 'A');
            else if (Character.isLowerCase(ch))
                result += (char) ((ch - 'a' + shift) % 26 + 'a');
            else
                result += ch;
        }

        return result;
    }

    static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(text, shift);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("Decrypted Text: " + decrypted);
    }
}