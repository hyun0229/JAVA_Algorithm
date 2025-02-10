package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17472_다리만들기2 {
    static class Node {
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N,M,map[][], vis_map[][], prime_map[][];
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        vis_map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && vis_map[i][j] == 0) {
                    num++;
                    bfs(i,j,num);
                }
            }
        }
        prime_map = new int[num+1][num+1];
        adj_map();
        //TestTool.test_map(prime_map);
        int[] dist = new int[num+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] v = new boolean[num+1];
        dist[1] = 0;
        boolean flag = true;
        for(int step=1; step< num; step++){
			int minIdx = -1;
			int minD = Integer.MAX_VALUE;	
			for (int i = 0; i < dist.length; i++) {
				if (dist[i] < minD && !v[i]) {
					minIdx = i;
					minD = dist[i];
				}
			}
            if(minIdx == -1){
                flag = false;
                break;
            }
			v[minIdx] = true;
			for (int i = 0; i < num+1; i++) {
					if (prime_map[minIdx][i] != 0 && !v[i] && prime_map[minIdx][i] < dist[i]) {
					dist[i] = prime_map[minIdx][i];
				}
			}
		}
        int ans = 0;
        for(int i = 1 ; i < dist.length ; i++){
            if (dist[i] == Integer.MAX_VALUE) {
                flag = false;
                break;
            }
            ans += dist[i];
        }
        if (flag) {
            System.out.println(ans);
        }
        else System.out.println(-1);
    }
    private static void adj_map() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = vis_map[i][j];
                if (num != 0) {
                    for (int k = 0; k < 4; k++) {
                        int px = i + dx[k];
                        int py = j + dy[k];
                        for (int l = 0 ; true; l++) {
                            if (px<0||py< 0|| px>=N || py>= M || vis_map[px][py] == num ) break;
                            else if (vis_map[px][py] != 0) {
                                int chk = vis_map[px][py];
                                if(l==1)break;
                                if (prime_map[num][chk] == 0) {
                                    prime_map[num][chk] = l;
                                    prime_map[chk][num] = l;
                                }
                                else{
                                    prime_map[num][chk] = Math.min(prime_map[num][chk], l);
                                    prime_map[chk][num] = prime_map[chk][num];
                                }
                                break;
                            }
                            px = px + dx[k];
                            py = py + dy[k];
                        }
                    }
                }
            }
        }
    }
    private static void bfs(int i, int j, int num) {
        Queue<Node> q= new ArrayDeque<>();
        q.offer(new Node(i, j));
        vis_map[i][j] = num;
        while (!q.isEmpty()) {
            Node now = q.poll();
            int x = now.x;
            int y = now.y;
            for (int k = 0; k < dy.length; k++) {
                int px = x + dx[k];
                int py = y + dy[k];
                if (px<0||py< 0|| px>=N || py>= M || map[px][py]==0||vis_map[px][py] !=0 )continue;
                q.offer(new Node(px, py));
                vis_map[px][py] = num;
            }
        }
    }
}
