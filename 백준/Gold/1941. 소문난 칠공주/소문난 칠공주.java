import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int ans = 0;
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static char[][] map;
    static ArrayList<Node> arr;
    public static class Node {
        int x, y;
        Node(int x,int y){this.x =x; this.y = y;}
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = 5;
        map = new char[N][N];
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
        if (map[i][j] == 'S')recursive(i+1, j, lv+1, cnt+1);
        else recursive(i+1, j, lv+1, cnt);
        arr.remove(arr.size()-1);
        recursive(i+1, j, lv, cnt);
    }
    private static void bfs() {
        int[][] num = new int[5][5];
        Queue<Node> q = new ArrayDeque<>();
        q.add(arr.get(0));
        for (Node node : arr) {
            num[node.x][node.y] = 1;
        }
        int cnt = 0;
        num[q.peek().x][q.peek().y] =0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            cnt+=1;
            for (int i = 0; i < 4; i++) {
                int x = now.x + dx[i];
                int y = now.y + dy[i];
                if (x>=5||x<0||y>=5||y<0||num[x][y]==0) continue;
                q.add(new Node(x, y));
                num[x][y] = 0;
            }
        }
        if (cnt==7)ans++;

    }
}
