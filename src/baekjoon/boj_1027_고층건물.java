package baekjoon;

import java.io.*;
import java.util.*;
public class boj_1027_고층건물 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[N+2];
        int[] rck = new int[N+2];
        int[] lck = new int[N+2];
        for (int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1 ;i <= N; i++) {
            double maxSlope = 0;
            boolean flag = false;
            for (int j = i + 1; j <= N; j++) {
                double slope = (double)(arr[j] - arr[i]) / (j - i);
                if (!flag && maxSlope == 0 && arr[i]==arr[j]) {
                    flag = true;
                    rck[i]+=1;
                }
                if (slope > maxSlope) {
                    rck[i]+=1;
                    lck[j]+=1;
                    maxSlope = slope;
                }
            }
        }
        for (int i = N; i >0; i--) {
            double maxSlope = 0;
            boolean flag = false;
            for (int j = i-1; j >= 0; j--) {
                double slope = (double)(arr[j] - arr[i]) / (i - j);
                if (!flag && maxSlope == 0 && arr[i]==arr[j]) {
                    flag = true;
                    lck[i]+=1;
                }
                if (slope > maxSlope) {
                    rck[i]+=1;
                    lck[j]+=1;
                    maxSlope = slope;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N+1; i++) {
            ans = Math.max(ans, lck[i]+rck[i]);
        }
        //System.out.println(Arrays.toString(rck));
        //System.out.println(Arrays.toString(lck)); 
        System.out.println(ans);
    }
}
