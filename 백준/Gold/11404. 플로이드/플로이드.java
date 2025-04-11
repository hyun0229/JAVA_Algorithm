import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        int dp[][] = new int[V][V];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int value = Integer.parseInt(st.nextToken());
            dp[from][to] = Math.min(dp[from][to], value);
        }
        for (int k = 0; k < dp.length; k++) {
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp.length; j++) {
                    if (dp[i][k] < INF && dp[k][j] < INF) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (dp[i][j] == INF) dp[i][j] = 0;
                sb.append(dp[i][j]);
                if (j < dp.length - 1) sb.append(' '); 
            }
            if (i < dp.length - 1) sb.append('\n'); 
        }
        System.out.print(sb.toString());
    }

}
