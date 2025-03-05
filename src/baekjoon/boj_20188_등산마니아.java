package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_20188_등산마니아 {

    static int N;
    static ArrayList<Integer>[] arr_ls;
    static long ans;
    static boolean vis[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ans = 0;
        vis = new boolean[N];
        arr_ls = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr_ls[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            arr_ls[x].add(y);
            arr_ls[y].add(x);
        }
        vis[0] = true;
        dfs(0);
        System.out.println(ans);
    }
    private static int dfs(int start) {
        long v = 0;
        for (int i : arr_ls[start]) {
            if (vis[i]) continue;
            vis[i] = true;
            v += dfs(i);
        }
        if (start!=0)ans += (N - v - 1) * (v + 1) + ((v + 1) * v) / 2;
        return (int) (v+1);
    }
}
