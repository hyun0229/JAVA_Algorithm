package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1520_내리막길 {
    static int memo[][], map[][];
    static int N,M;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static boolean vis[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        memo = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                memo[i][j] = -1;
            }
        }
        vis = new boolean[N][M];
        int ans = dfs(0,0);
        System.out.println(ans);
    }
    private static int dfs(int x, int y) {
        if (x==N-1 && y==M-1) {
            return 1;
        }
        if (memo[x][y] == -1)memo[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||ny<0||nx>=N||ny>=M||map[x][y]<=map[nx][ny])continue;
            if (memo[nx][ny]!=-1)memo[x][y]+=memo[nx][ny];
            else {
                vis[nx][ny] = true;
                memo[x][y]+= dfs(nx, ny);
                vis[nx][ny] = false;                
            }
        }
        return memo[x][y];
    }
}
