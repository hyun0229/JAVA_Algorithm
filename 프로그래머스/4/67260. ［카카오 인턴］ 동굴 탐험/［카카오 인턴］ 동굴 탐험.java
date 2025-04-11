import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    ArrayList<Integer>[] map;
    int[][] orders;
    boolean[] vis;
    public boolean solution(int n, int[][] path, int[][] order) {
        map = new ArrayList[n];
        orders = new int[n][2];
        int degree[] = new int[n];
        vis = new boolean[n];
        Arrays.fill(degree, 1);
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int[] is : path) {
            map[is[0]].add(is[1]);
            map[is[1]].add(is[0]);
        }
        for (int[] is : order) {
            map[is[0]].add(is[1]);
            degree[is[1]]+=1;
        }
        int cnt =0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        degree[0] -= 1;
        vis[0] = true;
        if(degree[0]!=0)return false;
        while (!q.isEmpty()) {
            int now = q.poll();
            cnt ++;
            for (int i : map[now]) {
                if (!vis[i]&&--degree[i]==0) {
                    vis[i]=true;
                    q.add(i);
                }
            }
        }

        return cnt==n;
    }

}