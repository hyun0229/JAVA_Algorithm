package SWEA;

import java.util.Scanner;

public class SWEA_1861 {
    static boolean[][] vis;
    static int[][] map,ans;
    static int[][] points;
    public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            map = new int[N][N];
            points = new int[N+1][2];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num= sc.nextInt();
                    map[i][j] = num;
                    points[num][0] = i;
                    points[num][1] = j;
                }
            }
            
		}
	}
}
