import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int R, C;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[][] arr;
    static int ans = 0;
    static boolean[] visited = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = s.charAt(j) - 'A';
            }
        }

        visited[arr[0][0]] = true;
        DFS(0, 0, 1);

        System.out.println(ans);
    }

    public static void DFS(int x, int y, int depth) {
        ans = Math.max(ans, depth);

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

            int idx = arr[nx][ny];
            if (!visited[idx]) {
                visited[idx] = true;
                DFS(nx, ny, depth + 1);
                visited[idx] = false;
            }
        }
    }
}
