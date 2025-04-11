
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int map[][],r,c,t;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        int[] line = new int[2];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        int num = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1)line[num++] = i;
            }
        }
        int ans = 0;
        ans += aircleaner(line);
        // for (int[] is : map) {
        //     System.out.println(Arrays.toString(is));
        // }

        System.out.println(ans);
    }

    private static int aircleaner(int line[]) {
        for (int i = 0; i < t; i++) {
            dust();
            lotation(0, line[0]+1,true);
            lotation(line[1], r,false);
        }
        int sum=0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sum+= map[i][j];
            }
        }
        return sum+2;
    }
    private static void dust() {
        boolean vis[][] = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!vis[i][j]&&map[i][j]!=0) {
                    bfs(i,j,vis);
                }
            }
        }

    }
    private static void bfs(int i, int j,boolean[][] vis) {
        vis[i][j] = true;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(i, j, map[i][j]));
        while (!q.isEmpty()) {
            Node n = q.poll();
            int x = n.x; int y = n.y;
            int p = n.p/5;
            int cnt = 0;
            for (int k = 0; k < 4; k++) {
                int px = x+dx[k];
                int py = y+dy[k];
                if (px<0||py<0||px>=r||py>=c||map[px][py]==-1)continue;
                cnt++;
                if(map[px][py]!=0&&!vis[px][py]){
                    q.add(new Node(px,py,map[px][py]));
                }
                map[px][py]+=p;
                vis[px][py] = true;
            }
            map[x][y]-= p*cnt;
        }
    }
    static public class Node {
        int x,y,p;
        public Node (int x, int y, int p){
            this.x = x;
            this.y = y;
            this.p = p;
        }
        
    }
    private static void lotation(int bottom,int top,boolean flag) {
        int i = bottom;
        int j = 0; 
        int num = 0;
        Deque<Integer> q = new ArrayDeque<>();
        while(true) {
            int px = i + dx[num];
            int py = j + dy[num];
            if (px<bottom||py<0||px>=top||py>=c) {
                num+=1;
                num%=4;
                px = i + dx[num];
                py = j + dy[num];
            }
            q.add(map[px][py]);
            i = px;
            j = py;
            if (i== bottom && j == 0) {
                break;
            }
        }

        if (!flag) {
            int tmp = q.pollLast();
            q.addFirst(tmp);
            tmp = q.pollLast();
            q.addFirst(tmp);
        }


        map[i][j] = q.poll();
        num = 0;
        while(!q.isEmpty()) {
            int px = i + dx[num];
            int py = j + dy[num];
            if (px<bottom||py<0||px>=top||py>=c) {
                num+=1;
                num%=4;
                px = i + dx[num];
                py = j + dy[num];
            }
            map[px][py] = q.poll();
            i = px;
            j = py;
        }
        if (flag) {
            map[top-1][0] = -1;
            map[top-1][1] = 0;
        }
        else{
            map[bottom][0] = -1;
            map[bottom][1] = 0;
        }
    }

}
