
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static int[] company = new int[2];
	public static int[] home = new int[2];
	public static int[][] map;
	public static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			ans = Integer.MAX_VALUE;
			int N = sc.nextInt();
			
			company[0] = sc.nextInt();
			company[1] = sc.nextInt();
			home[0] = sc.nextInt();
			home[1] = sc.nextInt();
			
			
			map = new int[N][2];
			for(int i = 0; i<N; i++) {
				map[i][0] = sc.nextInt();
				map[i][1] = sc.nextInt();
			}
			recursive(new int[N],0,new boolean[N]);
			System.out.println("#"+test_case+" "+ans);

		}
		

	}
	private static void recursive(int[] sel, int k, boolean[] v) {
		//basis part
		
		if (k == sel.length) {
			
			int tmp = 0;
			for(int i = 0 ; i<sel.length-1; i ++) {
				tmp += distance(map[sel[i]],map[sel[i+1]]);
			}
			tmp += distance(company,map[sel[0]]) + distance(home, map[sel[sel.length-1]]);
			ans = Math.min(ans, tmp);
			return;
		}
		
		for(int i =0 ; i<sel.length;i++){
			if(!v[i]){
				v[i] = true;
				sel[k] = i;
				recursive(sel, k+1,v);
				v[i] = false;
			}
		}
		
	}
	private static int distance(int[] p1, int[] p2) {
		return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]) ;
	}
}