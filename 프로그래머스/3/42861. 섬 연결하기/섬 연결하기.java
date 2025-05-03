import java.util.*;
class Solution {
    
    public int solution(int n, int[][] costs) {

        ArrayList<Vertex>[] arr = new ArrayList[n];
        for(int i =0; i<n ; i++){
            arr[i] = new ArrayList<>();
        }
        for(int i = 0; i<costs.length ; i++){
            int x = costs[i][0];
            int y = costs[i][1];
            int w = costs[i][2];
            arr[x].add(new Vertex(y,w));
            arr[y].add(new Vertex(x,w));
        }
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] vis = new boolean[n];
        dist[0] = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(0, 0));
        while (!pq.isEmpty()) {
            Vertex v = pq.poll();
            if(vis[v.e]) continue;
            vis[v.e] = true;
            for (Vertex t : arr[v.e]) {
                if (!vis[t.e]&& t.w<dist[t.e]){
                    dist[t.e] = t.w;
                    pq.add(new Vertex(t.e, dist[t.e]));
                }
            }
        }
        int answer = 0;
        for(int i : dist){
            if(i == Integer.MAX_VALUE)continue;
            answer+=i;
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
}