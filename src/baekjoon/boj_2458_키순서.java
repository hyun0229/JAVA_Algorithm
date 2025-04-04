package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2458_키순서 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] arr = new ArrayList[N];
        List<Integer>[] rearr = new ArrayList[N];
        BitSet[] set = new BitSet[N];   
        BitSet[] reset = new BitSet[N];  

        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
            rearr[i] = new ArrayList<>();
            set[i] = new BitSet(N);
            reset[i] = new BitSet(N);
            set[i].set(i);  
            reset[i].set(i);  
        }

        int[] dist = new int[N];
        int[] reDist = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            arr[x].add(y);
            rearr[y].add(x);
            dist[y]++;
            reDist[x]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (dist[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : arr[now]) {
                set[next].or(set[now]);
                dist[next]--;
                if (dist[next] == 0) q.add(next);
            }
        }

        q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (reDist[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : rearr[now]) {
                reset[next].or(reset[now]);
                reDist[next]--;
                if (reDist[next] == 0) q.add(next);
            }
        }

        //StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int total = set[i].cardinality() + reset[i].cardinality() - 1; 
            if (total == N ) {
                cnt++;
                //sb.append(i + 1).append(' ');
            }
        }
        System.out.println(cnt);
        //System.out.println(sb.toString());
    }
}
