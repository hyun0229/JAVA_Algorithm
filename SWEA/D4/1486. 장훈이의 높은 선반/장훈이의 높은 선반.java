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
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.io.FileInputStream;

class Solution {
    static int ans = 0;
    static int N;
    public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            ans = Integer.MAX_VALUE;
            N = sc.nextInt();
            int B = sc.nextInt();
            Integer[] arr = new Integer[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr,Collections.reverseOrder()); 
            recursive(arr,0,0,B);
            System.out.println(String.format("#%d %d", test_case,ans));

		}
	}
    private static void recursive(Integer[] arr, int index, int sum,int B) {
        if (sum>=B) {
            int num = sum-B;
            ans = Math.min(ans, num);
            return;
        }
        if (index == N) {
            return;
        }
        recursive(arr, index+1, sum+arr[index], B);
        recursive(arr, index+1, sum, B);
        

    }
}
