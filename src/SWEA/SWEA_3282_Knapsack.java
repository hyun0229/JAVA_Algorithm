package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3282_Knapsack{
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int[] dp = new int[W+1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int w = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                for (int j = W; j >= w; j--) {
                    dp[j] = Math.max(dp[j], dp[j-w]+v);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(test_case).append(' ').append(dp[W]);
            System.out.println(sb.toString());
        }
    }
}
