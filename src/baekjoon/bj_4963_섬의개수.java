package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class bj_4963_섬의개수 {
    public static int w,h;
    public static int[][] map;
    public static boolean vis[][];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }
            map = new int[h][w];
            vis = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int ans = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!vis[i][j] && map[i][j]==1){
                        bfs(i,j);
                        ans+=1;
                    } 
                }
            }

            System.out.println(ans);
        }
    }
    public static void bfs(int i,int j){
        Point movePoint[] = {
            new Point(0,1), new Point(1, 0),new Point(0, -1),new Point(-1, 0),
            new Point(1, 1),new Point(-1, 1),new Point(-1, -1),new Point(1, -1)
        };
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(i, j));
        vis[i][j] = true;
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (Point move : movePoint) {
                Point p = Point.addPoint(move, now);
                if (p.x>=0 && p.x<h && p.y >=0 && p.y < w && !vis[p.x][p.y] && map[p.x][p.y]==1) {
                    q.offer(p);
                    vis[p.x][p.y] = true;
                }
            }
        }
        
    }
    public static class Point{
        public int x,y;
        public Point(int x,int y){
            this.x =x; this.y = y;
        }
        public static Point addPoint(Point p1, Point p2){
            return new Point(p1.x+p2.x, p1.y+p2.y);
        }
    }
    // public static Square square; //아 정사각형 섬 찾으라는 줄ㅋㅋ 
    // public static class Square {
    //     public int cnt;
    //     public int x1,x2,x3,x4; //1부터 반시계 방향
    //     public int y1,y2,y3,y4;
    //     public Square(int x,int y){
    //         x1 = x; x2 = x; x3 = x; x4 = x; //x좌표 초기화
    //         y1 = y; y2 = y; y3 = y; y4 = y; //y좌표 초기화
    //         cnt = 1;
    //     }
    //     public void addsquare(int x,int y){
    //         if(x <= x1 && y <= y1) x1 = x; y1 = y;
    //         if(x >= x2 && y <= y2) x2 = x; y2 = y;
    //         if(x >= x3 && y >= y3) x3 = x; y3 = y;
    //         if(x >= x4 && y >= y4) x4 = x; y4 = y;
    //         cnt++;
    //     }
    //     public boolean isSquare(){
    //         if(x1 == x4 && x2 == x3 && y1 == y3 && y2 == y4){
    //             if ((x2 - x1 +1)*(x2 - x1 +1) == cnt) {
    //                 return true;
    //             }
    //         }
    //         return false;
    //     }
    // }
}
