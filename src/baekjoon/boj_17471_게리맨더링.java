package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17471_게리맨더링 {
    static int ans = Integer.MAX_VALUE, peoples[],N;
    static boolean[]chk;
    static ArrayList<Integer>[] adjLists;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        peoples = new int[N];
        st = new StringTokenizer(br.readLine());
        adjLists = new ArrayList[N];
        for (int i = 0; i < adjLists.length; i++) {
            adjLists[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            peoples[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < adjLists.length; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                adjLists[i].add(Integer.parseInt(st.nextToken())-1);
            }
        }
        chk = new boolean[N];
        recursive(0,new ArrayList<Integer>(),chk);
        System.out.println(ans != Integer.MAX_VALUE?ans:-1);
        
        }
        static void recursive( int num, ArrayList<Integer> arrayList,boolean[] chk) {
            if (num==N) {
                boolean V[] = new boolean[N];
                boolean bV[] = new boolean[N];
                int A = 0,B = 0;
                for (int i = 0; i < chk.length; i++) {
                    bV[i] = !chk[i];
                    V[i] = chk[i];
                }
                A = bfs(bV);
                B = bfs(V);
                if (A == -1 || B == -1) {
                    return;    
                }
                ans = Math.min(ans,Math.abs(A-B));
                return;
                
            }
            arrayList.add(num);
            chk[num] =true;
            recursive(num+1, arrayList,chk);
            chk[num] =false;
            arrayList.remove(arrayList.size()-1);
            recursive(num+1, arrayList,chk);
        }
        private static int bfs(boolean[] bV) {
            Queue<Integer> q = new ArrayDeque<>();
            int sum = 0;
            for (int i = 0; i < bV.length; i++) {
                if (bV[i]!=true) {
                    sum += peoples[i];
                    q.offer(i);
                    bV[i] = true;
                    break;
                }
            }
            while (!q.isEmpty()) {
                int now = q.poll();
                for (Integer i : adjLists[now]) {
                    if (!bV[i]) {
                        sum += peoples[i];
                        q.offer(i);
                        bV[i] =true;
                    }
                }
            }

            for (int i = 0; i < bV.length; i++) {
                if (!bV[i]) {
                    return -1;
                }
            }
            return sum;
        }
}
