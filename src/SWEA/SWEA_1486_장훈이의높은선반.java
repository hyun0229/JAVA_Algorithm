package SWEA;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SWEA_1486_장훈이의높은선반 {
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

