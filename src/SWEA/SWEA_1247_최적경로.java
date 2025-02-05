package SWEA;

import java.util.Scanner;

public class SWEA_1247_최적경로 {
    public static int ans = 0;
    public static int L = 0;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            ans = 0;
            int N = sc.nextInt();
            L = sc.nextInt();
            int[][] bugers = new int[N][2];
            for (int i = 0; i < N; i++) {
                bugers[i][0] = sc.nextInt();
                bugers[i][1] = sc.nextInt();
            }
            recursive(bugers, 0, 0, new int[N][2] );
            System.out.printf("#%d %d\n",test_case,ans);
        }
    }
    static void recursive(int[][] arr,int idx, int depth, int[][] sel) {
        //basis part
        int kcal = 0;
        int score = 0;
        for (int i = 0; i < depth; i++) {
            score += sel[i][0];
            kcal+= sel[i][1];
        }
        if(kcal>L) {
            return;
        }
        else {
            ans = Math.max(ans, score);
        }

        if(depth == sel.length) {
            return;
        }
        if(idx ==arr.length) {
            return;
        }

        sel[depth][0] =arr[idx][0];
        sel[depth][1] =arr[idx][1];
        recursive(arr, idx+1, depth+1, sel);
        recursive(arr, idx+1, depth, sel);

    }
}