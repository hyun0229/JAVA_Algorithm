package baekjoon;

import java.io.*;
import java.util.*;

public class bj_11722__가장긴감소하는부분수열_이진탐색버전 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[N];
        int len = 0;
        for (int i = 0; i < N; i++) {
            int x = -Integer.parseInt(st.nextToken());
            int idx = Arrays.binarySearch(dp, 0, len, x);
            if (idx < 0) {
                idx = -idx - 1;
            }
            dp[idx] = x;
            if (idx == len) {
                len++;
            }
        }
        System.out.println(len);
    }
}