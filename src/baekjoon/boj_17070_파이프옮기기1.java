package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17070_파이프옮기기1 {


    
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int map[][] = new int[N][N];
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map.length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //recursive(map,0,1,N,1);
        bfs(map, N);
        System.out.println(ans);
    }
    private static void recursive(int[][] map, int i, int j,int N,int state) {
        if (i == N-1 && j ==N-1) {
            ans++;
            return;
        }
        if (j+1<N && map[i][j+1]!=1 && state!= -1 ) {
            recursive(map, i, j+1, N,1);
        }
        if(i+1< N &&map[i+1][j]!=1 && state !=1){
            recursive(map, i+1, j, N,-1);
        }
        if (i+1< N &&  j+1<N && map[i+1][j]!=1 &&map[i][j+1]!=1  &&map[i+1][j+1]!=1 ) {
            recursive(map, i+1, j+1, N,0);
        }
    }
public static void bfs(int[][] map, int N) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 1, 1}); // 시작 위치 (0,0) 오른쪽으로 시작

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int i = current[0], j = current[1], state = current[2];
            
            if (i == N - 1 && j == N - 1) {
                ans++;
                continue;
            }
            
            // 오른쪽 이동 (→)
            if (j + 1 < N && map[i][j + 1] != 1 && state != -1) {
                queue.add(new int[]{i, j + 1, 1});
            }
            
            // 아래 이동 (↓)
            if (i + 1 < N && map[i + 1][j] != 1 && state != 1) {
                queue.add(new int[]{i + 1, j, -1});
            }
            
            // 대각선 이동 (↘)
            if (i + 1 < N && j + 1 < N && map[i][j + 1] != 1 && map[i + 1][j] != 1 && map[i + 1][j + 1] != 1) {
                queue.add(new int[]{i + 1, j + 1, 0});
            }
        }
    }
}
