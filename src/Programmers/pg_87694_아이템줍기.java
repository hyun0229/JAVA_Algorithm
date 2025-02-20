package Programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class pg_87694_아이템줍기 {
    public static void main(String[] args) {
        solution_87694 s = new solution_87694();
        int[][] arr = {
            {1, 1, 8, 4},
            {2, 2, 4, 9},
            {3, 6, 9, 8},
            {6, 3, 7, 7}
        
        };
        int a =s.solution(arr, 9, 7, 6, 1);
        System.out.println(a);
    }


}
class solution_87694{
    int[][] map;
    boolean vis[][];
    int dx[] =  {1,0,-1,0};
    int dy[] =  {0,1,0,-1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[102][102];
        vis = new boolean[102][102];
        for (int[] is : rectangle) {
            mapfill(is);
        }
        // for (int[] is : map) {
        //     System.out.println(Arrays.toString(is));
        // }
        return bfs(characterX*2,characterY*2, itemX*2,itemY*2)/2;
    }
    private void mapfill(int[] is) {
        int x1 = is[0] * 2, y1 = is[1] * 2;
        int x2 = is[2] * 2, y2 = is[3] * 2;

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (i == x1 || i == x2 || j == y1 || j == y2) {
                    if (map[i][j] == 0) map[i][j] = 1;
                } else {
                    map[i][j] = 9;
                }
            }
        }
    }
    private int bfs(int startX, int startY, int itemX, int itemY) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{startX, startY, 0});
        vis[startX][startY] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0], y = now[1], depth = now[2];

            if (x == itemX && y == itemY) {
                return depth;
            }

            for (int k = 0; k < 4; k++) {
                int px = x + dx[k];
                int py = y + dy[k];

                if (px < 0 || py < 0 || px >= 102 || py >= 102) continue;
                if (map[px][py] != 1 || vis[px][py]) continue;

                vis[px][py] = true;
                q.add(new int[]{px, py, depth + 1});
            }
        }
        return -1;
    }

}
