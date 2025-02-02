package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//import SWEA.TestTool;

public class bj_2206_벅부수고이동 {
    public static int map[][];
    public static int N,M;
    public static int[][][] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        vis = new int[N][M][2]; //0번인덱스는 방문했나 1번 인덱스는 부쉈나
        for (int i = 0; i < N ; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0'; //문자를 숫자로 변경
            }
        }

        bfs(0,0);
        N-=1;
        M-=1;
        if (vis[N][M][0] == 0 && vis[N][M][1] ==0)  {
            System.out.println(-1);
        }
        else if (vis[N][M][0] == 0) {
            System.out.println(vis[N][M][1]);
        }
        else{
            System.out.println(Math.min(vis[N][M][0], vis[N][M][1]));
        }
        //TestTool.test_map(vis);

    }
    public static void bfs(int i, int j) {
        Point[] movePoints = {new Point(-1, 0),new Point(0, 1),new Point(1, 0),new Point(0, -1)}; //사방탐색
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(i, j));
        vis[i][j][0] =1;
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (Point move : movePoints) {
                Point p = Point.addPoint(move, now);
                if (p.x>=0 && p.x<N && p.y>=0 && p.y<M) { //사방탐색 조건
                    if (vis[p.x][p.y][0] == 0 && map[p.x][p.y]==0 && vis[now.x][now.y][0] !=0) {
                        vis[p.x][p.y][0] +=vis[now.x][now.y][0]+1;
                        q.offer(p);
                    }
                    if (vis[now.x][now.y][0] != 0 && map[p.x][p.y]==1 && vis[p.x][p.y][1] == 0) { //처음 벽뚫
                        vis[p.x][p.y][1] += vis[now.x][now.y][0]+1;
                        q.offer(p);
                    }
                    if (vis[p.x][p.y][1] == 0 && vis[now.x][now.y][1] != 0 && map[p.x][p.y]== 0 && vis[p.x][p.y][0] == 0) {
                        vis[p.x][p.y][1] += vis[now.x][now.y][1]+1;
                        q.offer(p);
                    }
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
}
