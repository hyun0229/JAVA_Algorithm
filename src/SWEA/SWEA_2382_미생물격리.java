package SWEA;

import java.util.ArrayList;
import java.util.Scanner;
public class SWEA_2382_미생물격리 {
	static int N,M,K;
	static ArrayList<Integer>[][] map;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};

		int[] change = {1,0,3,2};
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			map = new ArrayList[N][N];
			Micro micro[] = new Micro[K];
			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int m = sc.nextInt();
				int dt = sc.nextInt()-1;
				micro[i] = new Micro(x, y, m, dt);
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = new ArrayList<>();
				}
			}
			for (int time = 0; time < M; time++) {
				for (int i = 0; i < micro.length; i++) {
					if (micro[i].m == 0)continue;
					micro[i].x +=dx[micro[i].dt];
					micro[i].y +=dy[micro[i].dt];
					map[micro[i].x][micro[i].y].add(i);
					if (micro[i].x == 0||micro[i].y == 0|| micro[i].x==N-1||micro[i].y==N-1) {
						micro[i].dt = change[micro[i].dt];
						micro[i].m = (int)(micro[i].m/2);
					}
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j].size()>=2) {
							int tmp = map[i][j].get(0);
							int sum = micro[tmp].m;
							int max = micro[tmp].m;
							for (int k = 1; k < map[i][j].size(); k++) {
								sum += micro[map[i][j].get(k)].m;
								if (max<micro[map[i][j].get(k)].m) {
									max = micro[map[i][j].get(k)].m;
									micro[tmp].m = 0;
									tmp = map[i][j].get(k);
								}
								else{
									micro[map[i][j].get(k)].m = 0;
								}
							}
							micro[tmp].m = sum; 
						}
						map[i][j].clear();
					}
				}
			}
			int ans = 0;
			for (Micro micro2 : micro) {
				//System.out.print(micro2.m+" ");
				ans+= micro2.m;
			}
			System.out.println(String.format("#%d %d",test_case ,ans));
		}
	}
	static class Micro {
		int x,y,m,dt;
		public Micro(int x, int y, int m, int dt){
			this.x = x;
			this.y = y;
			this.m = m;
			this.dt = dt;
		}
		
	}
}