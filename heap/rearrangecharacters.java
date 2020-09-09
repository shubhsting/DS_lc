/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        while (tc-- > 0) {
            String str = scn.next();

        }
    }

    static class random {
        char c = 'm';
        int count = 0;

        random(char c, int co) {
            this.c = c;
            this.count = co;
        }
    }

    public static boolean create(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<random> pq = new PriorityQueue<>((random a, random b) -> {
            return b.count - a.count;
        });
        String myans = "";
        for (char key : map.keySet())
            pq.add(new random(key, map.get(key)));
        random temp = null;
        while (myans.length() < str.length()) {
            random tem = pq.remove();
        }
        return myans.length() == str.length();
    }
}