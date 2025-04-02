package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1038_감소하는수 {
    static int N;
    static int count = -1;
    static long answer = -1;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        flag = false;
        for (int i = 0; i < 10; i++) {
            if (flag)break;
            recursive(10, i, 0);
        }

        System.out.println(answer);
    }

    private static void recursive(int max, int lv, long ans) {
        if (lv == -1) {
            count++;
            if (count == N) {
                flag = true;
                answer = ans;
            }
            return;
        }
        for (int i = 0; i < max; i++) {
            if (flag)break;
            recursive(i, lv - 1, ans * 10 + i);
        }
    }
}