import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

class Solution{
    int N;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        N = n;
        a-=1;b-=1;s-=1;
        ArrayList<Vertex>[] arr = new ArrayList[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int[] fare : fares) {
            int v = fare[0]-1;
            int e = fare[1]-1;
            int w = fare[2];
            arr[v].add(new Vertex(e, w));
            arr[e].add(new Vertex(v, w));
        }
        int[][] ans = new int[3][];
        ans[0] = Dijkstra(s,arr);
        ans[1] = Dijkstra(a,arr);
        ans[2] = Dijkstra(b,arr);
        
        L:for (int i = 0; i < N; i++) {
            int num = 0;
            for (int j = 0; j < ans.length; j++) {
                if( ans[j][i] == Integer.MAX_VALUE) continue L;
                num+= ans[j][i];
            }
            answer = Math.min(answer, num);
        }

        return answer;
    }
    
    static class Vertex implements Comparable<Vertex> {
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
    private int[] Dijkstra(int s, ArrayList<Vertex>[] arr) {
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
                if (!vis[t.e]&& t.w+v.w<dist[t.e]){
                    dist[t.e] = t.w+v.w;
                    pq.add(new Vertex(t.e, dist[t.e]));
                }
            }
        }
        return dist;
    }
}