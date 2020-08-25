import java.util.*;

public class questions {
    public static class edge {
        int v = 0;
        int w = 0;

        edge(int u, int w) {
            this.v = u;
            this.w = w;
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();
        vis = new boolean[n + 1];
        while (m-- > 0) {
            int a = scn.nextInt();
            int b = scn.nextInt();
            graph[a].add(new edge(b, 0));
            graph[b].add(new edge(a, 1));
        }
        System.out.println(primsAlgo(1, n));
    }

    static ArrayList<edge>[] graph;
    static boolean[] vis;

    static class pair {
        int src = 0;
        int par = 0;
        int wt = 0;
        int wsf = 0;

        pair(int src, int par, int wt, int wsf) {
            this.par = par;
            this.src = src;
            this.wt = wt;
            this.wsf = wsf;
        }
    }

    public static int primsAlgo(int src, int dest) {

        PriorityQueue<pair> pq = new PriorityQueue<>((pair a, pair b) -> {
            return a.wsf - b.wsf;
        });
        pq.add(new pair(src, -1, 0, 0));
        int ans = 0;
        while (pq.size() != 0) {
            int siz = pq.size();
            while (siz-- > 0) {
                pair temp = pq.remove();
                if (vis[temp.src])
                    continue;
                if (temp.src == dest)
                    return temp.wsf;
                vis[temp.src] = true;
                for (edge e : graph[temp.src]) {
                    if (!vis[e.v])
                        pq.add(new pair(e.v, temp.src, e.w, temp.wsf + e.w));
                }
            }
        }
        return -1;
    }    
}