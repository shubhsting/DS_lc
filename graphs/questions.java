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

    // articulation point
    class GFG {
        public static void main(String[] args) {
            Scanner scn = new Scanner(System.in);
            int tc = scn.nextInt();
            while (tc-- > 0) {
                int n = scn.nextInt();
                int m = scn.nextInt();
                graph = new ArrayList[n + 1];
                for (int i = 1; i <= n; i++)
                    graph[i] = new ArrayList<>();
                while (m-- > 0) {
                    int a = scn.nextInt();
                    int b = scn.nextInt();
                    graph[a].add(b);
                    graph[b].add(a);
                }
                vis = new boolean[n + 1];
                entry = new int[n + 1];
                min = new int[n + 1];
                par = new int[n + 1];
                AP = new boolean[n + 1];
                time = 0;
                int ans = 0;
                for (int i = 1; i <= n; i++) {
                    if (!vis[i]) {
                        nofcalls = 0;
                        csrc = i;
                        dfs(i);
                        if (nofcalls > 1)
                            AP[i] = true;
                    }
                }
                for (int i = 1; i <= n; i++) {

                    if (AP[i]) {
                        ans++;
                        // System.out.println(nofcalls);
                    }
                }
                System.out.println(ans);
            }
        }

        static boolean[] vis;
        static int[] entry;
        static int[] min;
        static int[] par;
        static boolean[] AP;
        static int nofcalls;
        static int csrc = 0;
        static ArrayList<Integer>[] graph;
        static int time;

        public static void dfs(int src) {
            vis[src] = true;
            entry[src] = time;
            min[src] = time;

            for (Integer e : graph[src]) {
                if (vis[e] && par[e] != src) {
                    min[src] = Math.min(min[src], entry[e]);
                } else {
                    time++;
                    par[e] = src;
                    if (src == csrc)
                        nofcalls++;
                    dfs(e);
                    min[src] = Math.min(min[src], min[e]);
                    if (min[src] < min[e] && src != csrc)
                        AP[src] = true;
                }
            }
        }

    }
}

    // Number of islands 2 lintcode
    /**
     * Definition for a point. class Point { int x; int y; Point() { x = 0; y = 0; }
     * Point(int a, int b) { x = a; y = b; } }
     */

    // public class Solution {
    int[] par;
    int[] size;

    public int find(int l1) {
        if (par[l1] == l1)
            return l1;
        return par[l1] = find(par[l1]);
    }

    public void union(int l1, int l2) {
        if (size[l1] >= size[l2]) {
            size[l1] += size[l2];
            par[l2] = l1;
        } else {
            size[l2] += size[l1];
            par[l1] = l2;
        }
    }

    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        int[][] arr = new int[n][m];
        if (arr.length == 1 && arr[0].length == 1)
            return new ArrayList<>();
        par = new int[n * m];
        size = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            par[i] = i;
            size[i] = 1;
        }
        List<Integer> list = new ArrayList<>();
        int islands = 0;
        for (Point p : operators) {
            if (arr[p.x][p.y] == 1) {
                list.add(islands);
                continue;

            }
            islands += 1;
            arr[p.x][p.y] = 1;
            for (int i = 0; i < dir.length; i++) {
                int nx = p.x + dir[i][0];
                int ny = p.y + dir[i][1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == 1) {
                    int p1 = find((nx * m) + ny);
                    int p2 = find(((p.x) * m) + p.y);
                    if (p1 != p2) {
                        islands -= 1;
                        union(p1, p2);
                    }
                }
            }
            list.add(islands);
        }
        return list;
    }
}