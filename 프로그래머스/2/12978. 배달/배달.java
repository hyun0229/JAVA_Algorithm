import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution{
    public int solution(int N, int[][] road, int K) {
        ArrayList<Vertex>[] arr = new ArrayList[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < road.length; i++) {
            int v = road[i][0]-1;
            int e = road[i][1]-1;
            int w = road[i][2];
            arr[v].add(new Vertex(e, w));
            arr[e].add(new Vertex(v, w));
        }
        int[] dist = new int[N];
        boolean[] vis = new boolean[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        dist[0] = 0;
        pq.add(new Vertex(0, 0));
        while (!pq.isEmpty()) {
            Vertex v = pq.poll();
            if (vis[v.e])continue;
            vis[v.e] = true;
            for (Vertex t : arr[v.e]) {
                if (!vis[t.e]&& v.w + t.w<dist[t.e]) {
                    dist[t.e] = v.w + t.w;
                    pq.add(new Vertex(t.e, dist[t.e]));
                }
            }
        }
        int cnt = 0;
        for (int i : dist) {
            if(i<=K)cnt++;
        }
        return cnt;
    }
    static class Vertex implements Comparable<Vertex>{
        int e,w;
        public Vertex(int e, int w){
            this.e = e;
            this.w = w;
        }
        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.w, o.w);
        }
    }
}