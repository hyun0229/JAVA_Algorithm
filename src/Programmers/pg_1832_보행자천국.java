package Programmers;

public class pg_1832_보행자천국 {
    class Solution {
        int MOD = 20170805;
        int memo[][][];
        int N,M;
        int dx[] = {0,1};
        int dy[] = {1,0};
        public int solution(int m, int n, int[][] cityMap) {
            N = m;
            M = n;
            memo = new int[N][M][3];
            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    for(int k = 0; k<3; k++){
                        memo[i][j][k] = -1;
                    }
                }
            }
            memo[N-1][M-1][2] = 1;
            return dfs(0, 0, 2, cityMap);
        }
        private int dfs(int x, int y,int idx, int[][] map) {
            if(memo[x][y][idx]!=-1)return memo[x][y][idx];
            int ans = 0;
            for (int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<0||ny<0||nx>=N||ny>=M||map[nx][ny]==1)continue;
                if(map[x][y]==2&&idx!=i)continue;
                else {
                    int num = 2;
                    if(map[nx][ny] == 2)num = i;
                    ans += dfs(nx, ny,num,map);         
                }
            }
            return memo[x][y][idx] = ans%MOD;
        }
        
    }
}
