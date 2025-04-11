import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, W, H, ans,total;

    static int dx[] = { 1, 0, -1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            total = 0;
            int[][] map = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if(tmp!=0)total++; 
                    map[i][j] = tmp;
                }
            }
            ans = 0;
            recursive(0, map,0);
            //System.out.println(ans);
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(test_case).append(' ').append(total-ans);
            System.out.println(sb.toString());

        }
    }

    private static void recursive(int lv, int[][] map,int cnt) {
        if (N == lv) {
            ans = Math.max(ans, cnt);
            return;
        }
        for (int i = 0; i < W; i++) {
            int tmpMap[][] = new int[H][];
            for (int j = 0; j < H; j++) {
                tmpMap[j] = map[j].clone();
            }
            int broken = selectball(i, tmpMap);
            if (broken == total){
                ans = broken;
                return;
            }
            if(broken == 0)continue;
            recursive(lv + 1, tmpMap, cnt + broken);
        }
    }

    private static int selectball(int j, int[][] map) {
        Node start = null;
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            if (map[i][j] != 0) {
                if (map[i][j] == 1) {
                    map[i][j] = 0;
                    cnt++;
                    return cnt;
                }
                start = new Node(i, j, map[i][j]);
                break;
            }
        }
        if (start == null)return cnt;
        cnt+=bfs(start, map);
        return cnt;
    }

    private static int bfs(Node start, int[][] map) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);
        map[start.x][start.y] = 0;
        int cnt = 0;
        cnt++;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int size = node.num;
            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;
                for (int j = 0; j < size - 1; j++) {
                    nx += dx[i];
                    ny += dy[i];
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W)break;
                    if (map[nx][ny] == 0)continue;
                    cnt++;
                    if (map[nx][ny] == 1)map[nx][ny] = 0;
                    else {
                        q.add(new Node(nx, ny, map[nx][ny]));
                        map[nx][ny] = 0;
                    }
                }
            }
        }
        setMap(map);

        return cnt;
    }

    private static void setMap(int[][] map) {
        for (int j = 0; j < W; j++) {
            int empty = H - 1;
            for (int i = H - 1; i >= 0; i--) {
                if (map[i][j] != 0) {
                    map[empty][j] = map[i][j];
                    if (empty != i) map[i][j] = 0;
                    empty--;
                }
            }
        }
    }
    static class Node {
        int x, y, num;
        public Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
