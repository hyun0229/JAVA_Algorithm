package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_1249_보급로 {
    static int N;
    static int[][] grid;
    static boolean vis[][];
    static int dist[][];
    static int dx[] = { 0, 1, 0, -1 };
    static int dy[] = { 1, 0, -1, 0 };

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    grid[i][j] = line.charAt(j)-'0';
                }
            }
            dist = new int[N][N];
            for (int[] is : dist) {
                Arrays.fill(is, Integer.MAX_VALUE);
            }
            vis = new boolean[N][N];
            int ans = Dijkstra();
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(test_case).append(' ').append(ans);
            System.out.println(sb.toString());
        }
    }

    public static class Vertex implements Comparable<Vertex> {
        int x, y, w;

        public Vertex(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.w, o.w);
        }

    }

    private static int Dijkstra() {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        dist[0][0] = 0;
        pq.add(new Vertex(0, 0, dist[0][0]));
        while (!pq.isEmpty()) {
            Vertex tmp = pq.poll();
            int nx = tmp.x;
            int ny = tmp.y;
            int nw = tmp.w;
            if (vis[N-1][N-1])break;
            if(vis[nx][ny])continue;
            vis[nx][ny] = true;
            for (int i = 0; i < 4; i++) {
                int px = nx+dx[i];
                int py = ny+dy[i];
                if (px<0||py<0||px>=N||py>=N||vis[px][py])continue;
                if (dist[px][py]>nw + grid[px][py]){
                    dist[px][py] = nw+grid[px][py];
                    pq.add(new Vertex(px, py, dist[px][py]));
                }
            }
        }

        return dist[N-1][N-1];
    }
}
