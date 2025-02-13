package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17406_배열돌리기4 {
    static int N,M,K,ans;
    static int[][] map, calc;
    static boolean[] vis;
    public static void main(String[] args) throws IOException {
        //N,M,K = 배열돌리기 횟수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        //배열 선언N*M, 돌리기 연산 배열 K*3, 방문배열 K
        map = new int[N][M];
        calc = new int[K][3];
        vis = new boolean[K];
        //배열입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                calc[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //K로 순열만들기 -> 백트랙킹
        ans = Integer.MAX_VALUE;
        recursive(0,new ArrayList<Integer>());
        
        //정답 출력
    }
    private static void recursive(int lv, ArrayList<Integer> arr) {
        if (lv == K) {
            for (Integer i : arr) {
                rotation(i,true);
            }

            for (int[] row : map) {
                int num = 0;
                for (int i : row) {
                    num+=i;
                }
                ans = Math.min(num, ans);
            }
            for (int i = K-1; i > 0; i--) {
                rotation(arr.get(i), false); //배열 원상복구
            }
            return;
        }
        for (int i = 0; i < K; i++) {
            vis[i] = true;
            arr.add(i);
            recursive(lv+1, arr);
            vis[i] = false;
            arr.remove(arr.size()-1); 
            recursive(lv, arr);
        }

    }
    private static void rotation(Integer num,boolean flag) {
        boolean[][] V = new boolean[N][M];
        int r = calc[num][0]-1;
        int c = calc[num][1]-1;
        int s = calc[num][2];
        int[] T1 = {1,0,-1,0};
        int[] T2 = {0,1,0,-1};
        int[] dx,dy;
        if (flag) {dx = T1;dy=T2;}
        else{dx = T2; dy =T1;}
        //배열 true false 확인
        for (int i = 1; i <= s; i++) {
            int nor = (i*2-1)*4+4; //총 회전횟수
            int x = r-i; int y = c - i;
            int temp = map[x][y];
            int lv = 0;
            for (int j = 0; j < nor ; j++) {
                if (x) {
                    
                }
            }
        }

    }
}
