package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestTool {
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 배열인지 확인
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int Ms = Integer.parseInt(st.nextToken());
            int Ma = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int arr[][] = new int[N][L+1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < L+1; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int cost = Ms+Ma*L;
            for (int i = 0; i < L; i++) {
                int Dp[] = new int[Ms+1];
                for (int j = 0; j < N; j++) {
                    int w = arr[j][i]; 
                    int v = arr[j][i+1] - arr[j][i];
                    if (v<=0) continue;
                    for (int k = 1; k <= Ms; k++) {
                        if (k>=w) {
                            Dp[k] = Math.max(Dp[k], Dp[k-w]+v);
                        }
                    }
                }
                Ms+=Dp[Ms];
                Ms+=Ma;
            }
            System.out.println("#"+testcase+" "+(Ms-cost));
        }
    }
}
