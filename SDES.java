import java.util.*;

public class SDES {

    static int[] P10={3,5,2,7,4,10,1,9,8,6};
    static int[] P8={6,3,7,4,8,5,10,9};
    static int[] P4={2,4,3,1};
    static int[] IP={2,6,3,1,4,8,5,7};
    static int[] IP_INV={4,1,3,5,7,2,8,6};
    static int[] EP={4,1,2,3,2,3,4,1};

    static int[][] S0={
        {1,0,3,2},
        {3,2,1,0},
        {0,2,1,3},
        {3,1,3,2}
    };

    static int[][] S1={
        {0,1,2,3},
        {2,0,1,3},
        {3,0,1,0},
        {2,1,0,3}
    };

    static String permute(String s,int[] table){
        String r="";
        for(int i:table)
            r+=s.charAt(i-1);
        return r;
    }

    static String leftShift(String s,int n){
        return s.substring(n)+s.substring(0,n);
    }

    static String xor(String a,String b){
        String r="";
        for(int i=0;i<a.length();i++)
            r+=(a.charAt(i)^b.charAt(i));
        return r;
    }

    static String sBox(String s,int[][] box){
        int row=Integer.parseInt(""+s.charAt(0)+s.charAt(3),2);
        int col=Integer.parseInt(""+s.charAt(1)+s.charAt(2),2);
        return String.format("%2s",
                Integer.toBinaryString(box[row][col]))
                .replace(' ','0');
    }

    static String[] generateKeys(String key){

        key=permute(key,P10);

        String left=key.substring(0,5);
        String right=key.substring(5);

        left=leftShift(left,1);
        right=leftShift(right,1);

        String k1=permute(left+right,P8);

        left=leftShift(left,2);
        right=leftShift(right,2);

        String k2=permute(left+right,P8);

        return new String[]{k1,k2};
    }

    static String fk(String input,String key){

        String left=input.substring(0,4);
        String right=input.substring(4);

        String temp=xor(permute(right,EP),key);

        String s0=sBox(temp.substring(0,4),S0);
        String s1=sBox(temp.substring(4),S1);

        String p4=permute(s0+s1,P4);

        return xor(left,p4)+right;
    }

    static String swap(String s){
        return s.substring(4)+s.substring(0,4);
    }

    static String encrypt(String text,String key){

        String[] k=generateKeys(key);

        String r1=fk(permute(text,IP),k[0]);
        String r2=fk(swap(r1),k[1]);

        return permute(r2,IP_INV);
    }

    static String decrypt(String text,String key){

        String[] k=generateKeys(key);

        String r1=fk(permute(text,IP),k[1]);
        String r2=fk(swap(r1),k[0]);

        return permute(r2,IP_INV);
    }

    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);

        System.out.print("Enter 10-bit key: ");
        String key=sc.next();

        System.out.print("Enter 8-bit plaintext: ");
        String text=sc.next();

        String cipher=encrypt(text,key);
        System.out.println("Encrypted: "+cipher);

        System.out.println("Decrypted: "+decrypt(cipher,key));
    }
}