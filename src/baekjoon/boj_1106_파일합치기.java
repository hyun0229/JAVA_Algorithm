package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1106_파일합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int map[][] = new int[N+1][N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                map[i+1][i+1] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <N; j++) {
                    
                }
            }

        }
    }
}
