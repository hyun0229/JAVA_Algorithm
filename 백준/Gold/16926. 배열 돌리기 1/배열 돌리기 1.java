
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int map[][], N,M, R;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static boolean vis[][];
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); 
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        vis = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int nx = 0;
        int ny = 0;
        int min = Math.min(N, M);
        for (int i = 0; i < min/2+min%2; i++) {
            lotation(nx,ny,i);
            nx++;ny++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    private static void lotation(int nx, int ny,int lv) {
        int i = nx;
        int j = ny;
        int num = 0;
        Queue<Integer> q = new ArrayDeque<>();
        while(true) {
            int px = i + dx[num];
            int py = j + dy[num];
            if (px<0||py<0||px>=N||py>=M||vis[px][py]) {
                num+=1;
                num%=4;
                px = i + dx[num];
                py = j + dy[num];
            }
            q.add(map[px][py]);
            i = px;
            j = py;
            if (i== nx && j == ny) {
                break;
            }
        }
        for (int k = 1; k < R; k++) {
            int tmp = q.poll();
            q.add(tmp);
        }
        map[nx][ny] = q.poll();
        num = 0;
        i = nx;
        j = ny;
        while(!q.isEmpty()) {
            int px = i + dx[num];
            int py = j + dy[num];
            if (px<0||py<0||px>=N||py>=M||vis[px][py]) {
                num+=1;
                num%=4;
                px = i + dx[num];
                py = j + dy[num];
            }
            map[px][py] = q.poll();
            vis[px][py] = true;
            i = px;
            j = py;
        }
    }
}
