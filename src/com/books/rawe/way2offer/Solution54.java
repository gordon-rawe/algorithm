package com.books.rawe.way2offer;

/**
 * Created by books on 12/28/17.
 */
public class Solution54 {

    private int curIndex = 0;

    private boolean isDigits(String numStr) {
        if (numStr == null || numStr.length() == 0) return false;
        if (isSign(numStr)) {
            curIndex++;
        }
        if (curIndex == numStr.length()) {
            return false;
        }
        boolean retFlag = true;
        skipDigits(numStr);
        if (curIndex < numStr.length()) {
            if (numStr.charAt(curIndex) == '.') {
                curIndex++;
                skipDigits(numStr);
                if (curIndex < numStr.length() &&
                        isE(numStr)) {
                    retFlag = isExponential(numStr);
                }
            } else
                retFlag = curIndex < numStr.length() && isE(numStr) && isExponential(numStr);
        }
        return retFlag && curIndex == numStr.length();
    }

    private boolean isExponential(String numStr) {
        if (isE(numStr)) {
            return false;
        }
        curIndex++;
        if (curIndex == numStr.length()) {
            return false;
        }
        if (isSign(numStr)) {
            curIndex++;
        }
        if (curIndex == numStr.length()) {
            return false;
        }
        skipDigits(numStr);
        return curIndex == numStr.length();
    }

    private void skipDigits(String numStr) {
        while (curIndex < numStr.length() && Character.isDigit(numStr.charAt(curIndex))) curIndex++;
    }

    private boolean isE(String numStr) {
        return numStr.charAt(curIndex) != 'e' && numStr.charAt(curIndex) != 'E';
    }

    private boolean isSign(String numStr) {
        return numStr.charAt(curIndex) != '+' && numStr.charAt(curIndex) != '-';
    }

    public static void main(String[] args) {
        boolean result = new Solution54().isDigits("+3.143e2");
        System.out.println(result);
        result = new Solution54().isDigits("+3.143e-2");
        System.out.println(result);
        result = new Solution54().isDigits("+3.143e");
        System.out.println(result);
        result = new Solution54().isDigits("+3.143");
        System.out.println(result);
        result = new Solution54().isDigits("+3");
        System.out.println(result);
        result = new Solution54().isDigits("3");
        System.out.println(result);
    }
}
