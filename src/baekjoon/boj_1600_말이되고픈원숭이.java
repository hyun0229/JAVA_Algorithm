package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1600_말이되고픈원숭이 {
    public static Point[] monkey_move = {
        new Point(0, 1,0),new Point(0, -1,0),new Point(1, 0,0),new Point(-1, 0,0)
    };
    public static Point[] Horse_move = {
        new Point(-2, 1,1),new Point(-1, 2,1),new Point(1, 2,1),new Point(2, 1,1),
        new Point(2, -1,1),new Point(1, -2,1),new Point(-1, -2,1),new Point(-2, -1,1)
    };
    static public class Point {
        public int x,y,k;
        public Point(int x, int y, int k){
            this.x = x; this.y = y;this.k = k;
        }
        public static Point addPoint(Point p1, Point p2){
            return new Point(p1.x+p2.x, p1.y + p2.y,p1.k+p2.k);
        }
    }

    public static int[][] map;
    public static int[][][] vis;
    public static int W,H,K;
    public static void main(String[] args) throws IOException {
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        if(H == 1 && W == 1){
            System.out.println(0);
            return;
        }
        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        vis = new int[H][W][K+1];
        int ans = bfs(0,0);
        System.out.println(ans);
    }
    public static int bfs(int x,int y){
        vis[x][y][0] +=1;
        int num=0;
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(x, y,0));
        boolean flag = false;
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (Point p : monkey_move) {
                Point tmp = Point.addPoint(p,now);
                if (tmp.x<H&&tmp.y<W&&tmp.x>=0 && tmp.y>=0 &&map[tmp.x][tmp.y] !=1) {
                    boolean chk = true;
                    for (int i = 0; i <= tmp.k; i++) {
                        if(vis[tmp.x][tmp.y][i]!=0){
                            chk = false;
                            break;
                        }
                    }
                    if (chk) {
                        q.offer(tmp);
                        vis[tmp.x][tmp.y][tmp.k] =vis[now.x][now.y][now.k]+1;
                        if (tmp.x == H-1 && tmp.y == W-1) {
                            flag = true;
                            num = tmp.k;
                            break;
                        }
                    }
                }
            }

            // TestTool.test_map(vis);
            // System.out.println("_____________________");
            if (!flag && now.k<K) {
                for (Point p : Horse_move) {
                    Point tmp = Point.addPoint(p,now);
                    if (tmp.x<H&&tmp.y<W&&tmp.x>=0 && tmp.y>=0 &&map[tmp.x][tmp.y] !=1) {
                        boolean chk = true;
                        for (int i = 0; i <= tmp.k; i++) {
                            if(vis[tmp.x][tmp.y][i]!=0){
                                chk = false;
                                break;
                            }
                        }
                        if (chk) {
                            q.offer(tmp);
                            vis[tmp.x][tmp.y][tmp.k] =vis[now.x][now.y][now.k]+1;
                            if (tmp.x == H-1 && tmp.y == W-1) {
                                flag = true;
                                num = tmp.k;
                                break;
                            }
                        }
                    }
                }
            }

            if (flag) {
                return vis[H-1][W-1][num]-1;
            }
            
        }
        return -1;
    }


}
