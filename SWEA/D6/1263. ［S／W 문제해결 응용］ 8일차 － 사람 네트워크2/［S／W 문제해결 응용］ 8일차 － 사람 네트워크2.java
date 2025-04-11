import java.io.*;
import java.util.*;

public class Solution {
    static final int Inf = 10000;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st =new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int dp[][] = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp == 1) {
                        dp[i][j] = 1;
                    }
                    else if (i==j) {
                        dp[i][j] = 0;
                    }
                    else dp[i][j] = Inf;
                }
            }
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
            int min = Inf;
            for (int i = 0; i < dp.length; i++) {
                int cnt = 0;
                for (int j = 0; j < dp.length; j++) {
                    cnt+= dp[i][j];
                }
                min = Math.min(min, cnt);
            }
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(test_case).append(' ').append(min);
            System.out.println(sb.toString());
        }
    }
}
