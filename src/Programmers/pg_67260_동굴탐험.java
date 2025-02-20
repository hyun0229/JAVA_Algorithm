package Programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class pg_67260_동굴탐험{
    public static void main(String[] args) {
        Solution_67260 s = new Solution_67260();
        int[][] array1 = {
            {0, 1},
            {0, 3},
            {0, 7},
            {8, 1},
            {3, 6},
            {1, 2},
            {4, 7},
            {7, 5}
        };

        int[][] array2 = {
            {8, 5},
            {6, 7},
            {4, 1}
        };
        System.out.println(s.solution(9, array1, array2));
    }
}

class Solution_67260 {
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
        degree[0] = 0;
        vis[0] = true;
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