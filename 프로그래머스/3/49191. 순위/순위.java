import java.util.*;

class Solution {
    static final int INF = 987654321;

    public int solution(int n, int[][] results) {
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
        
        int[] in = new int[n + 1], layer = new int[n + 1], fp = new int[n + 1], ln = new int[n + 1];
        
        for (int[] edge : results) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            in[v]++;
        }

        int[] q = new int[n + 1];
        int front = 0, back = 0;
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) q[back++] = i;
        }

        while (front < back) {
            int cur = q[front++];
            for (int nxt : g[cur]) {
                layer[nxt] = Math.max(layer[cur] + 1, layer[nxt]);
                if (--in[nxt] == 0) q[back++] = nxt;
            }
        }

        for (int i = 1; i <= n; i++) {
            fp[i] = INF;
            if (!g[i].isEmpty()) {
                for (int nxt : g[i]) {
                    fp[i] = Math.min(fp[i], layer[nxt]);
                }
            }
        }

        int maxLayer = 0;
        for (int i = 1; i <= n; i++) {
            ln[layer[i]]++;
            maxLayer = Math.max(maxLayer, layer[i]);
        }

        int[] mx = new int[maxLayer + 1];
        Arrays.fill(mx, -1);
        for (int i = 1; i <= n; i++) {
            int L = layer[i];
            mx[L] = Math.max(mx[L], fp[i]);
        }

        int[] cum = new int[maxLayer + 1];
        cum[0] = -1;
        for (int L = 1; L <= maxLayer; L++) {
            cum[L] = Math.max(cum[L - 1], mx[L - 1]);
        }
        int cnt = 0;
        for (int p = 1; p <= n; p++) {
            int L = layer[p];
            boolean flag = ln[L] == 1 ? (cum[L] > L) : true;
            if (!flag) cnt++;
        }
        return cnt;

    }
}
