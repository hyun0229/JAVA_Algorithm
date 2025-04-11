
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][][] vis;
    static int N,M;
    static char map[][];

    static class Node {
        int x,y,keys;
        public Node(int x,int y,int keys){
            this.x= x; this.y = y; this.keys = keys;
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        vis = new int[N][M][64];
        int x=0,y=0;
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (s.charAt(j) =='0'){x =i;y=j;}
            }
        }
        System.out.println(bfs(x,y));
        
    }
    static int bfs(int i, int j){
        Queue<Node> q = new ArrayDeque<>();
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        q.offer(new Node(i, j,0));
        vis[i][j][0] = 1;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x, y = cur.y, keys = cur.keys;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                int nkeys = keys;
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == '#') continue;
                if (map[nx][ny]== '1') return vis[x][y][keys];
                char cell = map[nx][ny];
                if (cell >= 'a' && cell <= 'f') {
                    nkeys = keys | (1 << (cell - 'a'));
                }
                
                if (cell >= 'A' && cell <= 'F') {
                    if ((keys & (1 << (cell - 'A'))) == 0) continue;
                }
                
                if (vis[nx][ny][nkeys] == 0) {
                    vis[nx][ny][nkeys] = vis[x][y][keys] + 1;
                    q.offer(new Node(nx, ny, nkeys));
                }
            }
        }
        return -1;
    }
}
