import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int N, M,K, map[][][],ans;
    static int dx[] = { 1, -1, 0, 0 };
    static int dy[] = { 0, 0, 1, -1 };
 
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numN = Integer.parseInt(st.nextToken());
            int numM = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            N = numN+2*K;
            M = numM+2*K;
            map = new int[N][M][2];
            ans = 0;
            for (int i = K; i <numN+K ; i++) {
                st= new StringTokenizer(br.readLine());
                for (int j = K; j < numM+K; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if(num==0)continue;
                    map[i][j][1] = num;
                    ans++;
                }
            }
            for (int k = 1; k <= K; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        int b = map[i][j][0];
                        int t = map[i][j][1];
                        if(t==0||b==-1)continue;
                        if (b+t+1==k) {
                            update(i,j,t,k);
                        }
                        if (b+t+t ==k) {
                            map[i][j][0] = -1;
                            ans--;
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ").append(ans);
            System.out.println(sb.toString());
        }
    }
 
    private static void update(int x, int y,int v,int now) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i]+x;
            int ny = dy[i]+y;
            if (nx<0||ny<0||nx>=N||ny>=M)continue;
            if (map[nx][ny][1]==0) {
                ans++;
                map[nx][ny][0] = now;
                map[nx][ny][1] = v;
            }
            else if(map[nx][ny][1]<v&&map[nx][ny][0]==now){
                map[nx][ny][0] = now;
                map[nx][ny][1] = v;
            }
        }
    }
}