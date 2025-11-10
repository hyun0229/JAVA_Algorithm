import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int MAX = 100000;
        boolean[] visited = new boolean[MAX + 1];
        int[] prev = new int[MAX + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        visited[N] = true;
        prev[N] = -1; 

        int time = 0;
        boolean found = false;

        while (!q.isEmpty() && !found) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur == K) {
                    found = true;
                    break;
                }
                // 다음 가능한 위치
                int[] nexts = { cur - 1, cur + 1, cur * 2 };
                for (int nx : nexts) {
                    if (nx < 0 || nx > MAX) continue;
                    if (!visited[nx]) {
                        visited[nx] = true;
                        prev[nx] = cur;
                        q.offer(nx);
                    }
                }
            }
            if (!found) time++;
        }

        System.out.println(time);

        ArrayList<Integer> path = new ArrayList<>();
        int cur = K;
        while (cur != -1) {
            path.add(cur);
            cur = prev[cur];
        }
        Collections.reverse(path);
        
        StringBuilder sb = new StringBuilder();
        for (int v : path) {
            sb.append(v).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
