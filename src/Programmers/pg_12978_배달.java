package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class pg_12978_배달 {
    public static void main(String[] args) {
        int[][] arr = {
            {1, 2, 1},
            {1, 3, 2},
            {2, 3, 2},
            {3, 4, 3},
            {3, 5, 2},
            {3, 5, 3},
            {5, 6, 1}
        };
        Solution_12978 s = new Solution_12978();
        System.out.println(s.solution(6, arr, 4));
    }

}


class Solution_12978 {
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
        System.out.println(Arrays.toString(dist));
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