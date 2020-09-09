/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class simplefraction {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        while (tc-- > 0) {
            int num1 = scn.nextInt();
            int num2 = scn.nextInt();
            System.out.println(calc(num1, num2));
        }
    }

    public static String calc(int num1, int num2) {
        StringBuilder sb = new StringBuilder();
        int quo = num1 / num2;
        int rem = num1 % num2;
        String ans = quo + "";
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(rem, 0);
        int i = 1;
        while (rem != 0) {
            rem = rem * 10;
            int qu = rem / num2;
            int re = rem % num2;
            if (map.containsKey(re)) {
                sb.append(qu);
                sb.append(")");
                sb.insert(map.get(re), "(");
                break;
            } else {
                map.put(re, i);
                i++;
                sb.append(qu);
                rem = re;
            }
        }
        String st = sb.toString();
        if (st.length() == 0)
            return ans;
        return ans + "." + st;
    }
}