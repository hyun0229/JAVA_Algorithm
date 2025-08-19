import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(N);
        dist[N] = 0;

        while (!dq.isEmpty()) {
            int now = dq.poll();

            if (now == K) {
                System.out.println(dist[now]);
                return;
            }

            if (now * 2 <= MAX && dist[now * 2] > dist[now]) {
                dist[now * 2] = dist[now];
                dq.addFirst(now * 2); 
            }

            if (now - 1 >= 0 && dist[now - 1] > dist[now] + 1) {
                dist[now - 1] = dist[now] + 1;
                dq.addLast(now - 1);
            }
            
            if (now + 1 <= MAX && dist[now + 1] > dist[now] + 1) {
                dist[now + 1] = dist[now] + 1;
                dq.addLast(now + 1);
            }
        }
    }
}
