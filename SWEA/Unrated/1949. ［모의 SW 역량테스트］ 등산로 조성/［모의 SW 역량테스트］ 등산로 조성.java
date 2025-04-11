/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
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