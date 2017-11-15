package com.company;

/**
 * Created by gordon on 10/24/17.
 */
public class DynamicPrograming {

    public static String addition(String binString1, String binString2) {
        if (binString1 == null) return binString2;
        if (binString2 == null) return binString1;
        int add = 0;
        int len1 = binString1.length();
        int len2 = binString2.length();
        int p1 = len1 - 1, p2 = len2 - 1;
        StringBuilder builder = new StringBuilder();
        while (p1 >= 0 || p2 >= 0 || add > 0) {
            int mod1 = p1 < len1 && p1 >= 0 && binString1.charAt(p1) == '1' ? 1 : 0;
            int mod2 = p2 < len2 && p2 >= 0 && binString2.charAt(p2) == '1' ? 1 : 0;
            builder.append((mod1 + mod2 + add) % 2 == 0 ? '0' : '1');
            add = (mod1 + mod2 + add) / 2;
            p1--;
            p2--;
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        String bin1 = "1";
        String bin2 = "10";
        String retValue = addition(bin1, bin2);
        System.out.println(retValue);
    }
}
