import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
    static boolean[][] vis;
    static int[][] map;
    static int ans[], N;
    static int[][] points;
    public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            map = new int[N][N];
            points = new int[N*N+1][2];
            vis = new boolean[N][N];
            ans = new int[2];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num= sc.nextInt();
                    map[i][j] = num;
                    points[num][0] = i;
                    points[num][1] = j;
                }
            }
            for (int i = 1; i < N*N+1; i++) {
                int x = points[i][0];
                int y = points[i][1];
                if (vis[x][y])continue;
                int tmp = bfs(x,y);
                if (tmp >ans[1]) {
                    ans[0] = i;
                    ans[1] = tmp;
                }
            }
            System.out.println(String.format("#%d %d %d", test_case,ans[0],ans[1]));
		}
	}
    private static int bfs(int x, int y) {
        int dx[] = {1,0,-1,0};
        int dy[] = {0,-1,0,1};
        Queue<int[]> q = new ArrayDeque<>();
        vis[x][y] = true;
        q.add(new int[]{x,y});
        int cnt = 1;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nx = now[0];
            int ny = now[1];
            for (int i = 0; i < 4; i++) {
                int px = nx + dx[i];
                int py = ny + dy[i];
                if (px<0||py<0||px>=N||py>=N||vis[px][py])continue;
                if (map[nx][ny]+1==map[px][py]) {
                    q.add(new int[]{px,py});
                    vis[px][py] = true;
                    cnt++;
                }
            }
        }
        return cnt;

    }
    
}
