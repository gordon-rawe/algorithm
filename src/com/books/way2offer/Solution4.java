package com.books.way2offer;

/**
 * Created by gordon on 1/11/18.
 */
public class Solution4 {

    /*将字符串中的空格替换为20%,模拟url加密,采用从后往前的办法*/

    public static void main(String[] args) {
        System.out.println(urlEncode("hello world"));
    }

    /*我们用一个数组来模拟这题目*/
    private static String urlEncode(String urlString) {
        if (urlString == null || urlString.length() == 0) {
            return urlString;
        }
        int blankCount = 0;
        for (int i = 0; i < urlString.length(); i++) {
            if (urlString.charAt(i) == ' ') {
                blankCount++;
            }
        }
        char[] retValue = new char[urlString.length() + blankCount * 2];
        System.arraycopy(urlString.toCharArray(), 0, retValue, 0, urlString.length());
        int post = retValue.length - 1, pre = urlString.length() - 1;
        while (pre >= 0) {
            if (retValue[pre] == ' ') {
                retValue[post--] = '0';
                retValue[post--] = '2';
                retValue[post--] = '%';
                pre--;
            } else {
                retValue[post--] = retValue[pre--];
            }
        }
        return String.valueOf(retValue);
    }
}
