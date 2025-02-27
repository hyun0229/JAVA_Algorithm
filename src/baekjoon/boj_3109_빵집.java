package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3109_빵집 {
    static int  ans, R,C;
    static char map[][];
    static boolean flag;
    static int dx[] = {-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        ans = 0;
        for (int i = 0; i < R; i++) {
            flag = false;
            dfs(i,0);
        }
        
        System.out.println(ans);
    }
    private static void dfs(int x, int y) {
        if (y==C-1){
            flag = true;
            ans++;
            return;
        }
        for (int i = 0; i < 3; i++) {
            int px = x+dx[i];
            int py = y+1;
            if (px<0||px>=R)continue;
            if (map[px][py] == '.'){
                map[px][py] = 'z';
                dfs(px, py);
                if(flag)return;
            }
        }
    }
}
