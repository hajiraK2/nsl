import java.util.*;

public class LCG {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter seed (X0): ");
        int seed = sc.nextInt();

        System.out.print("Enter multiplier (a): ");
        int a = sc.nextInt();

        System.out.print("Enter increment (c): ");
        int c = sc.nextInt();

        System.out.print("Enter modulus (m): ");
        int m = sc.nextInt();

        System.out.print("Enter number of random numbers to generate: ");
        int n = sc.nextInt();

        int X = seed;

        System.out.println("\nGenerated Pseudo Random Numbers:");

        for (int i = 0; i < n; i++) {
            X = (a * X + c) % m;
            System.out.println("X" + (i + 1) + " = " + X);
        }

        sc.close();
    }
}