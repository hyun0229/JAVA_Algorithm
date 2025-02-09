package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7576_토마토 {

    public static int[][] map;
    public static int N,M;

    static public class Point {
        public int x,y;
        public Point(int x, int y){
            this.x = x; this.y = y;
        }
        public static Point addPoint(Point p1, Point p2){
            return new Point(p1.x+p2.x, p1.y + p2.y);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1){
                    bfs(i,j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = map[i][j];
                if (num ==0) {
                    ans = -1;
                    break;
                }
                ans = Math.max(ans, num);
            }
            if(ans ==-1) break;
        }
        System.out.println(ans==-1? -1:ans-1);
        //test_map(map); 배열 확인


    }
    public static void bfs(int i, int j) {
        Point[] movePoints = {new Point(-1, 0),new Point(0, 1),new Point(1, 0),new Point(0, -1)}; //사방탐색
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(i, j));
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (Point move : movePoints) {
                Point p = Point.addPoint(move, now);
                if (p.x>=0 && p.x<N && p.y>=0 && p.y<M && map[p.x][p.y]!=-1) { //사방탐색 조건 + -1 인지 확인
                    if (map[p.x][p.y]==0 || map[now.x][now.y]<map[p.x][p.y]-1) {
                        q.offer(p);
                        map[p.x][p.y] = map[now.x][now.y]+1; //움직일 공간에 현재 위치의 날짜+1
                    }
                    else if (map[now.x][now.y]>map[p.x][p.y]+1) {
                        q.offer(now);
                        map[now.x][now.y] = map[p.x][p.y]+1;
                    }
                }
            }
        }
    }
    // public static void test_map(int[][] arr){ //배열 확인용
    //     for (int[] x: arr) {
    //         System.out.println(Arrays.toString(x));
    //     }
    // }

}
