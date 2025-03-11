package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2252_줄세우기 {
    static int chain[], N,M;
    static ArrayList<Integer>[] arr;
    static StringBuilder sr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chain = new int[N];
        arr= new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            chain[y]++;
            arr[x].add(y);
        }
        sr = new StringBuilder();

        bfs();
        System.out.println(sr);
    }
    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i <N; i++) {
            if (chain[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int num = q.poll();
            sr.append(num+1).append(" ");
            for (Integer x : arr[num]) {
                if (--chain[x]==0) {
                    q.add(x);
                }
            }
        }
    }
}
