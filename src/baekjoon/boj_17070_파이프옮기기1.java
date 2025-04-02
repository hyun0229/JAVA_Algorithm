package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        recursive(map,0,1,N,1); //재귀 버전
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
}
