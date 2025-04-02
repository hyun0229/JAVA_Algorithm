package SWEA;

import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_1949_등산로조성
{
    static int map[][],N,K,ans;
    static boolean vis[][];
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static ArrayList<int[]> arr;
    public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            K = sc.nextInt();
            map = new int[N][N];
            vis = new boolean[N][N];
            arr = new ArrayList<>();
            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = sc.nextInt();
                    map[i][j] = num;
                    if(num>max){
                        max = num;
                        arr.clear();
                        arr.add(new int[]{i,j});
                    }
                    else if (num == max) {
                        arr.add(new int[]{i,j});
                    }
                }
            }
            
            ans = 0;

            for (int[] location : arr) {
                recursive(location[0],location[1],1,false);
            }
            System.out.println(String.format("#%d %d",test_case,ans));

		}
	}
    private static void recursive(int x, int y, int dept,boolean used) {
        vis[x][y] =true;
        for (int i = 0; i < 4; i++) {
            int px = x + dx[i];
            int py = y + dy[i];
            if(px<0||py<0||px>=N||py>=N)continue;
            if (map[x][y]>map[px][py]&&!vis[px][py]) {
                recursive(px, py, dept+1,used);
            }
            else{
                if (!used && map[x][y]>map[px][py]-K && !vis[px][py]) {
                    int temp = map[px][py];
                    map[px][py] = map[x][y]-1;
                    recursive(px, py, dept+1,true);
                    map[px][py] = temp;
                }
            }
        }
        vis[x][y] = false;
        ans = Math.max(ans, dept);

    }
}