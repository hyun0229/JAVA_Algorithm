package JAVA_Algorithm.src;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_7744_치즈도둑 {

    public static boolean[][] vis;
    public static int[][] Map;
    public static int N;
    

    public static void test_map(int[][] arr){
        for (int[] x: arr) {
            System.out.println(Arrays.toString(x));
        }
    }
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            Map = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    Map[i][j] = sc.nextInt();
                }
            }
            //test_map(map); //배열 확인용
            int ans = 0;
            for (int i = 1; i <= 100; i++) { //날짜 1~100일까지
                vis = new boolean[N][N]; // 방문배열 초기화
                int temp=0;
                for (int x = 0; x <N; x++) {
                    for (int y  = 0; y < N; y++) {
                        if (Map[x][y]>i && !vis[x][y]) {
                            temp+=1;
                            bfs(x,y,i);
                        }
                    }
                }
                ans = Math.max(ans, temp);
                if (ans >=50) {
                    break;
                }
            }
            System.out.println("#%d %d".formatted(test_case,ans));
		}


	}
    public static void  bfs(int x,int y,int day){
        Point[] move = {new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)}; //사방탐색
        Queue<Point> q = new ArrayDeque<Point>();
        q.offer(new Point(x,y));
        vis[x][y] = true;
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int i = 0; i < 4; i++) {
                Point p = Point.addPoints(now, move[i]);
                if (p.x>=0&&p.x<N && p.y>=0&&p.y<N && !vis[p.x][p.y] && Map[p.x][p.y]>day) { //사방 탐색 조건 + 방문배열 확인
                    q.offer(p);
                    vis[p.x][p.y] =true;
                }
            }

        }

        
    }

    static public class Point {
        public int x;
        public int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public static Point addPoints(Point p1, Point p2) {
            return new Point(p1.x + p2.x, p1.y + p2.y);
        }
    
        
    }

}
