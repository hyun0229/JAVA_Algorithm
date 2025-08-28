import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Vertex>[] arr = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[x].add(new Vertex(y,e));
            arr[y].add(new Vertex(x,e));
        }
        int dist[] = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(1,0));
        while (!pq.isEmpty()){
            Vertex vertex = pq.poll();
            if (vertex.e > dist[vertex.v])continue;
            for (Vertex next : arr[vertex.v]) {
                if (dist[next.v] > dist[vertex.v] + next.e){
                    dist[next.v] = dist[vertex.v] + next.e;
                    pq.add(new Vertex(next.v, dist[next.v]));
                }
            }
        }
        System.out.println(dist[N]);

    }
    static public  class Vertex implements Comparable<Vertex>{
        int v, e;
        public Vertex(int v, int e){
            this.v =v;
            this.e =e;
        }
        @Override
        public int compareTo(Vertex o) {
            return this.e - o.e;
        }
    }
}
