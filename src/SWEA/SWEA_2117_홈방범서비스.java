package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2117_홈방범서비스 {
    static int KS[], map[][];
    static int max;
    static int N, M,ans;
    static int house;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        KS = new int[22];
        for (int i = 0; i < 22; i++) {
            KS[i] = i * i + (i - 1) * (i - 1);
        }
        house = 0;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                    if(num==1)house++;
                }
            }
            ans = 1;
            for (int i = 2; i <= N + 1; i++) {
                checkMap(i);
                if (house==ans)break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(test_case).append(' ').append(ans);
            System.out.println(sb.toString());
        }
    }

    private static void checkMap(int k) {
        int size = (k - 1) / 2;
        for (int i = size-1; i < N - size+1; i++) {
            for (int j = size-1; j < N - size+1; j++) {
                calc(i, j, k);
                if (house==ans)break;
            }
        }
    }

    private static void calc(int x, int y, int k) {
        int cnt = 0;
        for (int i = x - k; i < x + k; i++) {
            for (int j = y - k; j < y + k; j++) {
                if (i < 0 || j < 0 || i >= N || j >= N)continue;
                int dist = Math.abs(i - x) + Math.abs(j - y);
                if (dist > k - 1)continue;
                if(map[i][j] == 1)cnt++;
            }
        }
        if (cnt*M-KS[k]>=0) {
            ans = Math.max(ans, cnt);
        }
    }
}
