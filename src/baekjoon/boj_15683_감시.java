package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_15683_감시 {
    static CCTV[] cctvs;
    static int map[][], N, M;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int ans = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cctvs = new CCTV[8];
        map = new int[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0 && num != 6) {
                    cctvs[cnt] = new CCTV(i, j, num);
                    cnt++;
                }
                map[i][j] = num;
            }
        }
        ans = Integer.MAX_VALUE;
        recursive(0, cnt);
        System.out.println(ans);
    }

    private static void recursive(int idx, int cctv_size) {
        if (cctv_size == idx) {
            for (int i = 0; i < cctv_size; i++) {
                draw(cctvs[i]);
            }
            int num = check();
            ans = Math.min(ans, num);
            return;
        }
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < i; k++) {
                cctvs[idx].qMove();
            }
            recursive(idx + 1, cctv_size);
            for (int k = 0; k < 4 - i; k++) {
                cctvs[idx].qMove();
            }
        }
    }

    private static void draw(CCTV cctv) {
        int num = -1;
        for (int k : cctv.q) {
            num++;
            if (k == 0)
                continue;
            int x = cctv.x;
            int y = cctv.y;
            while (true) {
                if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 6)
                    break;
                map[x][y] = 9;
                x += dx[num];
                y += dy[num];
            }
            map[cctv.x][cctv.y] = 10;
        }
    }

    private static int check() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                } else if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static class CCTV {
        int x, y;
        Queue<Integer> q;

        CCTV(int x, int y, int cctvnum) {
            this.x = x;
            this.y = y;
            q = new ArrayDeque<>();
            if (cctvnum == 1) {
                q.add(1);
                q.add(0);
                q.add(0);
                q.add(0);
            }
            if (cctvnum == 2) {
                q.add(1);
                q.add(0);
                q.add(1);
                q.add(0);
            }
            if (cctvnum == 3) {
                q.add(1);
                q.add(1);
                q.add(0);
                q.add(0);
            }
            if (cctvnum == 4) {
                q.add(1);
                q.add(1);
                q.add(1);
                q.add(0);
            }
            if (cctvnum == 5) {
                q.add(1);
                q.add(1);
                q.add(1);
                q.add(1);
            }
        }

        void qMove() {
            int num = q.poll();
            q.add(num);
        }
    }

}
