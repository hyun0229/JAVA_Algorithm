package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1939_중량제한 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Vertex>[] arr = new ArrayList[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            arr[x].add(new Vertex(y,w));
            arr[y].add(new Vertex(x,w));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken())-1;
        int dist[] = Dijkstra(start,arr);
        System.out.println(dist[end]);

    }

    static class Vertex implements Comparable<Vertex> {
        int e,w;
        public Vertex(int e, int w){
            this.e = e;
            this.w = w;
        }
        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(o.w, this.w);
        }
        
    }
    private static int[] Dijkstra(int s, ArrayList<Vertex>[] arr) {
        int dist[] = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] vis = new boolean[N];
        dist[s] = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(s, 0));
        while (!pq.isEmpty()) {
            Vertex v = pq.poll();
            if(vis[v.e]) continue;
            vis[v.e] = true;
            for (Vertex t : arr[v.e]) {
                if (!vis[t.e]){
                    int num = Math.min(v.w, t.w);
                    if (num ==0)num = t.w;
                    if(dist[t.e] ==Integer.MAX_VALUE ||dist[t.e]<num){
                        dist[t.e] = num;
                        pq.add(new Vertex(t.e, dist[t.e]));
                    }
                }
            }
        }
        return dist;
    }
}
