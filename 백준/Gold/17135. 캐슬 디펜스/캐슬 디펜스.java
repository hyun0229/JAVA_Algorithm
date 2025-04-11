
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int x,y;
        Node(int x, int y){
            this.x = x; this.y =y;
        }
        @Override
        public boolean equals(Object obj) {
            if(this == obj)return true;
            Node other = (Node)obj;
            return this.x == other.x && this.y == other.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }
    static int N,M,D;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        int ans = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M-2; i++) {
            for (int j = i+1; j < M-1; j++) {
                for (int j2 = j+1; j2 < M; j2++) {
                    HashSet<Node> chk = new HashSet<>();
                    hunt(new int[]{i,j,j2},D,chk);
                    ans = Math.max(ans, chk.size());
                }
            }
        }
        System.out.println(ans);
    }
    public static void hunt(int[] archers, int d, HashSet<Node> chk){
        for (int stage = N-1; stage >= 0; stage--) {
            for (int archer : archers) {
                L:for (int range = 1; range <= d; range++) {
                    int x = stage, y = archer-range+1;
                    for (int i = 0; i < range-1; i++) {
                        if (!(x<0||y<0||y>=M||map[x][y]==0)){
                            chk.add(new Node(x, y));
                            break L;
                        }
                        x--;y++;
                    }
                    for (int i = 0; i < range; i++) {
                        if (!(x<0||y<0||y>=M||map[x][y]==0)){
                            chk.add(new Node(x, y));
                            break L;
                        }
                        x++;y++;
                    }
                }   
            }
            for (Node node : chk) {
                map[node.x][node.y] = 0;
            }
        }
        for (Node node : chk) {
            //System.out.print(node.x+ ","+node.y+ " ");
            map[node.x][node.y] =1;
        }
        //System.out.println();
    }
}
