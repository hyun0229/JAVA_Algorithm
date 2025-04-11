import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//1545
public class Solution {
    static final boolean chk[][];
    static boolean vis[][];
    static int map[][], N, M, ans;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, 1, 0, -1 };
    static {
        chk = new boolean[8][4];
        Arrays.fill(chk[1], true);
        chk[2][0] = true;
        chk[2][2] = true;
        chk[3][1] = true;
        chk[3][3] = true;
        chk[4][0] = true;
        chk[4][1] = true;
        chk[5][1] = true;
        chk[5][2] = true;
        chk[6][2] = true;
        chk[6][3] = true;
        chk[7][0] = true;
        chk[7][3] = true;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans = 1;
            vis = new boolean[N][M];
            bfs(R, C, L);
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(test_case).append(' ').append(ans);
            System.out.println(sb.toString());
        }
    }

    private static void bfs(int r, int c, int l) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r, c, 1));
        vis[r][c] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int len = node.len;
            if (len == l)break;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<0||ny<0||nx>=N||ny>=M||map[nx][ny] == 0 || vis[nx][ny])continue;
                int now = map[x][y];
                int go = map[nx][ny];
                if (!chk[now][i]||!chk[go][(i+2)%4])continue;
                q.add(new Node(nx, ny, len+1));
                ans++;
                vis[nx][ny] = true;
            }
        }
    }
    static class Node {
        int x, y, len;
        public Node(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }
}
