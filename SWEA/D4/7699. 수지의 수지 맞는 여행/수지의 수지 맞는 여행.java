
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static char[][] map;
    public static int R,C,ans;
    public static boolean[] vis; //방문배열 
    public static class Point { //x,y 좌표 관리
        public int x,y;
        public Point(int x,int y){
            this.x = x;this.y = y;
        }
        public static Point addPoint(Point p1, Point p2){
            return new Point(p1.x+p2.x, p1.y+p2.y);
        }
    }

    public static Point[] movePoints = {new Point(0, 1),new Point(1, 0),new Point(0, -1),new Point(-1, 0)}; //사방탐색
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            R = sc.nextInt();
            C = sc.nextInt();
            map = new char[R][C];
            ans = 0;
            vis = new boolean[26];
            for (int i = 0; i < R; i++) {
                char[] row = sc.next().toCharArray();
                for (int j = 0; j < C; j++) {
                    map[i][j] = row[j];
                    
                }
            }
            //TestTool.test_map(map);
            dfs(new Point(0, 0),1);
            System.out.println(String.format("#%d %d",test_case,ans));
		}
	}
    
    public static void dfs(Point now,int lv) {
        vis[map[now.x][now.y]-'A'] = true;
        ans = Math.max(ans, lv);
        for (Point move : movePoints) {
            Point p = Point.addPoint(move, now);
            if (p.x>=0 && p.x<R && p.y>=0 && p.y<C && (! vis[map[p.x][p.y]-'A'])) {
                dfs(p,lv+1);
            }
        }
        vis[map[now.x][now.y]-'A'] = false;
        
    }
}
