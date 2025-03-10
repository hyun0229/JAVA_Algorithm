package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14502_연구소 {
    static int N,M,map[][],wall,sum,ans;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static boolean vis[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==1)wall++;
            }
        }
        ans = 0;
        recursive(0,0,0);
        System.out.println(ans);
    }
    private static void recursive(int x, int y, int indx) {
        if (indx ==3) {
            vis = new boolean[N][M];
            sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 2 && !vis[i][j]) {
                        dfs(i,j);
                    }
                }
            }
            ans = Math.max(ans, N*M-sum-wall-3);
            return;
        }
        for (int i = x; i <N; i++) {
            for (int j = y; j < M; j++) {
                if (map[i][j] != 0)continue;
                map[i][j] = 1;
                recursive(i, j+1, indx+1); 
                map[i][j] = 0;
            }
            y = 0;
        }
    }
    private static void dfs(int x, int y) {
        vis[x][y] = true;
        sum++;
        for (int i = 0; i < 4; i++) {
            int px = x+dx[i];
            int py = y+dy[i];
            
            if (px<0||py<0||px>=N||py>=M||vis[px][py]||map[px][py]==1)continue;
            dfs(px, py);
        }
    }

}
