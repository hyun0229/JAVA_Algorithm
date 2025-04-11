
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static int map[][], N, ans[];
    static ArrayList<int[]> arr;
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            map = new int[N][N];
            arr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = sc.nextInt();
                    map[i][j] = num;
                    if (i== 0||j==0||i==N-1||j==N-1||num==0)continue;
                    arr.add(new int[]{i,j});
                }
            }
            ans = new int[2]; //0이 코어수, 1이 전선 길이
            recuresive(0,arr.size(),0,0);
            System.out.println(String.format("#%d %d",test_case,ans[1]));

		}
	}
    private static void recuresive(int lv, int size,int core, int line) {
        if (lv== size) {
            if (ans[0]<core) {
                ans[0] = core;
                ans[1] = line;
            }
            else if (ans[0] == core) {
                ans[1] = Math.min(ans[1], line);
            }
            return;
        }

        int nx = arr.get(lv)[0];
        int ny = arr.get(lv)[1];
        for (int i = 0; i <4; i++) {
            boolean flag = true;
            int x = nx;
            int y = ny;
            int num = 0;
            while (true) {
                x+=dx[i];
                y+=dy[i];
                if (x<0||y<0||x==N||y==N) {
                    break;
                }
                if (map[x][y]==1) {
                    flag = false;
                    break;
                }
                num++;
            }
            if (flag) {
                drawline(nx,ny,i,num,1);
                recuresive(lv+1, size, core+1, line+num);
                drawline(nx,ny,i,num,0);
            }

        }
        recuresive(lv+1, size, core, line);
    }
    private static void drawline(int nx, int ny, int i,int line, int draw) {
        for (int j = 0; j < line; j++) {
            nx += dx[i];
            ny+= dy[i];
            map[nx][ny] = draw;
        }
    }
    static void drawmap(int[][] ddd){
        for (int[] is : ddd) {
            System.out.println(Arrays.toString(is));
        }

    }
}
