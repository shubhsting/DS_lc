/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    static class job {
        int jobid = 0;
        int ded = 0;
        int cost = 0;

        job(int a, int b, int c) {
            jobid = a;
            ded = b;
            cost = c;
        }
    }

    static int[] par;

    public static int findpar(int l1) {
        if (par[l1] == l1)
            return l1;
        return par[l1] = findpar(par[l1]);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        while (tc-- > 0) {
            PriorityQueue<job> pq = new PriorityQueue<>((job a, job b) -> {
                return b.cost - a.cost;
            });

            int n = scn.nextInt();
            par = new int[1001];
            for (int i = 0; i <= 1000; i++)
                par[i] = i;
            while (n-- > 0) {
                int a = scn.nextInt();
                int b = scn.nextInt();
                int c = scn.nextInt();
                pq.add(new job(a, b, c));
            }
            int ans = 0;
            int count = 0;
            while (pq.size() != 0) {
                job temp = pq.remove();
                int p = findpar(temp.ded);

                if (p != 0) {
                    ans += temp.cost;
                    count++;
                    par[p] = findpar(p - 1);
                }
            }
            System.out.println(count + " " + ans);
        }
    }
}