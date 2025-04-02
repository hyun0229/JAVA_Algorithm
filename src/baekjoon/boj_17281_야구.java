package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17281_야구 {
    static int N,ans;
    static Integer players[][];
    static boolean vis[] = new boolean[9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        players = new Integer[9][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                players[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        Deque<Integer[]> dq = new ArrayDeque<>();
        vis[0] = true;
        ans = 0;
        recursive(dq,0);
        System.out.println(ans);
    }
    private static void recursive(Deque<Integer[]> dq,int i) {
        if (i == 9) {
            // for (Integer[] integers : dq) {
            //     System.out.println(Arrays.toString(integers));
            // }
            // System.out.println("__________");
            ans = Math.max(ans, baseball(dq));
            return;
        }
        if(i == 3){
            dq.add(players[0]);
            recursive(dq, i+1);
            dq.pollLast();
        }
        else{
            for (int j = 0; j <9 ; j++) {
                if (!vis[j]) {
                    vis[j] = true;
                    dq.add(players[j]);
                    recursive(dq, i+1);
                    vis[j] = false;
                    dq.pollLast();
                }

            }
        }
    }
    private static int baseball(Queue<Integer[]> nowq) {
        Queue<Integer[]> q =new ArrayDeque<>();
        for (Integer[] integers : nowq) {
            q.add(integers);
        }
        int score = 0 ;
        Queue<Integer> ground;
        for (int i = 0; i < N; i++) {
            int out = 0;
            ground = new ArrayDeque<>();
            while (out!=3) {
                Integer[] player = q.poll();
                q.add(player);
                if(player[i]==0) {
                    out++;
                    continue;
                }
                int size = ground.size();
                for (int j = 0; j < size; j++) {
                    int num = ground.poll();
                    if (num+player[i]>=4)score++;
                    else ground.add(num+player[i]);
                }
                if (player[i]==4)score++;
                else ground.add(player[i]);
            }
        }
        return score;
    }
}
