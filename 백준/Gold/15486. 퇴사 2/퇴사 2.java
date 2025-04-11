import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int list[][] = new int[N][2];
        int memo[] = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }
        if (list[N-1][0] == 1)memo[N-1] = list[N-1][1];
        for (int i = N-2; i >=0 ; i--) {
            int cost = list[i][1];
            int working = list[i][0];
            if(working + i >N){
                memo[i] = memo[i+1];
            }
            else if (working + i ==N) {
                memo[i] = Math.max(memo[i+1], cost);
            }
            else{
                memo[i] = Math.max(memo[i+1], cost + memo[i+working]);
            }
        }
        System.out.println(memo[0]);
    }
}
