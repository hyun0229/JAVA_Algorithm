package baekjoon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class boj_1941_소문난칠공주 {

    static int ans = 0;
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static char[][] map;
    static boolean[][] vis;
    static ArrayList<Node> arr;
    public static class Node {
        int x, y;
        Node(int x,int y){this.x =x; this.y = y;}
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = 5;
        map = new char[N][N];
        vis = new boolean[N][N];

        for (int i = 0; i < map.length; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < map.length; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        arr = new ArrayList<>();
        recursive(0,0,0,0);
        System.out.println(ans);

    }
    private static void recursive(int i, int j,int lv,int cnt) {
        if (lv == 7) {
            if(arr.size()!=7)System.out.println("?");
            if(cnt>=4)bfs();
            return;
        }
        if (i==5 && j ==4)return;
        if (i==5) {i = 0;j++;}
        arr.add(new Node(i, j));
        vis[i][j] = true;
        if (map[i][j] == 'S')recursive(i+1, j, lv+1, cnt+1);
        else recursive(i+1, j, lv+1, cnt);
        vis[i][j] = false;
        arr.remove(arr.size()-1);
        recursive(i+1, j, lv, cnt);
    }
    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(arr.get(1));
        vis[q.peek().x][q.peek().y] = false;
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = now.x + dx[i];
                int y = now.y + dy[i];
                if (x<0||y<0||x>=5||y>=5||!vis[x][y])continue;
                q.add(new Node(x, y));
                vis[x][y] = false;
            }
        }
        for (boolean[] bools : vis) {
            for (boolean b : bools) {
                if (b) {
                    return;
                }
            }
        }

        for (Node node : arr) {
            System.out.println(node.x+", "+node.y);
        }
        System.out.println("_______________");
        ans++;
    }
}
